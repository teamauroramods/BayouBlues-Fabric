package com.teamaurora.bayou_blues.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LilyFlowerBlock extends FlowerBlock {
    protected static final VoxelShape LILY_PAD_AABB = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);
    protected static final VoxelShape LILY_FLOWER_AABB = Block.createCuboidShape(3.0D, 1.5D, 3.0D, 13.0D, 13.D, 13.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.union(LILY_PAD_AABB, LILY_FLOWER_AABB);
    private final Item item;

    public static List<LilyFlowerBlock> LILY_FLOWERS = new ArrayList<>();

    public LilyFlowerBlock(Item item, Settings builder) {
        super(StatusEffects.POISON, 12, builder);
        this.item = item;
        LILY_FLOWERS.add(this);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return LILY_PAD_AABB;
    }

    @Override
    public ItemStack getPickStack(BlockView worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.item);
    }

    public static Block getRandomLily(Random rand) {
        return LILY_FLOWERS.get(rand.nextInt(LILY_FLOWERS.size()));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.canPlaceAt(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
        FluidState fluidstate = worldIn.getFluidState(pos.down());
        FluidState fluidstate1 = worldIn.getFluidState(pos);
        return (fluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getFluid() == Fluids.EMPTY;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (worldIn instanceof ServerWorld && entityIn instanceof BoatEntity) {
            worldIn.breakBlock(new BlockPos(pos), true, entityIn);
        }

    }

    @Override
    protected boolean canPlantOnTop(BlockState state, BlockView worldIn, BlockPos pos) {
        FluidState fluidstate = worldIn.getFluidState(pos);
        FluidState fluidstate1 = worldIn.getFluidState(pos.up());
        return (fluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getFluid() == Fluids.EMPTY;
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return OffsetType.NONE;
    }
}