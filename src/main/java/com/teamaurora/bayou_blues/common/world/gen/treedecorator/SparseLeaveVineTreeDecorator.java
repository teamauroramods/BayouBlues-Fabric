package com.teamaurora.bayou_blues.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.block.VineBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SparseLeaveVineTreeDecorator extends TreeDecorator {
    public static final Codec<SparseLeaveVineTreeDecorator> CODEC;
    public static final SparseLeaveVineTreeDecorator DECORATOR = new SparseLeaveVineTreeDecorator();

    protected TreeDecoratorType<?> getType() {
        return BayouBluesFeatures.SPARSE_LEAVE_VINE;
    }

    public void generate(StructureWorldAccess worldAccess, Random random, List<BlockPos> pos, List<BlockPos> pos2, Set<BlockPos> pos3, BlockBox blockBox) {
        pos2.forEach((dir) -> {
            if (random.nextInt(16) == 0) {
                BlockPos blockpos = dir.west();
                if (Feature.isAir(worldAccess, blockpos)) {
                    this.placeVines(worldAccess, blockpos, VineBlock.EAST, pos3, blockBox);
                }
            }

            if (random.nextInt(16) == 0) {
                BlockPos blockpos1 = dir.east();
                if (Feature.isAir(worldAccess, blockpos1)) {
                    this.placeVines(worldAccess, blockpos1, VineBlock.WEST, pos3, blockBox);
                }
            }

            if (random.nextInt(16) == 0) {
                BlockPos blockpos2 = dir.north();
                if (Feature.isAir(worldAccess, blockpos2)) {
                    this.placeVines(worldAccess, blockpos2, VineBlock.SOUTH, pos3, blockBox);
                }
            }

            if (random.nextInt(16) == 0) {
                BlockPos blockpos3 = dir.south();
                if (Feature.isAir(worldAccess, blockpos3)) {
                    this.placeVines(worldAccess, blockpos3, VineBlock.NORTH, pos3, blockBox);
                }
            }

        });
    }

    private void placeVines(ModifiableTestableWorld world, BlockPos pos, BooleanProperty booleanProperty, Set<BlockPos> pos2, BlockBox blockBox) {
        this.placeVine(world, pos, booleanProperty, pos2, blockBox);
        int i = 4;

        for(BlockPos blockpos = pos.down(); Feature.isAir(world, blockpos) && i > 0; --i) {
            this.placeVine(world, blockpos, booleanProperty, pos2, blockBox);
            blockpos = blockpos.down();
        }
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }
}