package com.teamaurora.bayou_blues.core.registry;

import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

public class BayouBluesItems {
    public static final Item CYPRESS_BOAT = createBoatItem("cypress", BayouBluesBlocks.CYPRESS_PLANKS);

    public static final Item GOOSEBERRIES = createItem("gooseberries", new Item(new Item.Settings().food(Foods.GOOSEBERRIES).group(ItemGroup.FOOD)));
    public static final Item GOOSEBERRY_JUICE = createItem("gooseberry_juice", new DrinkItem(new Item.Settings().food(Foods.GOOSEBERRY_JUICE).maxCount(16).group(ItemGroup.FOOD)));
    public static final Item GOOSEBERRY_PIE = createItem("gooseberry_pie", new Item(new Item.Settings().food(Foods.GOOSEBERRY_PIE).group(ItemGroup.FOOD)));

    public static final Item HONEY_GLAZED_GOOSEBERRIES = createItem("honey_glazed_gooseberries", new Item(new Item.Settings().food(Foods.HONEY_GLAZED_GOOSEBERRIES).group(ItemGroup.FOOD)));
    public static final Item GOOSEBERRY_JAM = createItem("gooseberry_jam", new JamItem(new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE).food(Foods.GOOSEBERRY_JAM).maxCount(16).group(ItemGroup.FOOD)));
    public static final Item GOOSEBERRY_JAM_COOKIE = createItem("gooseberry_jam_cookie", new Item(new Item.Settings().food(FabricLoader.INSTANCE.isModLoaded("farmersdelight") ? Foods.GOOSEBERRY_JAM_COOKIE_FAST : Foods.GOOSEBERRY_JAM_COOKIE).group(ItemGroup.FOOD)));

    public static final Item ALGAE = createItem("algae", new AlgaeItem(BayouBluesBlocks.ALGAE, new Item.Settings().group(ItemGroup.DECORATIONS)));

    public static final Item BLUE_LILY = createItem("blue_lily", new LilyItem(BayouBluesBlocks.BLUE_LILY, BayouBluesBlocks.POTTED_BLUE_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));
    public static final Item LIGHT_GRAY_LILY = createItem("light_gray_lily", new LilyItem(BayouBluesBlocks.LIGHT_GRAY_LILY, BayouBluesBlocks.POTTED_LIGHT_GRAY_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));
    public static final Item CYAN_LILY = createItem("cyan_lily", new LilyItem(BayouBluesBlocks.CYAN_LILY, BayouBluesBlocks.POTTED_CYAN_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));
    public static final Item LIGHT_BLUE_LILY = createItem("light_blue_lily", new LilyItem(BayouBluesBlocks.LIGHT_BLUE_LILY, BayouBluesBlocks.POTTED_LIGHT_BLUE_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));
    public static final Item MAGENTA_LILY = createItem("magenta_lily", new LilyItem(BayouBluesBlocks.MAGENTA_LILY, BayouBluesBlocks.POTTED_MAGENTA_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));
    public static final Item PINK_LILY = createItem("pink_lily", new LilyItem(BayouBluesBlocks.PINK_LILY, BayouBluesBlocks.POTTED_PINK_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));
    public static final Item PURPLE_LILY = createItem("purple_lily", new LilyItem(BayouBluesBlocks.PURPLE_LILY, BayouBluesBlocks.POTTED_PURPLE_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));
    public static final Item WHITE_LILY = createItem("white_lily", new LilyItem(BayouBluesBlocks.WHITE_LILY, BayouBluesBlocks.POTTED_WHITE_LILY, new Item.Settings().group(ItemGroup.DECORATIONS)));

    public static class Foods {
        public static final FoodComponent GOOSEBERRIES = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.2F).build();
        public static final FoodComponent GOOSEBERRY_JUICE = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.1F).build();
        public static final FoodComponent GOOSEBERRY_PIE = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.5F).build();

        public static final FoodComponent HONEY_GLAZED_GOOSEBERRIES = (new FoodComponent.Builder()).hunger(7).saturationModifier(0.3F).build();
        public static final FoodComponent GOOSEBERRY_JAM = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.25F).build();
        public static final FoodComponent GOOSEBERRY_JAM_COOKIE = (new FoodComponent.Builder().hunger(2).saturationModifier(0.3F)).build();
        public static final FoodComponent GOOSEBERRY_JAM_COOKIE_FAST = (new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).snack()).build();
    }

    public static Item createItem(String id, Item item) {
        Registry.register(Registry.ITEM, BayouBlues.id(id), item);
        return item;
    }
}