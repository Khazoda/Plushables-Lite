package com.seacroak.plushables.item;


import com.seacroak.plushables.PlushablesMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;

import static com.seacroak.plushables.registry.MainRegistry.CAP_MATERIAL;

public class CapArmorItem extends ArmorItem {
    static final Item.Settings capItemSettings = new Item.Settings().maxCount(1);

    public static final ArmorMaterial material = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(Type.CHESTPLATE, 2);
                map.put(Type.LEGGINGS, 2);
                map.put(Type.BOOTS, 2);

            }), 20,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.ofItems(Items.LEATHER),
            List.of(
                    new ArmorMaterial.Layer(
                            new Identifier(PlushablesMod.MOD_ID, "cap")
                    ),
                    new ArmorMaterial.Layer(
                            new Identifier(PlushablesMod.MOD_ID, "cap"), "_froglin" + "_overlay", false
                    )
            ),
            0, 0
    );


    public CapArmorItem(String name) {
        super(RegistryEntry.of(CAP_MATERIAL), Type.HELMET, capItemSettings);
    }
}
