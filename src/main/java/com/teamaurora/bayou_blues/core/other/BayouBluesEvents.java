package com.teamaurora.bayou_blues.core.other;

import com.teamaurora.bayou_blues.common.util.BayouBluesSellItemFactory;
import com.teamaurora.bayou_blues.core.BayouBluesConfig;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.village.VillagerProfession;

public class BayouBluesEvents {
    public static void init() {
        onWandererTradesEvent();
        onVillagerTradesEvent();
    }

    public static void onWandererTradesEvent() {
        TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesItems.ALGAE.asItem().getDefaultStack(), 1, 2, 6, 1))
        );
        TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new BayouBluesSellItemFactory.SellItemFactory(BayouBluesBlocks.CYPRESS_SAPLING.asItem().getDefaultStack(), 5, 1, 8, 1))
        );

        if (BayouBluesConfig.get().lilyFlowers.doLiliesSpawn || BayouBluesConfig.get().lilyFlowers.lilyBonemealBehavior != 0) {
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