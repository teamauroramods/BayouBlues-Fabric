package com.teamaurora.bayou_blues.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import java.util.Random;

@SuppressWarnings("deprecation")
public class AlgaeBlock extends PlantBlock implements Fertilizable {
    protected static final VoxelShape ALGAE_AABB = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public AlgaeBlock(AbstractBlock.Settings builder) {
        super(builder);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return ALGAE_AABB;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
        //if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        //    return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
        FluidState fluidstate = worldIn.getFluidState(pos.down());
        FluidState fluidstate1 = worldIn.getFluidState(pos);
        return (fluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getFluid() == Fluids.EMPTY;
    }

    @Override
    public boolean isFertilizable(BlockView worldIn, BlockPos pos, BlockState state, boolean isClient) {
        for (BlockPos pos2 : BlockPos.iterate(pos.add(-2, -2, -2), pos.add(2, 2, 2))) {
            if (pos.isWithinDistance(pos2, 2.0F)) {
                if (worldIn.getBlockState(pos2).isAir() && worldIn.getFluidState(pos2.down()).getFluid() == Fluids.WATER) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canGrow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        for (BlockPos pos2 : BlockPos.iterate(pos.add(-3, -3, -3), pos.add(3, 3, 3))) {
            if (pos.isWithinDistance(pos2, 3.0F)) {
                if (rand.nextFloat() <= 1.0F - pos2.getSquaredDistance(pos)/18.0F) {
                    if (worldIn.getBlockState(pos2).isAir() && worldIn.getFluidState(pos2.down()).getFluid() == Fluids.WATER) {
                        worldIn.setBlockState(pos2, this.getDefaultState());
                    }
                }
            }
        }
    }
}