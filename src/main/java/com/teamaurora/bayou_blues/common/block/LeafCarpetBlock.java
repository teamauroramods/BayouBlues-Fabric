package com.teamaurora.bayou_blues.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class LeafCarpetBlock extends Block {
    private static final VoxelShape SHAPE = createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public LeafCarpetBlock(Settings settings) {
        super(settings);
    }

    public boolean canReplace(BlockState state, ItemPlacementContext useContext) {
        return true;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockView world, @NotNull BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
}