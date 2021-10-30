package com.teamaurora.bayou_blues.common.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class DrinkItem extends Item {
    public DrinkItem(Item.Settings properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
        super.finishUsing(stack, world, livingEntity);
        if (livingEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)livingEntity;
            Criteria.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (livingEntity instanceof PlayerEntity && !((PlayerEntity)livingEntity).getAbilities().creativeMode) {
                ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
                PlayerEntity playerentity = (PlayerEntity)livingEntity;
                if (!playerentity.getInventory().insertStack(itemstack)) {
                    playerentity.dropItem(itemstack, false);
                }
            }

            return stack;
        }
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
}