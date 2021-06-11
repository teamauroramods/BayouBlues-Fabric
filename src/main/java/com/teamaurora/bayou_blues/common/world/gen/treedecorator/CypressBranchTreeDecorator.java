package com.teamaurora.bayou_blues.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.block.CypressBranchBlock;
import com.teamaurora.bayou_blues.common.block.DoubleCypressKneeBlock;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.tree.TreeDecoratorType;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CypressBranchTreeDecorator extends TreeDecorator {
    public static final Codec<CypressBranchTreeDecorator> CODEC;
    public static final CypressBranchTreeDecorator DECORATOR = new CypressBranchTreeDecorator();

    @Override
    protected TreeDecoratorType<?> getType() {
        return BayouBluesFeatures.CYPRESS_BRANCH.get();
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void generate(StructureWorldAccess world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, BlockBox bounds) {

        for (BlockPos pos : logs) {
            if (rand.nextInt(25) == 0) {
                Direction dir = Direction.fromHorizontal(rand.nextInt(4));
                if (world.getBlockState(pos.offset(dir)).isAir()) {
                    world.setBlockState(pos.offset(dir), BayouBluesBlocks.CYPRESS_BRANCH.get().getDefaultState().with(CypressBranchBlock.FACING, dir).with(CypressBranchBlock.AGE, 2), 3);
                }
            }
        }
    }
}