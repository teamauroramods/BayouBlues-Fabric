package com.teamaurora.bayou_blues.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.block.CypressKneeBlock;
import com.teamaurora.bayou_blues.common.block.DoubleCypressKneeBlock;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.tree.TreeDecoratorType;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SparseCypressKneesTreeDecorator extends TreeDecorator {
    public static final Codec<SparseCypressKneesTreeDecorator> CODEC;
    public static final SparseCypressKneesTreeDecorator DECORATOR = new SparseCypressKneesTreeDecorator();

    @Override
    protected TreeDecoratorType<?> getType() {
        return BayouBluesFeatures.SPARSE_CYPRESS_KNEES;
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void generate(StructureWorldAccess world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, BlockBox bounds) {
        int minY = world.getHeight();
        int maxWaterY = 0;
        for (BlockPos pos : logs) {
            if (pos.getY() < minY) minY = pos.getY();
            if (pos.getY() > maxWaterY) {
                for (int i = 0; i < 4; i++) {
                    Direction dir = Direction.fromHorizontal(i);
                    if (world.getFluidState(pos.offset(dir)).getFluid() == Fluids.WATER) {
                        maxWaterY = pos.getY();
                    }
                }
            }
        }
        if (maxWaterY > minY) minY = maxWaterY;
        for (BlockPos pos : logs) {
            if (pos.getY() == minY && rand.nextInt(6) == 0) {
                for (int x = -2; x <= 2; x++) {
                    for (int y = -2; y <= 2; y++) {
                        for (int z = -2; z <= 2; z++) {
                            BlockPos newPos = pos.add(x, y, z);
                            if (pos.isWithinDistance(newPos, 3.0D)) {
                                if ((world.getBlockState(newPos.down()).getBlock() == Blocks.GRASS_BLOCK && world.getBlockState(newPos).isAir())) {
                                    if (rand.nextInt(9) == 0) {
                                        if (rand.nextInt(3) == 0 && world.getBlockState(newPos.up()).isAir()) {
                                            ((DoubleCypressKneeBlock) BayouBluesBlocks.LARGE_CYPRESS_KNEE).placeAt(world, newPos, 3);
                                        } else {
                                            world.setBlockState(newPos, BayouBluesBlocks.CYPRESS_KNEE.getDefaultState().with(CypressKneeBlock.WATERLOGGED, false), 3);
                                        }
                                    }
                                } else if (world.getFluidState(newPos).getFluid() == Fluids.WATER && world.getBlockState(newPos.down()).isOpaque()) {
                                    if (rand.nextInt(8) == 0) {
                                        if (rand.nextInt(4) != 0 && world.getBlockState(newPos.up()).isAir()) {
                                            ((DoubleCypressKneeBlock) BayouBluesBlocks.LARGE_CYPRESS_KNEE).placeAt(world, newPos, 3);
                                        } else {
                                            world.setBlockState(newPos, BayouBluesBlocks.CYPRESS_KNEE.getDefaultState().with(CypressKneeBlock.WATERLOGGED, true), 3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}