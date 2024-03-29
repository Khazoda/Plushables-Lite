package com.seacroak.plushables.registry.uncommon;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.BasketBlockEntity;
import com.seacroak.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public final class TileRegistry {
	public static final BlockEntityType<BasketBlockEntity> BASKET_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":basket_block_entity",
			FabricBlockEntityTypeBuilder.create(BasketBlockEntity::new,
					MainRegistry.BASKET_BLOCK).build(null));
}