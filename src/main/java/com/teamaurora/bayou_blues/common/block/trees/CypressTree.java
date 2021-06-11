package com.teamaurora.bayou_blues.common.block.trees;

import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class CypressTree extends LargeTreeSaplingGenerator {
    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(@Nullable Random randomIn, boolean largeHive) {
        return BayouBluesFeatures.Configured.CYPRESS_GROWN;
    }

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> createLargeTreeFeature(@Nullable Random randomIn) {
        return BayouBluesFeatures.Configured.MEGA_CYPRESS_GROWN;
    }
}
