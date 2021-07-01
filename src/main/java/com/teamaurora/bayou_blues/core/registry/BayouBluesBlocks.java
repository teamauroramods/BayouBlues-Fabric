package com.teamaurora.bayou_blues.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamaurora.bayou_blues.common.block.*;
import com.teamaurora.bayou_blues.common.block.thatch.ThatchBlock;
import com.teamaurora.bayou_blues.common.block.thatch.ThatchSlabBlock;
import com.teamaurora.bayou_blues.common.block.thatch.ThatchStairsBlock;
import com.teamaurora.bayou_blues.common.block.wood.StrippedLogBlock;
import com.teamaurora.bayou_blues.common.item.FuelBlockItem;
import com.teamaurora.bayou_blues.core.BayouBlues;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import com.teamaurora.bayou_blues.common.block.trees.CypressTree;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class BayouBluesBlocks {
    // cypress
    public static final Block STRIPPED_CYPRESS_LOG = createBlock("stripped_cypress_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
    public static final Block STRIPPED_CYPRESS_WOOD = createBlock("stripped_cypress_wood", new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYPRESS_LOG= createBlock("cypress_log", new StrippedLogBlock(STRIPPED_CYPRESS_LOG, FabricBlockSettings.copy(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYPRESS_WOOD = createBlock("cypress_wood", new StrippedLogBlock(STRIPPED_CYPRESS_WOOD, FabricBlockSettings.copy(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYPRESS_LEAVES = createBlock("cypress_leaves", new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning((BlockState state, BlockView reader, BlockPos pos, EntityType<?> entity)->(entity == EntityType.OCELOT || entity == EntityType.PARROT)).suffocates((BlockState state, BlockView reader, BlockPos pos)->false).blockVision((BlockState state, BlockView reader, BlockPos pos)->false)), ItemGroup.DECORATIONS);
    public static final Block CYPRESS_SAPLING = createBlock("cypress_sapling", new SaplingBlock(new CypressTree(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
    public static final Block POTTED_CYPRESS_SAPLING = createBlockNoItem("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block CYPRESS_PLANKS = createBlock("cypress_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYPRESS_SLAB = createBlock("cypress_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYPRESS_STAIRS = createBlock("cypress_stairs", new StairsBlock(CYPRESS_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYPRESS_PRESSURE_PLATE = createBlock("cypress_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
    public static final Block CYPRESS_FENCE = createFuelBlock("cypress_fence", new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE)), 300, ItemGroup.DECORATIONS);
    public static final Block CYPRESS_FENCE_GATE = createFuelBlock("cypress_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE)), 300, ItemGroup.REDSTONE);
    public static final Block CYPRESS_BUTTON = createBlock("cypress_button", new WoodenButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
    public static final Block CYPRESS_DOOR = createBlock("cypress_door", new DoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
    public static final Block CYPRESS_TRAPDOOR = createBlock("cypress_trapdoor", new TrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.REDSTONE);
    public static final Pair<TerraformSignBlock, TerraformWallSignBlock> CYPRESS_SIGNS = createSignBlock("cypress", Pair.of(new TerraformSignBlock(BayouBlues.id("entity/signs/cypress"), FabricBlockSettings.copyOf(Blocks.OAK_SIGN).materialColor(MapColor.TERRACOTTA_PURPLE).breakByTool(FabricToolTags.AXES)), new TerraformWallSignBlock(BayouBlues.id("entity/signs/cypress"), FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).materialColor(MapColor.TERRACOTTA_PURPLE).breakByTool(FabricToolTags.AXES))));

    public static final Block CYPRESS_LEAF_CARPET = createBlock("cypress_leaf_carpet", new LeafCarpetBlock(FabricBlockSettings.copy(CYPRESS_LEAVES)), ItemGroup.DECORATIONS);
    public static final Block HANGING_CYPRESS_LEAVES = createBlock("hanging_cypress_leaves", new HangingCypressLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).breakByTool(FabricToolTags.SHEARS).breakByTool(FabricToolTags.HOES)), ItemGroup.DECORATIONS);

    public static final Block CYPRESS_KNEE = createBlock("cypress_knee", new CypressKneeBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ItemGroup.DECORATIONS);
    public static final Block LARGE_CYPRESS_KNEE = createBlock("large_cypress_knee", new DoubleCypressKneeBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ItemGroup.DECORATIONS);

    // gooseberries
    public static final Block CYPRESS_BRANCH = createBlock("cypress_branch", new CypressBranchBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.BAMBOO_SAPLING).breakByTool(FabricToolTags.SHEARS).breakByTool(FabricToolTags.HOES)), ItemGroup.DECORATIONS);

    // lilies
    public static final Block BLUE_LILY = createBlockNoItem("blue_lily", new LilyFlowerBlock(BayouBluesItems.BLUE_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));
    public static final Block LIGHT_GRAY_LILY = createBlockNoItem("light_gray_lily", new LilyFlowerBlock(BayouBluesItems.LIGHT_GRAY_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));
    public static final Block CYAN_LILY = createBlockNoItem("cyan_lily", new LilyFlowerBlock(BayouBluesItems.CYAN_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));
    public static final Block LIGHT_BLUE_LILY = createBlockNoItem("light_blue_lily", new LilyFlowerBlock(BayouBluesItems.LIGHT_BLUE_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));
    public static final Block MAGENTA_LILY = createBlockNoItem("magenta_lily", new LilyFlowerBlock(BayouBluesItems.MAGENTA_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));
    public static final Block PINK_LILY = createBlockNoItem("pink_lily", new LilyFlowerBlock(BayouBluesItems.PINK_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));
    public static final Block PURPLE_LILY = createBlockNoItem("purple_lily", new LilyFlowerBlock(BayouBluesItems.PURPLE_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));
    public static final Block WHITE_LILY = createBlockNoItem("white_lily", new LilyFlowerBlock(BayouBluesItems.WHITE_LILY, FabricBlockSettings.copy(Blocks.LILY_PAD)));

    public static final Block POTTED_BLUE_LILY = createBlockNoItem("potted_blue_lily", new FlowerPotBlock(BLUE_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block POTTED_LIGHT_GRAY_LILY = createBlockNoItem("potted_light_gray_lily", new FlowerPotBlock(LIGHT_GRAY_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block POTTED_CYAN_LILY = createBlockNoItem("potted_cyan_lily", new FlowerPotBlock(CYAN_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block POTTED_LIGHT_BLUE_LILY = createBlockNoItem("potted_light_blue_lily", new FlowerPotBlock(LIGHT_BLUE_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block POTTED_MAGENTA_LILY = createBlockNoItem("potted_magenta_lily", new FlowerPotBlock(MAGENTA_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block POTTED_PINK_LILY = createBlockNoItem("potted_pink_lily", new FlowerPotBlock(PINK_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block POTTED_PURPLE_LILY = createBlockNoItem("potted_purple_lily", new FlowerPotBlock(PURPLE_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block POTTED_WHITE_LILY = createBlockNoItem("potted_white_lily", new FlowerPotBlock(WHITE_LILY, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));

    // algae
    public static final Block ALGAE = createBlockNoItem("algae",  new AlgaeBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().sounds(BlockSoundGroup.LILY_PAD).nonOpaque().noCollision()));
    public static final Block ALGAE_THATCH = createBlock("algae_thatch",  new ThatchBlock(Settings.ALGAE_THATCH), ItemGroup.BUILDING_BLOCKS);
    public static final Block ALGAE_THATCH_SLAB = createBlock("algae_thatch_slab",  new ThatchSlabBlock(Settings.ALGAE_THATCH), ItemGroup.BUILDING_BLOCKS);
    public static final Block ALGAE_THATCH_STAIRS = createBlock("algae_thatch_stairs",  new ThatchStairsBlock(ALGAE_THATCH.getDefaultState(), Settings.ALGAE_THATCH), ItemGroup.BUILDING_BLOCKS);

    // other
    public static final Block BEARD_MOSS_BLOCK = createFuelBlock("beard_moss_block", new BeardMossBlockBlock(FabricBlockSettings.of(Material.PLANT).strength(0.1F).sounds(BlockSoundGroup.GRASS).nonOpaque()), 800, ItemGroup.DECORATIONS);
    public static final Block BEARD_MOSS = createFuelBlock("beard_moss", new BeardMossBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision().ticksRandomly()), 800, ItemGroup.DECORATIONS);
    public static final Block GIANT_FERN = createBlock("giant_fern", new TallPlantBlock(FabricBlockSettings.copy(Blocks.TALL_GRASS)), ItemGroup.DECORATIONS);

    public static class Settings {
        public static final FabricBlockSettings ALGAE_THATCH = FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.LIME).strength(0.5F).sounds(BlockSoundGroup.GRASS).nonOpaque().breakByTool(FabricToolTags.HOES);
    }

    public static Block createBlock(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, BayouBlues.id(id), block);
        Registry.register(Registry.ITEM, BayouBlues.id(id), new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }

    public static Block createBlockNoItem(String id, Block block) {
        Registry.register(Registry.BLOCK, BayouBlues.id(id), block);
        return block;
    }

    public static <B extends Block> B createFuelBlock(String name, B block, int burnTime, @Nullable ItemGroup group) {
        createBlockNoItem(name, block);
        BayouBluesItems.createItem(name, new FuelBlockItem(block, burnTime, (new Item.Settings()).group(group)));
        return block;
    }

    public static Pair<TerraformSignBlock, TerraformWallSignBlock> createSignBlock(String name, Pair<TerraformSignBlock, TerraformWallSignBlock> signBlockPair) {
        SignBlock sign = signBlockPair.getFirst();
        WallSignBlock wallSign = signBlockPair.getSecond();

        createBlockNoItem(name + "_sign", sign);
        createBlockNoItem(name + "_wall_sign", wallSign);

        BayouBluesItems.registerSignItem(name + "_sign", sign, wallSign, new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS));

        return signBlockPair;
    }
}