package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import java.util.Random;

public class PodzolPatchFeature extends Feature<DefaultFeatureConfig> {
    public PodzolPatchFeature(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess reader, ChunkGenerator generator, Random rand, BlockPos pos, DefaultFeatureConfig config) {
        int i = 0;
        for (BlockPos newPos : BlockPos.iterate(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
            if (reader.getBlockState(newPos).getBlock() == Blocks.GRASS_BLOCK) {
                if (rand.nextFloat() <= 1.0F - (newPos.getSquaredDistance(pos) / 32)) {
                    reader.setBlockState(newPos, Blocks.PODZOL.getDefaultState(), 3);
                    i++;
                }
            }
        }
        return i > 0;
    }
}