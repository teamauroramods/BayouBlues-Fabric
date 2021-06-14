package com.teamaurora.bayou_blues.core.mixin;

import com.teamaurora.bayou_blues.common.block.LilyFlowerBlock;
import com.teamaurora.bayou_blues.core.BayouBluesConfig;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    private static boolean checkAdjacentForSolid(World world, BlockPos pos) {
        for (int i = 0; i < 4; i++) {
            Direction dir = Direction.fromHorizontal(i);
            BlockPos poffset = pos.offset(dir);

            if (world.getFluidState(poffset).getFluid() != Fluids.WATER) {
                return true;
            }
        }
        return false;
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if (!world.isClient) {
            boolean success = false;
            int decrementAmount = 1;
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.LILY_PAD && BayouBluesConfig.get().lilyFlowers.lilyBonemealBehavior == 1) {
                if (world.getRandom().nextBoolean() || checkAdjacentForSolid(world, pos.down())) {
                    Block lily = LilyFlowerBlock.getRandomLily(world.getRandom());

                    world.setBlockState(pos, lily.getDefaultState(), 3);
                    success = true;
                }
            } else if (state.getBlock() instanceof LilyFlowerBlock && BayouBluesConfig.get().lilyFlowers.lilyBonemealBehavior == 2) {
                Block.dropStack(world, pos, state.getBlock().getPickStack(world, pos, state));
                success = true;
            }/* else if (state.getBlock() == Blocks.LARGE_FERN) {
                if (state.get(TallPlantBlock.HALF) == DoubleBlockHalf.LOWER) {
                    ((TallPlantBlock) BayouBluesBlocks.GIANT_FERN).placeAt(world, pos, 3);
                } else {
                    ((TallPlantBlock) BayouBluesBlocks.GIANT_FERN).placeAt(world, pos.down(), 3);
                }
                success = true;
            }*/

            if (success) {
                context.getStack().decrement(decrementAmount);
                world.syncWorldEvent(2005, pos, 0);
                cir.setReturnValue(ActionResult.SUCCESS);
                cir.cancel();
            }
        }
    }
}