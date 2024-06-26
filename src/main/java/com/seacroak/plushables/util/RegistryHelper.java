package com.seacroak.plushables.util;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.item.PlushableBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class RegistryHelper {

  // General use Identifier() maker function
  public static Identifier newID(String name) {
    return new Identifier(PlushablesMod.MOD_ID, name);
  }

  // Block Registry Helper Functions
  // *******************************
  // 1. Default BlockItem Registration Entrypoint: creates Identifier from ModID & block name
  public static <B extends Block> B registerBlock(String name, B block, Item.Settings itemSettings) {
    return registerBlock(newID(name), block, itemSettings);
  }

  // 2. Takes identifier and registers block and block items
  public static <B extends Block> B registerBlock(Identifier name, B block, Item.Settings itemSettings) {
    BlockItem item = new BlockItem(block, (itemSettings));
    item.appendBlocks(Item.BLOCK_ITEMS, item);

    Registry.register(Registries.BLOCK, name, block);
    Registry.register(Registries.ITEM, name, item);
    return block;
  }

  public static <B extends Block> B registerBlockOnly(String name, B block) {
    return registerBlockOnly(newID(name), block);
  }

  public static <B extends Block> B registerBlockOnly(Identifier name, B block) {
    Registry.register(Registries.BLOCK, name, block);
    return block;
  }

  public static <I extends BlockItem> I registerBlockItem(String name, I blockItem) {
    return registerBlockItem(newID(name), blockItem);
  }

  public static <I extends BlockItem> I registerBlockItem(Identifier name, I blockItem) {
    Registry.register(Registries.ITEM, name, blockItem);
    return blockItem;
  }

  // Block Registry Helper Functions (Variant for Plushable Blocks)
  public static <B extends Block> B registerPlushableBlock(String name, B block, Item.Settings itemSettings) {
    return registerPlushableBlock(newID(name), block, itemSettings);
  }

  public static <B extends Block> B registerPlushableBlock(Identifier name, B block, Item.Settings itemSettings) {
    BlockItem item = new PlushableBlockItem(block, (itemSettings));
    item.appendBlocks(Item.BLOCK_ITEMS, item);

    Registry.register(Registries.BLOCK, name, block);
    Registry.register(Registries.ITEM, name, item);
    return block;
  }

  // Item Registry Helper Functions
  // ******************************
  public static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, newID(name), item);
  }

  // Register Armor Material
  public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, ArmorMaterial material) {
    return Registry.registerReference(Registries.ARMOR_MATERIAL, newID(name), material);
  }

//  public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
//    EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap(ArmorItem.Type.class);
//    ArmorItem.Type[] var9 = ArmorItem.Type.values();
//    int var10 = var9.length;
//
//    for(int var11 = 0; var11 < var10; ++var11) {
//      ArmorItem.Type type = var9[var11];
//      enumMap.put(type, (Integer)defense.get(type));
//    }
//
//    return Registry.registerReference(Registries.ARMOR_MATERIAL, new Identifier(id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
//  }
}
