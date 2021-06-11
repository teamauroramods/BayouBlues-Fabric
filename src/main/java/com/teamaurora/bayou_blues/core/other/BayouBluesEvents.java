package com.teamaurora.bayou_blues.core.other;

import com.teamaurora.bayou_blues.common.block.LilyFlowerBlock;
import com.teamaurora.bayou_blues.common.util.BayouBluesSellItemFactory;
import com.teamaurora.bayou_blues.core.BayouBluesConfig;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class BayouBluesEvents {
    public void onLivingDamage(LivingDamageEvent event) {
        if (event.getSource() == DamageSource.FALL) {
            BlockPos pos = event.getEntity().getBlockPos().down();
            WorldView world = event.getEntity().getEntityWorld();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (block instanceof CypressKneeBlock || block instanceof DoubleCypressKneeBlock) {
                event.getEntity().damage(DamageSource.GENERIC, event.getAmount() * 2);
            }
        }
    }

    private boolean checkAdjacentForSolid(World world, BlockPos pos) {
        for (int i = 0; i < 4; i++) {
            Direction dir = Direction.fromHorizontal(i);
            BlockPos poffset = pos.offset(dir);

            if (world.getFluidState(poffset).getFluid() != Fluids.WATER) {
                return true;
            }
        }
        return false;
    }

    public void onBonemealUse(BonemealEvent event) {
        BlockState state = event.getBlock();
        BlockPos pos = event.getPos();
        World world = event.getWorld();

        if (state.getBlock() == Blocks.LILY_PAD && BayouBluesConfig.COMMON.lilyBonemealBehavior == 1) {
            if (world.getRandom().nextBoolean() || checkAdjacentForSolid(world, pos.down())) {
                Block lily = LilyFlowerBlock.getRandomLily(world.getRandom());

                if (!world.isClient) {
                    world.setBlockState(pos, lily.getDefaultState(), 3);
                    event.setResult(Event.Result.ALLOW);
                }
            }
        }
        if (BayouBluesConfig.COMMON.lilyBonemealBehavior == 2) {
            Block stateBlock = state.getBlock();
            if (stateBlock instanceof LilyFlowerBlock) {
                Block.dropStack(world, pos, stateBlock.getPickStack(world, pos, state));
                event.setResult(Event.Result.ALLOW);
            }
        }
        if (state.getBlock() == Blocks.LARGE_FERN) {
            if (state.get(TallPlantBlock.HALF) == DoubleBlockHalf.LOWER) {
                ((TallPlantBlock) BayouBluesBlocks.GIANT_FERN).placeAt(world, pos, 3);
            } else {
                ((TallPlantBlock) BayouBluesBlocks.GIANT_FERN).placeAt(world, pos.down(), 3);
            }
            event.setResult(Event.Result.ALLOW);
        }
    }

    public static void onWandererTradesEvent() {
        TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.ALGAE.asItem().getDefaultStack(), 1, 2, 6, 1))
        );
        TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesBlocks.CYPRESS_SAPLING.asItem().getDefaultStack(), 5, 1, 8, 1))
        );

        if (BayouBluesConfig.COMMON.doLiliesSpawn || BayouBluesConfig.COMMON.lilyBonemealBehavior != 0) {
            // lilies are enabled
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.BLUE_LILY, 1, 1, 7, 1))
            );
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.CYAN_LILY, 1, 1, 7, 1))
            );
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.LIGHT_BLUE_LILY, 1, 1, 7, 1))
            );
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.LIGHT_GRAY_LILY, 1, 1, 7, 1))
            );
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.WHITE_LILY, 1, 1, 7, 1))
            );
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.PINK_LILY, 1, 1, 7, 1))
            );
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.PURPLE_LILY, 1, 1, 7, 1))
            );
            TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.MAGENTA_LILY, 1, 1, 7, 1))
            );
        }
    }

    public static void onVillagerTradesEvent() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.GOOSEBERRY_PIE, 1, 4, 12, 5))
        );
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.GOOSEBERRY_JAM_COOKIE, 1, 18, 12, 5))
        );
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.GOOSEBERRIES, 2, 16, 12, 10))
        );
    }
}