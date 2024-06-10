package com.seacroak.plushables.item;


import com.seacroak.plushables.registry.MainRegistry;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;

import static com.seacroak.plushables.PlushablesMod.MOD_ID;

public class BeauxCapItem extends CapArmorItem{
  public BeauxCapItem() {
    super("cap_beaux", MainRegistry.BEAUX_MATERIAL);
  }

  public static final ArmorMaterial MATERIAL = new ArmorMaterial(
      Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.HELMET, 2);
      }),
      20,
      SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
      () -> Ingredient.ofItems(Items.LEATHER),
      List.of(
          new ArmorMaterial.Layer(
              new Identifier(MOD_ID, "cap_beaux")
          )
      ),
      0,
      0
  );
}
