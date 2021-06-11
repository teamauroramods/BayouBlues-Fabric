package com.teamaurora.bayou_blues.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class AlgaeItem extends BlockItem {
    public AlgaeItem(Block blockIn, Item.Settings builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        BlockHitResult blockraytraceresult = raycast(worldIn, playerIn, RaycastContext.FluidHandling.SOURCE_ONLY);
        BlockHitResult blockraytraceresult1 = blockraytraceresult.withBlockPos(blockraytraceresult.getBlockPos().up());
        ActionResult actionresulttype = super.useOnBlock(new ItemUsageContext(playerIn, handIn, blockraytraceresult1));
        return new TypedActionResult<>(actionresulttype, playerIn.getStackInHand(handIn));
    }
}