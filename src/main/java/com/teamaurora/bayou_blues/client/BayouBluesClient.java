package com.teamaurora.bayou_blues.client;

import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesEntities;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BayouBluesClient implements ClientModInitializer {
    public void onInitializeClient() {
        registerBlockColors();
        setupRenderLayer();
    }

    public static void setupRenderLayer() {
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.CYPRESS_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_CYPRESS_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.CYPRESS_LEAF_CARPET, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.HANGING_CYPRESS_LEAVES, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.CYPRESS_KNEE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.LARGE_CYPRESS_KNEE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.CYPRESS_BRANCH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.ALGAE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.ALGAE_THATCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.ALGAE_THATCH_SLAB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.ALGAE_THATCH_STAIRS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.BEARD_MOSS_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.BEARD_MOSS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.BLUE_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.LIGHT_GRAY_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.CYAN_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.LIGHT_BLUE_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.MAGENTA_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.PINK_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.PURPLE_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.WHITE_LILY, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_BLUE_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_LIGHT_GRAY_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_CYAN_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_LIGHT_BLUE_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_MAGENTA_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_PINK_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_PURPLE_LILY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.POTTED_WHITE_LILY, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BayouBluesBlocks.GIANT_FERN, RenderLayer.getCutout());

        EntityRendererRegistry.INSTANCE.register(BayouBluesEntities.BAYOU_BOAT, (dispatcher, context) -> new BoatEntityRenderer(dispatcher));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, BayouBluesBlocks.CYPRESS_SIGNS.getFirst().getTexture()));
    }

    public static void registerBlockColors() {
        registerBlockColor((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), Arrays.asList(BayouBluesBlocks.CYPRESS_LEAVES, BayouBluesBlocks.HANGING_CYPRESS_LEAVES, BayouBluesBlocks.CYPRESS_LEAF_CARPET));
        registerBlockColor((x, world, pos, u) -> world != null && pos != null ? 2129968 : 7455580, Arrays.asList(BayouBluesBlocks.BLUE_LILY, BayouBluesBlocks.LIGHT_GRAY_LILY, BayouBluesBlocks.CYAN_LILY, BayouBluesBlocks.LIGHT_BLUE_LILY, BayouBluesBlocks.MAGENTA_LILY, BayouBluesBlocks.PINK_LILY, BayouBluesBlocks.PURPLE_LILY, BayouBluesBlocks.WHITE_LILY));
        registerBlockColor((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getGrassColor(world, x.get(TallPlantBlock.HALF) == DoubleBlockHalf.UPPER ? pos.down() : pos) : -1, Arrays.asList(BayouBluesBlocks.GIANT_FERN));

        registerBlockItemColor((color, items) -> FoliageColors.getDefaultColor(), Arrays.asList(BayouBluesBlocks.CYPRESS_LEAVES, BayouBluesBlocks.HANGING_CYPRESS_LEAVES, BayouBluesBlocks.CYPRESS_LEAF_CARPET));
        registerBlockItemColor((color, items) -> GrassColors.getColor(0.5D, 1.0D), Arrays.asList(BayouBluesBlocks.GIANT_FERN));
    }

    public static void registerBlockColor(BlockColorProvider color, List<Block> blocksIn) {
        blocksIn.removeIf(Objects::isNull);
        if (blocksIn.size() > 0) {
            Block[] blocks = new Block[blocksIn.size()];
            for (int i = 0; i < blocksIn.size(); i++) {
                blocks[i] = blocksIn.get(i);
            }
            ColorProviderRegistry.BLOCK.register(color, blocks);
        }
    }

    public static void registerBlockItemColor(ItemColorProvider color, List<Block> blocksIn) {
        blocksIn.removeIf(Objects::isNull);
        if (blocksIn.size() > 0) {
            Block[] blocks = new Block[blocksIn.size()];

            for(int i = 0; i < blocksIn.size(); ++i) {
                blocks[i] = blocksIn.get(i);
            }

            ColorProviderRegistry.ITEM.register(color, blocks);
        }
    }
}