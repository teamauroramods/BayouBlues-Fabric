package com.teamaurora.bayou_blues.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class FuelBlockItem extends BlockItem {
    private int burnTime;

    public FuelBlockItem(Block block, int burnTimeIn, Settings settings) {
        super(block, settings);
        this.burnTime = burnTimeIn;
    }

    public int getBurnTime(ItemStack itemStack) {
        return this.burnTime;
    }
}