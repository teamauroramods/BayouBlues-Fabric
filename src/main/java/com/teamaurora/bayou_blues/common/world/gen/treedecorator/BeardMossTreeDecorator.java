package com.teamaurora.bayou_blues.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.block.BeardMossBlock;
import com.teamaurora.bayou_blues.common.block.BeardMossBlockBlock;
import com.teamaurora.bayou_blues.common.util.TreeUtil;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class BeardMossTreeDecorator extends TreeDecorator {
    public static final Codec<BeardMossTreeDecorator> CODEC;
    public static final BeardMossTreeDecorator DECORATOR = new BeardMossTreeDecorator();

    @Override
    protected TreeDecoratorType<?> getType() {
        return BayouBluesFeatures.BEARD_MOSS;
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        for (BlockPos pos : logPositions) {
            if (TreeUtil.isAirOrLeaves(world, pos.down())) {
                boolean flag = true;
                int rand1 = random.nextInt(3) + 1;
                for (int i = 0; i < rand1; i++) {
                    if (!TreeUtil.isAirOrLeaves(world, pos.down(i + 1))) {
                        flag = false;
                        break;
                    }
                }
                if (!TreeUtil.isAir(world, pos.down(rand1 + 1))) flag = false;
                if (flag) {
                    for (int i = 0; i < rand1; i++) {
                        world.setBlockState(pos.down(i + 1), BayouBluesBlocks.BEARD_MOSS_BLOCK.getDefaultState().with(BeardMossBlockBlock.PERSISTENT, false), 3);
                    }
                    int rand2 = random.nextInt(6) + 1;
                    for (int i = 0; i < rand2; i++) {
                        if (!world.isAir(pos.down(rand1 + i + 1))) {
                            if (i > 0) {
                                world.setBlockState(pos.down(rand1 + i), BayouBluesBlocks.BEARD_MOSS.getDefaultState(), 3);
                            }
                            break;
                        }
                        if (i == rand2 - 1) {
                            world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.BEARD_MOSS.getDefaultState(), 3);
                        } else {
                            world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.BEARD_MOSS.getDefaultState().with(BeardMossBlock.HALF, DoubleBlockHalf.UPPER), 3);
                        }
                    }
                }
            }
        }
        for (BlockPos pos : leavesPositions) {
            if (random.nextInt(6) == 0) {
                if (TreeUtil.isAirOrLeaves(world, pos.down())) {
                    boolean flag = true;
                    int rand1 = random.nextInt(2) + 1;
                    for (int i = 0; i < rand1; i++) {
                        if (!TreeUtil.isAirOrLeaves(world, pos.down())) {
                            flag = false;
                            break;
                        }
                    }
                    if (!TreeUtil.isAir(world, pos.down(rand1 + 1))) flag = false;
                    if (flag) {
                        for (int i = 0; i < rand1; i++) {
                            world.setBlockState(pos.down(i + 1), BayouBluesBlocks.BEARD_MOSS_BLOCK.getDefaultState().with(BeardMossBlockBlock.PERSISTENT, false), 3);
                        }
                        int rand2 = random.nextInt(4) + 1;
                        for (int i = 0; i < rand2; i++) {
                            if (!world.isAir(pos.down(rand1 + i + 1))) {
                                if (i > 0) {
                                    world.setBlockState(pos.down(rand1 + i), BayouBluesBlocks.BEARD_MOSS.getDefaultState(), 3);
                                }
                                break;
                            }
                            if (i == rand2 - 1) {
                                world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.BEARD_MOSS.getDefaultState(), 3);
                            } else {
                                world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.BEARD_MOSS.getDefaultState().with(BeardMossBlock.HALF, DoubleBlockHalf.UPPER), 3);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void generate(StructureWorldAccess world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> updatedBlocks, BlockBox bounds) {

    }
}