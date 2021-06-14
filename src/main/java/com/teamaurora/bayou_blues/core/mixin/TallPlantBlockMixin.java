package com.teamaurora.bayou_blues.core.mixin;

import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(TallPlantBlock.class)
public class TallPlantBlockMixin implements Fertilizable {
    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (state.getBlock() == Blocks.LARGE_FERN) {
            BlockState blockState = BayouBluesBlocks.GIANT_FERN.getDefaultState();
            BlockState blockState2 = blockState.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER);
            BlockPos blockPos = pos.up();
            if (world.getBlockState(blockPos).isOf(Blocks.AIR)) {
                world.setBlockState(pos, blockState, 3);
                world.setBlockState(blockPos, blockState2, 3);
            }
        }
    }
}