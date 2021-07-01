package com.teamaurora.bayou_blues.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HangingCypressLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<HangingCypressLeavesTreeDecorator> CODEC;
    public static final HangingCypressLeavesTreeDecorator DECORATOR = new HangingCypressLeavesTreeDecorator();

    @Override
    protected TreeDecoratorType<?> getType() {
        return BayouBluesFeatures.HANGING_CYPRESS_LEAVES;
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void generate(StructureWorldAccess world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, BlockBox bounds) {
        for (BlockPos pos : leaves) {
            if (world.getBlockState(pos.down()).isAir()) {
                if (rand.nextInt(3) == 0) {
                    world.setBlockState(pos.down(), BayouBluesBlocks.HANGING_CYPRESS_LEAVES.getDefaultState(), 3);
                }
            }
        }
    }
}