package com.teamaurora.bayou_blues.core.registry;

import com.teamaurora.bayou_blues.core.BayouBlues;
import com.terraformersmc.terraform.boat.TerraformBoat;
import com.terraformersmc.terraform.boat.TerraformBoatEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BayouBluesEntities {
    public static EntityType<TerraformBoatEntity> BAYOU_BOAT;

    public static void init() {
        BAYOU_BOAT = registerBoat("cypress", BayouBluesItems.CYPRESS_BOAT, BayouBluesBlocks.CYPRESS_WOOD, BoatEntity.Type.DARK_OAK);
    }

    private static EntityType<TerraformBoatEntity> registerBoat(String name, Item boat, Block wood, BoatEntity.Type vanilla) {
        Identifier skin = BayouBlues.id("textures/entity/boat/" + name + ".png");
        TerraformBoat boats = new TerraformBoat(boat, wood.asItem(), skin, vanilla);

        EntityType<TerraformBoatEntity> type = FabricEntityTypeBuilder.<TerraformBoatEntity>create(
                SpawnGroup.MISC, (entity, world) -> new TerraformBoatEntity(entity, world, boats))
                .dimensions(EntityDimensions.fixed(1.375F, 0.5625F))
                .build();

        return Registry.register(Registry.ENTITY_TYPE, BayouBlues.id(name + "_boat"), type);
    }
}