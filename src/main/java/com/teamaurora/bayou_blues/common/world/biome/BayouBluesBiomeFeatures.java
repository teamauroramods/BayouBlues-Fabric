package com.teamaurora.bayou_blues.common.world.biome;

import com.teamaurora.bayou_blues.core.BayouBluesConfig;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBiomes;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import java.util.function.Predicate;

/*
public class BayouBluesBiomeFeatures {
    private static final Predicate<BiomeSelectionContext> SWAMP = BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.SWAMP_HILLS);
    private static final Predicate<BiomeSelectionContext> BAYOU = BiomeSelectors.includeByKey(BayouBluesBiomes.BAYOU, BayouBluesBiomes.BAYOU_HILLS);

    public static void addFeatures() {
        withBayouFeatures();
        withCoolLilyFlowers();
    }

    public static void withBayouFeatures() {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        GenerationSettings.Builder builder = new GenerationSettings.Builder();

        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_SWAMP);
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDungeons(builder);

        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);

        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.TOP_LAYER_MODIFICATION, rk(BayouBluesFeatures.Configured.PODZOL));

        withBayouVegetation();

        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(ConfiguredFeatures.SEAGRASS_SWAMP));

        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addSwampVegetation(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);

        DefaultBiomeFeatures.addBatsAndMonsters(spawns);
        DefaultBiomeFeatures.addFarmAnimals(spawns);
        BiomeModifications.addSpawn(BAYOU, SpawnGroup.MONSTER, EntityType.SLIME, 1, 1, 1);
    }

    private static void withBayouVegetation() {
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.ALGAE));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.TREES_BAYOU));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.TREES_BAYOU_WATER));
        withBayouGrasses();
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.FALLEN_CYPRESS_LEAVES));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(ConfiguredFeatures.FLOWER_SWAMP));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(ConfiguredFeatures.PATCH_WATERLILLY));
        withAllLilyFlowers();
        BiomeModifications.addFeature(BAYOU,GenerationStep.Feature.VEGETAL_DECORATION, rk(ConfiguredFeatures.BROWN_MUSHROOM_SWAMP));
        BiomeModifications.addFeature(BAYOU,GenerationStep.Feature.VEGETAL_DECORATION, rk(ConfiguredFeatures.RED_MUSHROOM_SWAMP));
    }

    private static void withBayouGrasses() {
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_GRASS));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_FERN));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_GIANT_FERN));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_TALL_GRASS));
        BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LARGE_FERN));
    }

    private static void withAllLilyFlowers() {
        if (BayouBluesConfig.get().lilyFlowers.doLiliesSpawn) {
            BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LILY_COOL));
            BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL));
            BiomeModifications.addFeature(BAYOU, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LILY_WARM));
        }
    }

    private static void withCoolLilyFlowers() {
        if (BayouBluesConfig.get().lilyFlowers.doLiliesSpawn) {
            BiomeModifications.addFeature(SWAMP, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL));
            BiomeModifications.addFeature(SWAMP, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LILY_WARM));
        }
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> rk(ConfiguredFeature carver) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getKey(carver).get();
    }
}
 */

public class BayouBluesBiomeFeatures {
    private static final Predicate<BiomeSelectionContext> SWAMP = BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.SWAMP_HILLS);

    public static void init() {
        withCoolLilyFlowers();
    }

    public static void addFeatures() {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        GenerationSettings.Builder builder = new GenerationSettings.Builder();

        withBayouFeatures(builder, spawns);
    }

    public static void withBayouFeatures(GenerationSettings.Builder builder, SpawnSettings.Builder spawns) {
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_SWAMP);
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDungeons(builder);

        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);

        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, BayouBluesFeatures.Configured.PODZOL);

        withBayouVegetation(builder);

        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SEAGRASS_SWAMP);

        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addSwampVegetation(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);

        DefaultBiomeFeatures.addBatsAndMonsters(spawns);
        DefaultBiomeFeatures.addFarmAnimals(spawns);
        spawns.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 1, 1, 1));
    }

    private static void withBayouVegetation(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.ALGAE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.TREES_BAYOU);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.TREES_BAYOU_WATER);
        withBayouGrasses(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.FALLEN_CYPRESS_LEAVES);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FLOWER_SWAMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_WATERLILLY);
        withAllLilyFlowers(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_SWAMP);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.RED_MUSHROOM_SWAMP);
    }

    private static void withBayouGrasses(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_GRASS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_FERN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_GIANT_FERN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_TALL_GRASS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LARGE_FERN);
    }

    private static void withAllLilyFlowers(GenerationSettings.Builder builder) {
        if (BayouBluesConfig.get().lilyFlowers.doLiliesSpawn) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_COOL);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_WARM);
        }
    }

    private static void withCoolLilyFlowers() {
        if (BayouBluesConfig.get().lilyFlowers.doLiliesSpawn) {
            BiomeModifications.addFeature(SWAMP, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL));
            BiomeModifications.addFeature(SWAMP, GenerationStep.Feature.VEGETAL_DECORATION, rk(BayouBluesFeatures.Configured.PATCH_LILY_WARM));
        }
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> rk(ConfiguredFeature carver) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getKey(carver).get();
    }
}