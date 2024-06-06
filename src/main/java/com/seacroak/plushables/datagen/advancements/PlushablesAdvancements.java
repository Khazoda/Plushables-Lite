package com.seacroak.plushables.datagen.advancements;

import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class PlushablesAdvancements implements Consumer<Consumer<AdvancementEntry>> {
  @Override
  public void accept(Consumer<AdvancementEntry> advancementConsumer) {
    AdvancementEntry rootAdvancement = Advancement.Builder.create()
        .display(
            MainRegistry.PENGUIN_PLUSHABLE,
            Text.translatable("advancement.plushables.root.title"),
            Text.translatable("advancement.plushables.root.description"),
            new Identifier("textures/gui/advancements/backgrounds/adventure.png"), // Background image used
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            false, // Show toast top right
            false, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("root", EnterBlockCriterion.Conditions.block(Blocks.AIR))
        .build(advancementConsumer, "plushables" + "/root");

    AdvancementEntry gotCopperIngotAdvancement = Advancement.Builder.create().parent(rootAdvancement)
        .display(
            Items.COPPER_INGOT,
            Text.translatable("advancement.plushables.got_copper_ingot.title"),
            Text.translatable("advancement.plushables.got_copper_ingot.description"),
            null,
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            true, // Show toast top right
            false, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("got_copper_ingot", InventoryChangedCriterion.Conditions.items(Items.COPPER_INGOT))
        .rewards(AdvancementRewards.Builder.recipe(GenericUtils.ID("builder_block")))
        .build(advancementConsumer, "plushables" + "/got_copper_ingot");

    AdvancementEntry gotGoldIngotAdvancement = Advancement.Builder.create().parent(rootAdvancement)
        .display(
            Items.GOLD_INGOT,
            Text.translatable("advancement.plushables.got_gold_ingot.title"),
            Text.translatable("advancement.plushables.got_gold_ingot.description"),
            null,
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            true, // Show toast top right
            false, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("got_gold_ingot", InventoryChangedCriterion.Conditions.items(Items.GOLD_INGOT))
        .rewards(AdvancementRewards.Builder.recipe(GenericUtils.ID("heart_of_gold")).addRecipe(GenericUtils.ID("powered_heart")))
        .build(advancementConsumer, "plushables" + "/got_gold_ingot");

    AdvancementEntry gotBuilderAdvancement = Advancement.Builder.create().parent(gotCopperIngotAdvancement)
        .display(
            MainRegistry.PENGUIN_PLUSHABLE,
            Text.translatable("advancement.plushables.got_builder.title"),
            Text.translatable("advancement.plushables.got_builder.description"),
            null,
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            false, // Show toast top right
            true, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("got_builder", InventoryChangedCriterion.Conditions.items(MainRegistry.PENGUIN_PLUSHABLE))
        .rewards(AdvancementRewards.Builder.recipe(GenericUtils.ID("heart_of_gold")).addRecipe(GenericUtils.ID("powered_heart")))
        .build(advancementConsumer, "plushables" + "/got_builder");

    AdvancementEntry gotHeartAdvancement = Advancement.Builder.create().parent(gotGoldIngotAdvancement)
        .display(
            MainRegistry.HEART_OF_GOLD,
            Text.translatable("advancement.plushables.got_heart.title"),
            Text.translatable("advancement.plushables.got_heart.description"),
            null,
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            true, // Show toast top right
            true, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("got_heart", InventoryChangedCriterion.Conditions.items(MainRegistry.HEART_OF_GOLD))
        .rewards(AdvancementRewards.Builder.recipe(GenericUtils.ID("heart_of_gold"))
            .addRecipe(GenericUtils.ID("penguin" + "_plushable"))
            .addRecipe(GenericUtils.ID("froglin" + "_plushable"))
            .addRecipe(GenericUtils.ID("fox" + "_plushable"))
            .addRecipe(GenericUtils.ID("pig" + "_plushable"))
            .addRecipe(GenericUtils.ID("truffles" + "_plushable"))
            .addRecipe(GenericUtils.ID("djungelskog" + "_plushable"))
            .addRecipe(GenericUtils.ID("rattiam" + "_plushable"))
            .addRecipe(GenericUtils.ID("triceratops" + "_plushable"))
            .addRecipe(GenericUtils.ID("unicorn" + "_plushable"))
            .addRecipe(GenericUtils.ID("whelpling" + "_plushable"))
            .addRecipe(GenericUtils.ID("raptor" + "_plushable"))
            .addRecipe(GenericUtils.ID("wizard" + "_plushable"))
            .addRecipe(GenericUtils.ID("beaux" + "_plushable"))
            .addRecipe(GenericUtils.ID("goblin" + "_plushable"))
            .addRecipe(GenericUtils.ID("big_tater" + "_plushable"))
            .addRecipe(GenericUtils.ID("big_irritater" + "_plushable"))
            .addRecipe(GenericUtils.ID("otter" + "_plushable"))
            .addRecipe(GenericUtils.ID("shrump" + "_plushable"))
            .addRecipe(GenericUtils.ID("octoplush" + "_plushable"))
            .addRecipe(GenericUtils.ID("snail" + "_plushable"))
            .addRecipe(GenericUtils.ID("whale" + "_plushable"))
            .addRecipe(GenericUtils.ID("goldfish" + "_plushable"))
            .addRecipe(GenericUtils.ID("trater" + "_plushable"))
            .addRecipe(GenericUtils.ID("conductor" + "_plushable"))
            .addRecipe(GenericUtils.ID("capybara" + "_plushable"))
            .addRecipe(GenericUtils.ID("animatronic" + "_plushable"))
            .addRecipe(GenericUtils.ID("moobloom" + "_plushable"))
            .addRecipe(GenericUtils.ID("froge" + "_plushable"))
            .addRecipe(GenericUtils.ID("hampter" + "_plushable"))
            .addRecipe(GenericUtils.ID("dormouse" + "_plushable"))
            .addRecipe(GenericUtils.ID("sea_bunny" + "_plushable"))
            .addRecipe(GenericUtils.ID("mammoth" + "_plushable"))
            .addRecipe(GenericUtils.ID("tiger" + "_plushable"))
            .addRecipe(GenericUtils.ID("walrus" + "_plushable"))
            .addRecipe(GenericUtils.ID("blahaj" + "_plushable"))
            .addRecipe(GenericUtils.ID("wisp" + "_plushable"))
            .addRecipe(GenericUtils.ID("cooper" + "_plushable"))
            .addRecipe(GenericUtils.ID("ziggy" + "_plushable"))
            .addRecipe(GenericUtils.ID("potsy" + "_plushable"))
            .addRecipe(GenericUtils.ID("clucky" + "_plushable"))
            .addRecipe(GenericUtils.ID("rupert" + "_plushable"))
            .addRecipe(GenericUtils.ID("dragon" + "_plushable"))
            .addRecipe(GenericUtils.ID("orangutan" + "_plushable"))
            .addRecipe(GenericUtils.ID("owl" + "_plushable"))
            .addRecipe(GenericUtils.ID("statuette" + "_plushable"))

            .addRecipe(GenericUtils.ID("cap" + "_froglin"))
            .addRecipe(GenericUtils.ID("cap" + "_fox"))
            .addRecipe(GenericUtils.ID("cap" + "_unicorn"))
            .addRecipe(GenericUtils.ID("cap" + "_mushroom"))
            .addRecipe(GenericUtils.ID("cap" + "_beaux"))
            .addRecipe(GenericUtils.ID("cap" + "_truffles"))
        )
        .build(advancementConsumer, "plushables" + "/got_heart");
  }

}
