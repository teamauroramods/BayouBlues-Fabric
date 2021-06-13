package com.teamaurora.bayou_blues.core;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.Config.Gui.Background;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = BayouBlues.MODID)
@Background(value = BayouBlues.MODID + ":textures/block/algae_thatch.png")
public class BayouBluesConfig implements ConfigData {
    @CollapsibleObject
    @Comment("Values for biome frequencies; lower = more rare.")
    public BiomeWeights biomeWeights = new BiomeWeights();

    @CollapsibleObject
    @Comment("Lily flower configuration")
    public LilyFlowers lilyFlowers = new LilyFlowers();

    public static class BiomeWeights {
        @Comment("Bayou weight")
        @RequiresRestart
        public int bayouWeight = 1;

        @RequiresRestart
        @Comment("Bayou Hills weight")
        public int bayouHillsWeight = 0;
    }

    public static class LilyFlowers {
        @Comment("Lily bonemeal behavior. 0 = none. 1 = bonemealing lily pad grows lily flowers. 2 = bonemealing lily flower gives you more of it.")
        public int lilyBonemealBehavior = 1;

        @Comment("Lily flower spawns in the world")
        public boolean doLiliesSpawn = true;
    }

    public static void registerConfig() {
        AutoConfig.register(BayouBluesConfig.class, GsonConfigSerializer::new);
    }

    public static BayouBluesConfig get() {
        return AutoConfig.getConfigHolder(BayouBluesConfig.class).getConfig();
    }
}