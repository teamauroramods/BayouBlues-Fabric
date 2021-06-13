package com.teamaurora.bayou_blues.core;

import com.teamaurora.bayou_blues.common.world.biome.BayouBluesBiomeFeatures;
import com.teamaurora.bayou_blues.core.other.BayouBluesCompat;
import com.teamaurora.bayou_blues.core.other.BayouBluesEvents;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBiomes;
import com.teamaurora.bayou_blues.core.registry.BayouBluesEntities;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BayouBlues implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "bayou_blues";
    public static final String MOD_NAME = "BayouBlues";

    @Override
    public void onInitialize() {
        BayouBluesFeatures.Configured.registerConfiguredFeatures();
        BayouBluesCompat.init();
        BayouBluesBiomeFeatures.init();

        log(Level.INFO, "Initializing");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}