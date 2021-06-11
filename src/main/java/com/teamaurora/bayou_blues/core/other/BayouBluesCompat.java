package com.teamaurora.bayou_blues.core.other;

import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesItems;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;

public class BayouBluesCompat {
    public static void registerCompostables() {
        ComposterBlock.registerCompostableItem(0.3F, BayouBluesBlocks.CYPRESS_LEAVES);
        ComposterBlock.registerCompostableItem(0.3F, BayouBluesBlocks.CYPRESS_SAPLING);
        ComposterBlock.registerCompostableItem(0.3F, BayouBluesBlocks.CYPRESS_LEAF_CARPET);

        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.GOOSEBERRIES);

        ComposterBlock.registerCompostableItem(0.15F, BayouBluesItems.ALGAE);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesBlocks.ALGAE_THATCH);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesBlocks.ALGAE_THATCH_SLAB);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesBlocks.ALGAE_THATCH_STAIRS);

        ComposterBlock.registerCompostableItem(0.3F, BayouBluesBlocks.BEARD_MOSS);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesBlocks.BEARD_MOSS_BLOCK);

        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.BLUE_LILY);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.LIGHT_BLUE_LILY);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.CYAN_LILY);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.LIGHT_GRAY_LILY);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.WHITE_LILY);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.MAGENTA_LILY);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.PINK_LILY);
        ComposterBlock.registerCompostableItem(0.65F, BayouBluesItems.PURPLE_LILY);
    }

    public static void registerFlammables() {
        FireBlock fire = (FireBlock) Blocks.FIRE;

        fire.registerFlammableBlock(BayouBluesBlocks.HANGING_CYPRESS_LEAVES, 30, 60);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_LOG, 5, 5);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_WOOD, 5, 5);
        fire.registerFlammableBlock(BayouBluesBlocks.STRIPPED_CYPRESS_LOG, 5, 5);
        fire.registerFlammableBlock(BayouBluesBlocks.STRIPPED_CYPRESS_WOOD, 5, 5);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_PLANKS, 5, 20);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_SLAB, 5, 20);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_STAIRS, 5, 20);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_FENCE, 5, 20);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_FENCE_GATE, 5, 20);
        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_LEAF_CARPET, 30, 60);

        fire.registerFlammableBlock(BayouBluesBlocks.CYPRESS_BRANCH, 60, 100);

        fire.registerFlammableBlock(BayouBluesBlocks.BEARD_MOSS, 15, 100);
        fire.registerFlammableBlock(BayouBluesBlocks.BEARD_MOSS_BLOCK, 15, 100);

        fire.registerFlammableBlock(BayouBluesBlocks.ALGAE_THATCH, 60, 20);
        fire.registerFlammableBlock(BayouBluesBlocks.ALGAE_THATCH_SLAB, 60, 20);
        fire.registerFlammableBlock(BayouBluesBlocks.ALGAE_THATCH_STAIRS, 60, 20);
    }
}