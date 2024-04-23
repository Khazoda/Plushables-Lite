package com.seacroak.plushables.item;


import com.google.common.collect.Multimap;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.*;


import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class CapArmorItem extends ArmorItem {
  static final Item.Settings capItemSettings = new Item.Settings().maxCount(1);

  public CapArmorItem(String name) {
    super(new ArmorMaterial() {
      @Override
      public int getDurability(Type type) {
        return 10;
      }

      @Override
      public int getProtection(Type type) {
        return 1;
      }

      @Override
      public int getEnchantability() {
        return 0;
      }

      @Override
      public SoundEvent getEquipSound() {
        return null;
      }

      @Override
      public Ingredient getRepairIngredient() {
        return Ingredient.ofStacks(new ItemStack(Items.LEATHER));
      }

      @Environment(EnvType.CLIENT)
      @Override
      public String getName() {
        return name;
      }

      @Override
      public float getToughness() {
        return 0.5f;
      }

      @Override
      public float getKnockbackResistance() {
        return 0f;
      }
    }, Type.HELMET, capItemSettings);
  }
}
