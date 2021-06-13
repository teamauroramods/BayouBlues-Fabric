package com.teamaurora.bayou_blues.common.util;

import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public final class TreeUtil {
    public static void placeDirectionalLogAt(ModifiableWorld world, BlockPos pos, Direction direction, Random rand, TreeFeatureConfig config) {
        setForcedState(world, pos, config.trunkProvider.getBlockState(rand, pos).with(PillarBlock.AXIS, direction.getAxis()));
    }

    public static void placeLeafAt(ModifiableTestableWorld world, BlockPos pos, Random rand, TreeFeatureConfig config) {
        if (isAirOrLeaves(world, pos)) {
            setForcedState(world, pos, config.leavesProvider.getBlockState(rand, pos).with(LeavesBlock.DISTANCE, 1));
        }
    }

    public static void setForcedState(ModifiableWorld world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, 18);
    }

    public static boolean isAir(TestableWorld world, BlockPos pos) {
        if (!(world instanceof BlockView)) {
            return world.testBlockState(pos, BlockState::isAir);
        } else {
            return world.testBlockState(pos, AbstractBlock.AbstractBlockState::isAir);
        }
    }

    public static boolean isAirOrLeaves(TestableWorld world, BlockPos pos) {
        if (world instanceof WorldView) {
            return world.testBlockState(pos, state -> state.isAir() || state.isIn(BlockTags.LEAVES));
        }
        return world.testBlockState(pos, (state) -> isAir(world, pos) || state.isIn(BlockTags.LEAVES));
    }

    public static void setDirtAt(WorldAccess world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        if (block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 18);
        }
    }

    public static boolean isValidGround(WorldAccess world, BlockPos pos) {
        return world.getBlockState(pos).canPlaceAt(world, pos);
    }
}