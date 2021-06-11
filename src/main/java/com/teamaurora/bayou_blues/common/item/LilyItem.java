package com.teamaurora.bayou_blues.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class LilyItem extends BlockItem {
    private final Block pottedFlower;

    public LilyItem(Block flower, Block pottedFlower, Settings properties) {
        super(flower, properties);
        this.pottedFlower = pottedFlower;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() == Blocks.LILY_PAD) {
            world.setBlockState(pos, super.getBlock().getDefaultState());
            ItemStack stack = context.getStack();
            stack.decrement(1);
            BlockSoundGroup soundtype = BlockSoundGroup.LILY_PAD;

            // Never know what other mods might do; player could be null
            if (context.getPlayer() != null) {
                PlayerEntity playerentity = context.getPlayer();
                world.playSound(playerentity, pos, this.getPlaceSound(state, world, pos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                playerentity.swingHand(context.getHand());
            }
            return ActionResult.CONSUME;
        } else if (state.getBlock() == Blocks.FLOWER_POT) {
            world.setBlockState(pos, pottedFlower.getDefaultState());
            ItemStack stack = context.getStack();
            stack.decrement(1);
            BlockSoundGroup soundtype = BlockSoundGroup.LILY_PAD;

            // Never know what other mods might do; player could be null
            if (context.getPlayer() != null) {
                PlayerEntity playerentity = context.getPlayer();
                world.playSound(playerentity, pos, this.getPlaceSound(state, world, pos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                playerentity.swingHand(context.getHand());
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
        return state.getSoundType(world, pos, entity).getPlaceSound();
    }
}
