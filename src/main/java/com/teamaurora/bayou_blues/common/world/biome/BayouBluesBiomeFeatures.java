package com.teamaurora.bayou_blues.common.world.biome;

import com.teamaurora.bayou_blues.core.BayouBluesConfig;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBiomes;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class BayouBluesBiomeFeatures {
    public static void addFeatures(BiomeLoadingEvent event) {
        Identifier biomeName = event.getName();

        if (biomeName == null)
            return;

        if (DataUtil.matchesKeys(biomeName, BayouBluesBiomes.BAYOU.getKey(), BayouBluesBiomes.BAYOU_HILLS.getKey())) {
            withBayouFeatures(event.getGeneration(), event.getSpawns());
        } else if (DataUtil.matchesKeys(biomeName, BiomeKeys.SWAMP, BiomeKeys.SWAMP_HILLS)) {
            withCoolLilyFlowers(event.getGeneration());
        } else if (biomeName.equals(new Identifier("environmental","blossom_woods")) ||
                biomeName.equals(new Identifier("environmental","blossom_hills")) ||
                biomeName.equals(new Identifier("environmental","blossom_highlands")) ||
                biomeName.equals(new Identifier("environmental","blossom_valleys"))) {
            withWarmLilyFlowers(event.getGeneration());
        }
    }

    public static void withBayouFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnInfoBuilder spawns) {
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

    private static void withBayouVegetation(BiomeGenerationSettingsBuilder builder) {
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

    private static void withBayouGrasses(BiomeGenerationSettingsBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_GRASS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_FERN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_GIANT_FERN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_TALL_GRASS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LARGE_FERN);
    }

    private static void withAllLilyFlowers(BiomeGenerationSettingsBuilder builder) {
        if (BayouBluesConfig.COMMON.doLiliesSpawn) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_COOL);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_WARM);
        }
    }

    private static void withWarmLilyFlowers(BiomeGenerationSettingsBuilder builder) {
        if (BayouBluesConfig.COMMON.doLiliesSpawn) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_WARM);
        }
    }

    private static void withCoolLilyFlowers(BiomeGenerationSettingsBuilder builder) {
        if (BayouBluesConfig.COMMON.doLiliesSpawn) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_NEUTRAL);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, BayouBluesFeatures.Configured.PATCH_LILY_WARM);
        }
    }
}
