package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class LargePatchFeature extends Feature<SingleStateFeatureConfig> {
    public LargePatchFeature(Codec<SingleStateFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        var pos = context.getOrigin();
        var config = context.getConfig();
        var rand = context.getRandom();
        var reader = context.getWorld();

        int i = 0;
        for (BlockPos newPos : BlockPos.iterate(pos.add(-6, -6, -6), pos.add(6, 6, 6))) {
            if (config.state.getBlock().canPlaceAt(config.state, reader, newPos)) {
                if (rand.nextFloat() <= 1.0F - (newPos.getSquaredDistance(pos) / 72)) {
                    reader.setBlockState(newPos, config.state, 3);
                    i++;
                }
            }
        }
        return i > 0;
    }
}