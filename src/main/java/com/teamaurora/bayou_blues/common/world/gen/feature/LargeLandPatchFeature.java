package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import java.util.Random;

public class LargeLandPatchFeature extends LargePatchFeature {
    public LargeLandPatchFeature(Codec<SingleStateFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess reader, ChunkGenerator generator, Random rand, BlockPos pos, SingleStateFeatureConfig config) {
        int i = 0;
        for (BlockPos newPos : BlockPos.iterate(pos.add(-6, -6, -6), pos.add(6, 6, 6))) {
            if (config.state.getBlock().canPlaceAt(config.state, reader, newPos) && reader.getBlockState(newPos).isAir() && reader.getBlockState(newPos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                if (rand.nextFloat() <= 1.0F - (newPos.getSquaredDistance(pos) / 72)) {
                    reader.setBlockState(newPos, config.state, 3);
                    i++;
                }
            }
        }
        return i > 0;
    }
}
