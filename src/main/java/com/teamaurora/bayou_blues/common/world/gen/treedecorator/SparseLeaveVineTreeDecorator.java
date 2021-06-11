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
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.tree.TreeDecoratorType;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SparseLeaveVineTreeDecorator extends TreeDecorator {
    public static final Codec<SparseLeaveVineTreeDecorator> CODEC;
    public static final SparseLeaveVineTreeDecorator DECORATOR = new SparseLeaveVineTreeDecorator();

    protected TreeDecoratorType<?> getType() {
        return BayouBluesFeatures.SPARSE_LEAVE_VINE.get();
    }

    public void generate(StructureWorldAccess p_225576_1_, Random p_225576_2_, List<BlockPos> p_225576_3_, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, BlockBox p_225576_6_) {
        p_225576_4_.forEach((p_242866_5_) -> {
            if (p_225576_2_.nextInt(16) == 0) {
                BlockPos blockpos = p_242866_5_.west();
                if (Feature.isAir(p_225576_1_, blockpos)) {
                    this.func_227420_a_(p_225576_1_, blockpos, VineBlock.EAST, p_225576_5_, p_225576_6_);
                }
            }

            if (p_225576_2_.nextInt(16) == 0) {
                BlockPos blockpos1 = p_242866_5_.east();
                if (Feature.isAir(p_225576_1_, blockpos1)) {
                    this.func_227420_a_(p_225576_1_, blockpos1, VineBlock.WEST, p_225576_5_, p_225576_6_);
                }
            }

            if (p_225576_2_.nextInt(16) == 0) {
                BlockPos blockpos2 = p_242866_5_.north();
                if (Feature.isAir(p_225576_1_, blockpos2)) {
                    this.func_227420_a_(p_225576_1_, blockpos2, VineBlock.SOUTH, p_225576_5_, p_225576_6_);
                }
            }

            if (p_225576_2_.nextInt(16) == 0) {
                BlockPos blockpos3 = p_242866_5_.south();
                if (Feature.isAir(p_225576_1_, blockpos3)) {
                    this.func_227420_a_(p_225576_1_, blockpos3, VineBlock.NORTH, p_225576_5_, p_225576_6_);
                }
            }

        });
    }

    private void func_227420_a_(ModifiableTestableWorld p_227420_1_, BlockPos p_227420_2_, BooleanProperty p_227420_3_, Set<BlockPos> p_227420_4_, BlockBox p_227420_5_) {
        this.placeVine(p_227420_1_, p_227420_2_, p_227420_3_, p_227420_4_, p_227420_5_);
        int i = 4;

        for(BlockPos blockpos = p_227420_2_.down(); Feature.isAir(p_227420_1_, blockpos) && i > 0; --i) {
            this.placeVine(p_227420_1_, blockpos, p_227420_3_, p_227420_4_, p_227420_5_);
            blockpos = blockpos.down();
        }

    }

    static {
        CODEC = Codec.unit(() -> {
            return DECORATOR;
        });
    }
}