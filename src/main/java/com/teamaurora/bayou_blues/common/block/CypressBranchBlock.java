package com.teamaurora.bayou_blues.common.block;

import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings("deprecation")
public class CypressBranchBlock extends Block implements Fertilizable {
    public static final IntProperty AGE = Properties.AGE_2;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE_NORTH = Block.createCuboidShape(4.0D, 4.0D, 3.0D, 12.0D, 12.0D, 16.0D);
    protected static final VoxelShape SHAPE_EAST = Block.createCuboidShape(0.0D, 4.0D, 4.0D, 13.0D, 12.0D, 12.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.createCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 13.0D);
    protected static final VoxelShape SHAPE_WEST = Block.createCuboidShape(3.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);

    public CypressBranchBlock(AbstractBlock.Settings properties) {
        super(properties);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(FACING, Direction.NORTH));
    }

    @Override
    public boolean isFertilizable(BlockView worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < 2;
    }

    @Override
    public boolean canGrow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1), 2);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            default:
            case NORTH:
                return SHAPE_NORTH;
            case EAST:
                return SHAPE_EAST;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 2;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int j = state.get(AGE);

        if (j < 2 && random.nextInt(10) == 0) {
            worldIn.setBlockState(pos, state.with(AGE, j+1), 2);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
        int i = state.get(AGE);
        boolean flag = i == 2;

        if (!flag && player.getStackInHand(handIn).getItem() == Items.BONE_MEAL) {
            return ActionResult.PASS;
        } else if (i > 1) {
            dropStack(worldIn, pos, new ItemStack(BayouBluesItems.GOOSEBERRIES, 1));
            worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
            worldIn.setBlockState(pos, state.with(AGE, 0), 2);
            return ActionResult.success(worldIn.isClient);
        } else {
            return super.onUse(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.offset(state.get(FACING), -1)).getBlock();
        return block == BayouBluesBlocks.CYPRESS_LOG || block == BayouBluesBlocks.CYPRESS_WOOD;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canPlaceAt(worldIn, currentPos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext context) {
        if (!context.canReplaceExisting()) {
            BlockState blockstate = context.getWorld().getBlockState(context.getBlockPos().offset(context.getSide().getOpposite()));
            if (blockstate.isOf(this) && blockstate.get(FACING) == context.getSide()) {
                return null;
            }
        }
        BlockState blockstate1 = this.getDefaultState();
        WorldView worldView = context.getWorld();
        BlockPos blockpos = context.getBlockPos();

        for(Direction direction : context.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate1 = blockstate1.with(FACING, direction.getOpposite());
                if (blockstate1.canPlaceAt(worldView, blockpos)) {
                    return blockstate1;
                }
            }
        }

        return null;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(FACING, AGE));
    }
}