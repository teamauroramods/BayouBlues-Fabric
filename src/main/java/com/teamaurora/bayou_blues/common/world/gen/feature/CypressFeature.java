package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.google.common.collect.Sets;
import com.minecraftabnormals.abnormals_core.core.util.MathUtil;
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

public class CypressFeature extends Feature<TreeFeatureConfig> {
    public CypressFeature(Codec<TreeFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess worldIn, ChunkGenerator generator, Random rand, BlockPos position, TreeFeatureConfig config) {
        int height = rand.nextInt(5) + 9;
        boolean bald = rand.nextInt(15) == 0;
        if (position.getY() <= 0 || position.getY() + height > worldIn.getHeight() - 1) {
            return false;
        }
        if (!TreeUtil.isValidGround(worldIn, position.down(), (SaplingBlock) BayouBluesBlocks.CYPRESS_SAPLING.get())) {
            return false;
        }

        List<DirectionalBlockPos> logs = new ArrayList<>();
        List<BlockPos> leaves = new ArrayList<>();

        for (int i = 0; i <= height; i++) {
            logs.add(new DirectionalBlockPos(position.up(i), Direction.UP));
        }
        int numBranches = rand.nextInt(4) + 1;
        if (numBranches == 4) numBranches = 2;
        for (int i = 0; i < numBranches; i++) {
            Direction dir = Direction.fromHorizontal(rand.nextInt(4));
            int x;
            if (bald)
                x = rand.nextInt(height - 3) + 3;
            else
                x = rand.nextInt(height - 5) + 3;
            logs.add(new DirectionalBlockPos(position.up(x).offset(dir), dir));
            logs.add(new DirectionalBlockPos(position.up(x).offset(dir, 2), dir));
            disc2H(position.up(x).offset(dir,2), leaves, rand);
            disc1(position.up(x+1).offset(dir,2), leaves);
        }
        if (!bald) {
            disc1(position.up(height - 1), leaves);
            disc3H(position.up(height), leaves, rand);
            disc2(position.up(height + 1), leaves);
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

    private void disc1(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) != 1 || Math.abs(z) != 1) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private void disc2(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 || Math.abs(z) != 2) {
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

    private void disc3H(BlockPos pos, List<BlockPos> leaves, Random rand) {
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if (Math.abs(x) != 3 || Math.abs(z) != 3) {
                    leaves.add(pos.add(x, 0, z));
                    if (rand.nextInt(3) == 0) {
                        leaves.add(pos.add(x, -1, z));
                        if (rand.nextBoolean()) {
                            leaves.add(pos.add(x, -2, z));
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
