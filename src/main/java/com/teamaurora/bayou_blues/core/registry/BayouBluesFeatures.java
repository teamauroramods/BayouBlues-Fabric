package com.teamaurora.bayou_blues.core.registry;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.world.gen.feature.*;
import com.teamaurora.bayou_blues.common.world.gen.treedecorator.*;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.placer.DoublePlantPlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class BayouBluesFeatures {
    public static final Feature<TreeFeatureConfig> CYPRESS_TREE = registerFeature("cypress_tree", new CypressFeature(TreeFeatureConfig.CODEC));
    public static final Feature<TreeFeatureConfig> MEGA_CYPRESS_TREE = registerFeature("mega_cypress_tree", new MegaCypressFeature(TreeFeatureConfig.CODEC));
    public static final Feature<TreeFeatureConfig> WATER_CYPRESS_TREE = registerFeature("water_cypress_tree", new WaterCypressFeature(TreeFeatureConfig.CODEC));
    public static final Feature<TreeFeatureConfig> WATER_MEGA_CYPRESS_TREE = registerFeature("water_mega_cypress_tree", new WaterMegaCypressFeature(TreeFeatureConfig.CODEC));

    public static final Feature<SingleStateFeatureConfig> LARGE_PATCH = registerFeature("large_patch", new LargePatchFeature(SingleStateFeatureConfig.CODEC));
    public static final Feature<SingleStateFeatureConfig> LARGE_LAND_PATCH = registerFeature("large_land_patch", new LargeLandPatchFeature(SingleStateFeatureConfig.CODEC));

    public static final Feature<DefaultFeatureConfig> PODZOL_PATCH = registerFeature("podzol_patch", new PodzolPatchFeature(DefaultFeatureConfig.CODEC));

    public static final TreeDecoratorType<?> HANGING_CYPRESS_LEAVES = registerTreeDecor("hanging_cypress_leaves", HangingCypressLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> CYPRESS_KNEES = registerTreeDecor("cypress_knees", CypressKneesTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> SPARSE_CYPRESS_KNEES = registerTreeDecor("sparse_cypress_knees", SparseCypressKneesTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> CYPRESS_BRANCH = registerTreeDecor("cypress_branch", CypressBranchTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> BEARD_MOSS = registerTreeDecor("beard_moss", BeardMossTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> SPARSE_LEAVE_VINE = registerTreeDecor("sparse_leave_vine", SparseLeaveVineTreeDecorator.CODEC);

    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        Registry.register(Registry.FEATURE, BayouBlues.id(name), feature);
        return feature;
    }

    private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecor(String id, Codec<P> codec) {
        return Registry.register(Registry.TREE_DECORATOR_TYPE, BayouBlues.id(id), new TreeDecoratorType<>(codec));
    }

    public static final class BlockStates {
        public static final BlockState CYPRESS_LOG = BayouBluesBlocks.CYPRESS_LOG.getDefaultState();
        public static final BlockState CYPRESS_LEAVES = BayouBluesBlocks.CYPRESS_LEAVES.getDefaultState();
        public static final BlockState CYPRESS_SAPLING = BayouBluesBlocks.CYPRESS_SAPLING.getDefaultState();
        public static final BlockState HANGING_CYPRESS_LEAVES = BayouBluesBlocks.HANGING_CYPRESS_LEAVES.getDefaultState();

        public static final BlockState ALGAE = BayouBluesBlocks.ALGAE.getDefaultState();
        public static final BlockState CYPRESS_LEAF_CARPET = BayouBluesBlocks.CYPRESS_LEAF_CARPET.getDefaultState();

        public static final BlockState BLUE_LILY = BayouBluesBlocks.BLUE_LILY.getDefaultState();
        public static final BlockState CYAN_LILY = BayouBluesBlocks.CYAN_LILY.getDefaultState();
        public static final BlockState LIGHT_BLUE_LILY = BayouBluesBlocks.LIGHT_BLUE_LILY.getDefaultState();
        public static final BlockState LIGHT_GRAY_LILY = BayouBluesBlocks.LIGHT_GRAY_LILY.getDefaultState();
        public static final BlockState WHITE_LILY = BayouBluesBlocks.WHITE_LILY.getDefaultState();
        public static final BlockState PINK_LILY = BayouBluesBlocks.PINK_LILY.getDefaultState();
        public static final BlockState MAGENTA_LILY = BayouBluesBlocks.MAGENTA_LILY.getDefaultState();
        public static final BlockState PURPLE_LILY = BayouBluesBlocks.PURPLE_LILY.getDefaultState();
        public static final BlockState LILY_PAD = Blocks.LILY_PAD.getDefaultState();

        public static final BlockState GRASS = Blocks.GRASS.getDefaultState();
        public static final BlockState TALL_GRASS = Blocks.TALL_GRASS.getDefaultState();
        public static final BlockState FERN = Blocks.FERN.getDefaultState();
        public static final BlockState LARGE_FERN = Blocks.LARGE_FERN.getDefaultState();
        public static final BlockState GIANT_FERN = BayouBluesBlocks.GIANT_FERN.getDefaultState();
    }

    public static final class Configs {
        public static final TreeFeatureConfig CYPRESS_TREE_CONFIG_GROWN = (new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new StraightTrunkPlacer(0, 0, 0),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_SAPLING),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),
                new TwoLayersFeatureSize(0, 0, 0)
        )).ignoreVines().decorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR)).build();

        public static final TreeFeatureConfig CYPRESS_TREE_CONFIG = (new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new StraightTrunkPlacer(0, 0, 0),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_SAPLING),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),
                new TwoLayersFeatureSize(0, 0, 0)
        )).ignoreVines().decorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, SparseCypressKneesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR, SparseLeaveVineTreeDecorator.DECORATOR, BeardMossTreeDecorator.DECORATOR)).build();

        public static final TreeFeatureConfig CYPRESS_KNEE_TREE_CONFIG = (new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new StraightTrunkPlacer(0, 0, 0),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_SAPLING),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),
                new TwoLayersFeatureSize(0, 0, 0)
        )).ignoreVines().decorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, CypressKneesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR, SparseLeaveVineTreeDecorator.DECORATOR, BeardMossTreeDecorator.DECORATOR)).build();

        public static final TreeFeatureConfig WATER_CYPRESS_TREE_CONFIG = (new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new StraightTrunkPlacer(0, 0, 0),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_SAPLING),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),
                new TwoLayersFeatureSize(0, 0, 0)
        )).ignoreVines().maxWaterDepth(3).decorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, SparseCypressKneesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR, SparseLeaveVineTreeDecorator.DECORATOR, BeardMossTreeDecorator.DECORATOR)).build();

        public static final TreeFeatureConfig WATER_CYPRESS_KNEE_TREE_CONFIG = (new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new StraightTrunkPlacer(0, 0, 0),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_SAPLING),
                new BlobFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), 0),
                new TwoLayersFeatureSize(0, 0, 0)
        )).ignoreVines().maxWaterDepth(3).decorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR, SparseLeaveVineTreeDecorator.DECORATOR, CypressKneesTreeDecorator.DECORATOR, BeardMossTreeDecorator.DECORATOR)).build();

        public static final TreeFeatureConfig CYPRESS_BUSH_CONFIG = (new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_SAPLING),
                new BushFoliagePlacer(ConstantIntProvider.create(2),ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0)
        )).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build();

        public static final SingleStateFeatureConfig ALGAE_PATCH_CONFIG = new SingleStateFeatureConfig(BlockStates.ALGAE);
        public static final SingleStateFeatureConfig CYPRESS_LEAF_CARPET_PATCH_CONFIG = new SingleStateFeatureConfig(BlockStates.CYPRESS_LEAF_CARPET);

        public static final RandomPatchFeatureConfig PATCH_LILY_COOL_CONFIG = (new RandomPatchFeatureConfig.Builder(new WeightedBlockStateProvider().addState(BlockStates.BLUE_LILY, 2).addState(BlockStates.CYAN_LILY, 2).addState(BlockStates.LIGHT_BLUE_LILY, 2).addState(BlockStates.LILY_PAD, 5), SimpleBlockPlacer.INSTANCE)).spreadX(5).spreadZ(5).tries(15).build();
        public static final RandomPatchFeatureConfig PATCH_LILY_NEUTRAL_CONFIG = (new RandomPatchFeatureConfig.Builder(new WeightedBlockStateProvider().addState(BlockStates.LIGHT_GRAY_LILY, 3).addState(BlockStates.WHITE_LILY, 3).addState(BlockStates.LILY_PAD, 5), SimpleBlockPlacer.INSTANCE)).spreadX(5).spreadZ(5).tries(15).build();
        public static final RandomPatchFeatureConfig PATCH_LILY_WARM_CONFIG = (new RandomPatchFeatureConfig.Builder(new WeightedBlockStateProvider().addState(BlockStates.PINK_LILY, 2).addState(BlockStates.MAGENTA_LILY, 2).addState(BlockStates.PURPLE_LILY, 2).addState(BlockStates.LILY_PAD, 5), SimpleBlockPlacer.INSTANCE)).spreadX(5).spreadZ(5).tries(15).build();

        public static final RandomPatchFeatureConfig GRASS_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockStates.GRASS), SimpleBlockPlacer.INSTANCE)).tries(80).cannotProject().build();
        public static final RandomPatchFeatureConfig TALL_GRASS_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockStates.TALL_GRASS), new DoublePlantPlacer())).spreadX(11).spreadZ(11).tries(150).cannotProject().build();
        public static final RandomPatchFeatureConfig FERN_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockStates.FERN), SimpleBlockPlacer.INSTANCE)).tries(80).cannotProject().build();
        public static final RandomPatchFeatureConfig LARGE_FERN_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockStates.LARGE_FERN), new DoublePlantPlacer())).spreadX(7).spreadZ(7).tries(110).cannotProject().build();
        public static final RandomPatchFeatureConfig GIANT_FERN_CONFIG = (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockStates.GIANT_FERN), new DoublePlantPlacer())).tries(110).cannotProject().build();

    }

    public static final class Configured {
        public static final ConfiguredFeature<TreeFeatureConfig, ?> CYPRESS_GROWN = BayouBluesFeatures.CYPRESS_TREE.configure(Configs.CYPRESS_TREE_CONFIG_GROWN);
        public static final ConfiguredFeature<TreeFeatureConfig, ?> MEGA_CYPRESS_GROWN = BayouBluesFeatures.MEGA_CYPRESS_TREE.configure(Configs.CYPRESS_TREE_CONFIG_GROWN);
        public static final ConfiguredFeature<TreeFeatureConfig, ?> CYPRESS = BayouBluesFeatures.CYPRESS_TREE.configure(Configs.CYPRESS_TREE_CONFIG);
        public static final ConfiguredFeature<TreeFeatureConfig, ?> MEGA_CYPRESS = BayouBluesFeatures.MEGA_CYPRESS_TREE.configure(Configs.CYPRESS_TREE_CONFIG);
        public static final ConfiguredFeature<TreeFeatureConfig, ?> MEGA_CYPRESS_KNEES = BayouBluesFeatures.MEGA_CYPRESS_TREE.configure(Configs.CYPRESS_KNEE_TREE_CONFIG);
        public static final ConfiguredFeature<TreeFeatureConfig, ?> WATER_CYPRESS = BayouBluesFeatures.WATER_CYPRESS_TREE.configure(Configs.WATER_CYPRESS_TREE_CONFIG);
        public static final ConfiguredFeature<TreeFeatureConfig, ?> WATER_MEGA_CYPRESS = BayouBluesFeatures.WATER_MEGA_CYPRESS_TREE.configure(Configs.WATER_CYPRESS_KNEE_TREE_CONFIG);

        public static final ConfiguredFeature<?, ?> CYPRESS_BUSH = Feature.TREE.configure(Configs.CYPRESS_BUSH_CONFIG);

        public static final ConfiguredFeature<?, ?> ALGAE = BayouBluesFeatures.LARGE_PATCH.configure(Configs.ALGAE_PATCH_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(2)));
        public static final ConfiguredFeature<?, ?> FALLEN_CYPRESS_LEAVES = BayouBluesFeatures.LARGE_LAND_PATCH.configure(Configs.CYPRESS_LEAF_CARPET_PATCH_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(6)));

        public static final ConfiguredFeature<?, ?> PODZOL = BayouBluesFeatures.PODZOL_PATCH.configure(DefaultFeatureConfig.INSTANCE).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(1, 0.1F, 1)));

        public static final ConfiguredFeature<?, ?> TREES_BAYOU = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(CYPRESS_BUSH.withChance(0.35F), MEGA_CYPRESS_KNEES.withChance(0.333333334F)), CYPRESS)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(20, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> TREES_BAYOU_WATER = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(WATER_MEGA_CYPRESS.withChance(0.333333334F)), WATER_CYPRESS)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(9, 0.1F, 1)));

        public static final ConfiguredFeature<?, ?> PATCH_GRASS = Feature.RANDOM_PATCH.configure(Configs.GRASS_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(2)));
        public static final ConfiguredFeature<?, ?> PATCH_TALL_GRASS = Feature.RANDOM_PATCH.configure(Configs.TALL_GRASS_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(2, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> PATCH_FERN = Feature.RANDOM_PATCH.configure(Configs.FERN_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(3)));
        public static final ConfiguredFeature<?, ?> PATCH_LARGE_FERN = Feature.RANDOM_PATCH.configure(Configs.LARGE_FERN_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(1, 0.3F, 1)));
        public static final ConfiguredFeature<?, ?> PATCH_GIANT_FERN = Feature.RANDOM_PATCH.configure(Configs.GIANT_FERN_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(2)));

        public static final ConfiguredFeature<?, ?> PATCH_LILY_COOL = Feature.RANDOM_PATCH.configure(Configs.PATCH_LILY_COOL_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(6)));
        public static final ConfiguredFeature<?, ?> PATCH_LILY_NEUTRAL = Feature.RANDOM_PATCH.configure(Configs.PATCH_LILY_NEUTRAL_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(6)));
        public static final ConfiguredFeature<?, ?> PATCH_LILY_WARM = Feature.RANDOM_PATCH.configure(Configs.PATCH_LILY_WARM_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(6)));

        private static <FC extends FeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, BayouBlues.id(name), configuredFeature);
        }

        public static void registerConfiguredFeatures() {
            register("cypress_grown", CYPRESS_GROWN);
            register("mega_cypress_grown", MEGA_CYPRESS_GROWN);
            register("cypress", CYPRESS);
            register("mega_cypress", MEGA_CYPRESS);
            register("mega_cypress_knees", MEGA_CYPRESS_KNEES);
            register("water_cypress", WATER_CYPRESS);
            register("water_mega_cypress", WATER_MEGA_CYPRESS);

            register("cypress_bush", CYPRESS_BUSH);

            register("algae", ALGAE);
            register("fallen_cypress_leaves", FALLEN_CYPRESS_LEAVES);

            register("podzol", PODZOL);

            register("trees_bayou", TREES_BAYOU);
            register("trees_bayou_water", TREES_BAYOU_WATER);

            register("patch_grass", PATCH_GRASS);
            register("patch_tall_grass", PATCH_TALL_GRASS);
            register("patch_fern", PATCH_FERN);
            register("patch_large_fern", PATCH_LARGE_FERN);
            register("patch_giant_fern", PATCH_GIANT_FERN);

            register("patch_lily_cool", PATCH_LILY_COOL);
            register("patch_lily_neutral", PATCH_LILY_NEUTRAL);
            register("patch_lily_warm", PATCH_LILY_WARM);
        }
    }
}