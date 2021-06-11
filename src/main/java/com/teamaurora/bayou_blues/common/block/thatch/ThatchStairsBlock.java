package com.teamaurora.bayou_blues.common.block.thatch;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class ThatchStairsBlock extends StairsBlock {
    public ThatchStairsBlock(BlockState state, Settings settings) {
        super(state, settings);
    }

    @Environment(EnvType.CLIENT)
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean isTranslucent(BlockState state, BlockView reader, BlockPos pos) {
        return true;
    }
}
