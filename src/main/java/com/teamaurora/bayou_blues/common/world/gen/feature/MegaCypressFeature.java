package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.google.common.collect.Sets;
import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.util.DirectionalBlockPos;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import java.util.*;

public class MegaCypressFeature extends Feature<TreeFeatureConfig> {
    public MegaCypressFeature(Codec<TreeFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess worldIn, ChunkGenerator generator, Random rand, BlockPos position, TreeFeatureConfig config) {
        int height = rand.nextInt(7) + 15;
        boolean bald = rand.nextInt(15) == 0;
        if (position.getY() <= 0 || position.getY() + height > worldIn.getHeight() - 1) {
            return false;
        }
        for (BlockPos pos2 : BlockPos.iterate(position, position.add(1, 0, 1))) {
            if (!TreeUtil.isValidGround(worldIn, pos2.down(), (SaplingBlock) BayouBluesBlocks.CYPRESS_SAPLING.get())) {
                return false;
            }
        }

        List<DirectionalBlockPos> logs = new ArrayList<>();
        List<BlockPos> leaves = new ArrayList<>();

        for (int i = 0; i <= height; i++) {
            logs.add(new DirectionalBlockPos(position.up(i), Direction.UP));
            logs.add(new DirectionalBlockPos(position.add(1, i, 0), Direction.UP));
            logs.add(new DirectionalBlockPos(position.add(0, i, 1), Direction.UP));
            logs.add(new DirectionalBlockPos(position.add(1, i, 1), Direction.UP));
        }
        int numBranches = rand.nextInt(5) + 4;
        for (int i = 0; i < numBranches; i++) {
            int x;
            if (bald)
                x = rand.nextInt(height - 5) + 4;
            else
                x = rand.nextInt(height - 7) + 4;
            Direction dir = Direction.fromHorizontal(rand.nextInt(4));
            if (dir == Direction.NORTH) {
                // min z, x varies
                addBranch(position.add(rand.nextInt(2),x,0), dir, logs, leaves, rand);
            } else if (dir == Direction.EAST) {
                // max x, z varies
                addBranch(position.add(1,x,rand.nextInt(2)), dir, logs, leaves, rand);
            } else if (dir == Direction.SOUTH) {
                // max z, x varies
                addBranch(position.add(rand.nextInt(2),x,1), dir, logs, leaves, rand);
            } else if (dir == Direction.WEST) {
                // min x, z varies
                addBranch(position.add(0,x,rand.nextInt(2)), dir, logs, leaves, rand);
            }
        }
        if (bald) {
            int variant = rand.nextInt(4);
            switch (variant) {
                case 0:
                    logs.add(new DirectionalBlockPos(position.up(height+1), Direction.UP));
                    break;
                case 1:
                    logs.add(new DirectionalBlockPos(position.add(1, height+1, 0), Direction.UP));
                    break;
                case 2:
                    logs.add(new DirectionalBlockPos(position.add(0, height+1, 1), Direction.UP));
                    break;
                case 3:
                    logs.add(new DirectionalBlockPos(position.add(1, height+1, 1), Direction.UP));
            }
        } else {
            canopyDisc1(position.up(height - 2), leaves);
            canopyDisc3Bottom(position.up(height - 1), leaves, rand);
            canopyDisc3Top(position.up(height), leaves);
            canopyDisc1(position.up(height + 1), leaves);
        }


        List<BlockPos> leavesClean = cleanLeavesArray(leaves, logs);

        boolean flag = true;
        for (DirectionalBlockPos log : logs) {
            if (!TreeUtil.isAirOrLeaves(worldIn, log.pos)) {
                flag = false;
            }
        }
        if (!flag) return false;

        TreeUtil.setDirtAt(worldIn, position.down());

        for (DirectionalBlockPos log : logs) {
            TreeUtil.placeDirectionalLogAt(worldIn, log.pos, log.direction, rand, config);
        }
        for (BlockPos leaf : leavesClean) {
            TreeUtil.placeLeafAt(worldIn, leaf, rand, config);
        }


        Set<BlockPos> decSet = Sets.newHashSet();
        BlockBox mutableBoundingBox = BlockBox.empty();

        List<BlockPos> logsPos = new ArrayList<>();
        for (DirectionalBlockPos log : logs) {
            logsPos.add(log.pos);
        }

        if (!config.decorators.isEmpty()) {
            logsPos.sort(Comparator.comparingInt(Vec3i::getY));
            leavesClean.sort(Comparator.comparingInt(Vec3i::getY));
            config.decorators.forEach((decorator) -> decorator.generate(worldIn, rand, logsPos, leavesClean, decSet, mutableBoundingBox));
        }

        return true;
    }

    private void addBranch(BlockPos pos, Direction dir, List<DirectionalBlockPos> logs, List<BlockPos> leaves, Random rand) {
        logs.add(new DirectionalBlockPos(pos.offset(dir), dir));
        logs.add(new DirectionalBlockPos(pos.offset(dir,2), dir));
        disc2H(pos.offset(dir,2), leaves, rand);
        disc1(pos.offset(dir,2).up(), leaves);
    }

    private void disc1(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) != 1 || Math.abs(z) != 1) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private void disc2H(BlockPos pos, List<BlockPos> leaves, Random rand) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 || Math.abs(z) != 2) {
                    leaves.add(pos.add(x, 0, z));
                    if (rand.nextInt(3) == 0) {
                        leaves.add(pos.add(x, -1, z));
                        if (rand.nextInt(3) == 0) {
                            leaves.add(pos.add(x, -2, z));
                        }
                    }
                }
            }
        }
    }

    private void canopyDisc1(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -1; x <= 2; x++) {
            for (int z = -1; z <= 2; z++) {
                if (!((x == -1 || x == 2) && (z == -1 || z == 2))) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private void canopyDisc3Top(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -3; x <= 4; x++) {
            for (int z = -3; z <= 4; z++) {
                if (!((x <= -2 || x >= 3) && (z <= -2 || z >= 3)) || ((x == -2 || x == 3) && (z == -2 || z == 3))) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private void canopyDisc3Bottom(BlockPos pos, List<BlockPos> leaves, Random rand) {
        for (int x = -3; x <= 4; x++) {
            for (int z = -3; z <= 4; z++) {
                if (!((x == -3 || x == 4) && (z == -3 || z == 4))) {
                    leaves.add(pos.add(x, 0, z));
                    if (rand.nextBoolean()) {
                        leaves.add(pos.add(x, -1, z));
                        if (rand.nextInt(3) != 0) {
                            leaves.add(pos.add(x, -2, z));
                            if (rand.nextBoolean()) {
                                leaves.add(pos.add(x, -3, z));
                            }
                        }
                    }
                }
            }
        }
    }

    private List<BlockPos> cleanLeavesArray(List<BlockPos> leaves, List<DirectionalBlockPos> logs) {
        List<BlockPos> logsPos = new ArrayList<>();
        for (DirectionalBlockPos log : logs) {
            logsPos.add(log.pos);
        }
        List<BlockPos> newLeaves = new ArrayList<>();
        for (BlockPos leaf : leaves) {
            if (!logsPos.contains(leaf)) {
                newLeaves.add(leaf);
            }
        }
        return newLeaves;
    }
}
