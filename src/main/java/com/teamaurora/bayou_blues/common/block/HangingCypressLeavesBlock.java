package com.teamaurora.bayou_blues.common.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class HangingCypressLeavesBlock extends Block {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 4.0, 1.0, 15.0, 16.0, 15.0);

    public HangingCypressLeavesBlock(AbstractBlock.Settings properties) {
        super(properties);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView reader, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean moving) {
        if (world.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
            world.removeBlock(pos, false);
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return true;
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos.up()).getBlock().getDefaultState();

        if (state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.LEAVES)) {
            return this.getDefaultState();
        } else {
            return null;
        }
    }
}