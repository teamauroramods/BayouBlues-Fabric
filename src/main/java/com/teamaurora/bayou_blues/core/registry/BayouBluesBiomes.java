package com.teamaurora.bayou_blues.core.registry;

import com.teamaurora.bayou_blues.common.world.biome.BayouBluesBiomeFeatures;
import com.teamaurora.bayou_blues.core.BayouBlues;
import com.teamaurora.bayou_blues.core.BayouBluesConfig;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

public class BayouBluesBiomes {
    public static final RegistryKey<Biome> BAYOU = createBiome("bayou", makeBayouBiome(-0.175F, 0.2F));
    public static final RegistryKey<Biome> BAYOU_HILLS = createBiome("bayou_hills", makeBayouBiome(-0.1F, 0.4F));

    private static RegistryKey<Biome> createBiome(String id, Biome biome) {
        Identifier identifier = BayouBlues.id(id);
        BuiltinRegistries.add(BuiltinRegistries.BIOME, identifier, biome);

        return getKey(identifier);
    }

    private static RegistryKey<Biome> getKey(Identifier identifier) {
        return RegistryKey.of(Registry.BIOME_KEY, identifier);
    }

    public static void init() {
        addHillBiome();
        registerBiomesToDictionary();
    }

    public static void addHillBiome() {
        OverworldBiomes.addHillsBiome(BAYOU, BAYOU_HILLS, 1);
    }

    public static void registerBiomesToDictionary() {
        OverworldBiomes.addContinentalBiome(BAYOU, OverworldClimate.TEMPERATE, BayouBluesConfig.get().biomeWeights.bayouWeight);
        if (BayouBluesConfig.get().biomeWeights.bayouHillsWeight > 0) {
            OverworldBiomes.addContinentalBiome(BAYOU_HILLS, OverworldClimate.TEMPERATE, BayouBluesConfig.get().biomeWeights.bayouHillsWeight);
        }
    }

    /*
    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(BAYOU, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.RARE, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BAYOU_HILLS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.RARE, BiomeDictionary.Type.OVERWORLD);
    }
    */

    private static Biome makeBayouBiome(float depth, float scale) {
        BayouBluesBiomeFeatures.addFeatures();

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.SWAMP)
                .depth(depth)
                .scale(scale)
                .temperature(0.75F)
                .downfall(1.0F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x87C0C6)
                        .waterFogColor(0x3D5156)
                        .fogColor(0xA0E2E5)
                        .skyColor(getSkyColorWithTemperatureModifier(0.75F))
                        .moodSound(BiomeMoodSound.CAVE)
                        .foliageColor(0x69AA2F)
                        .grassColor(0x6CC147)
                        .build())
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings((new GenerationSettings.Builder())
                        .surfaceBuilder(ConfiguredSurfaceBuilders.SWAMP)
                        .build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
}