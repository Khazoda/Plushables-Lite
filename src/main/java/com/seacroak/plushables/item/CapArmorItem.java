package com.seacroak.plushables.item;


import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

public class CapArmorItem extends ArmorItem {
    static final Item.Settings capItemSettings = new Item.Settings().maxCount(1);

    public CapArmorItem(String name, RegistryEntry<ArmorMaterial> material) {
        super(material, Type.HELMET, capItemSettings);
    }
}
