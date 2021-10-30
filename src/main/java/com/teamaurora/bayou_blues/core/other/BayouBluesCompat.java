package com.teamaurora.bayou_blues.core.other;

import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesItems;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.ItemConvertible;

public class BayouBluesCompat {
    public static void init() {
        registerCompostables();
        registerFlammables();
    }

    public static void registerCompostables() {
        registerCompostableItem(0.3F, BayouBluesBlocks.CYPRESS_LEAVES);
        registerCompostableItem(0.3F, BayouBluesBlocks.CYPRESS_SAPLING);
        registerCompostableItem(0.3F, BayouBluesBlocks.CYPRESS_LEAF_CARPET);

        registerCompostableItem(0.65F, BayouBluesItems.GOOSEBERRIES);

        registerCompostableItem(0.15F, BayouBluesItems.ALGAE);
        registerCompostableItem(0.65F, BayouBluesBlocks.ALGAE_THATCH);
        registerCompostableItem(0.65F, BayouBluesBlocks.ALGAE_THATCH_SLAB);
        registerCompostableItem(0.65F, BayouBluesBlocks.ALGAE_THATCH_STAIRS);

        registerCompostableItem(0.3F, BayouBluesBlocks.BEARD_MOSS);
        registerCompostableItem(0.65F, BayouBluesBlocks.BEARD_MOSS_BLOCK);

        registerCompostableItem(0.65F, BayouBluesItems.BLUE_LILY);
        registerCompostableItem(0.65F, BayouBluesItems.LIGHT_BLUE_LILY);
        registerCompostableItem(0.65F, BayouBluesItems.CYAN_LILY);
        registerCompostableItem(0.65F, BayouBluesItems.LIGHT_GRAY_LILY);
        registerCompostableItem(0.65F, BayouBluesItems.WHITE_LILY);
        registerCompostableItem(0.65F, BayouBluesItems.MAGENTA_LILY);
        registerCompostableItem(0.65F, BayouBluesItems.PINK_LILY);
        registerCompostableItem(0.65F, BayouBluesItems.PURPLE_LILY);
    }

    public static void registerFlammables() {
        registerFlammableBlock(BayouBluesBlocks.HANGING_CYPRESS_LEAVES, 30, 60);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_LOG, 5, 5);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_WOOD, 5, 5);
        registerFlammableBlock(BayouBluesBlocks.STRIPPED_CYPRESS_LOG, 5, 5);
        registerFlammableBlock(BayouBluesBlocks.STRIPPED_CYPRESS_WOOD, 5, 5);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_PLANKS, 5, 20);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_SLAB, 5, 20);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_STAIRS, 5, 20);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_FENCE, 5, 20);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_FENCE_GATE, 5, 20);
        registerFlammableBlock(BayouBluesBlocks.CYPRESS_LEAF_CARPET, 30, 60);

        registerFlammableBlock(BayouBluesBlocks.CYPRESS_BRANCH, 60, 100);

        registerFlammableBlock(BayouBluesBlocks.BEARD_MOSS, 15, 100);
        registerFlammableBlock(BayouBluesBlocks.BEARD_MOSS_BLOCK, 15, 100);

        registerFlammableBlock(BayouBluesBlocks.ALGAE_THATCH, 60, 20);
        registerFlammableBlock(BayouBluesBlocks.ALGAE_THATCH_SLAB, 60, 20);
        registerFlammableBlock(BayouBluesBlocks.ALGAE_THATCH_STAIRS, 60, 20);
    }

    private static void registerCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        CompostingChanceRegistry.INSTANCE.add(item.asItem(), levelIncreaseChance);
    }

    private static void registerFlammableBlock(Block block, int burnChance, int spreadChance) {
        FlammableBlockRegistry.getDefaultInstance().add(block, burnChance, spreadChance);
    }
}