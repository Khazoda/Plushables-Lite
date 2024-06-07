package com.seacroak.plushables.registry;

import com.seacroak.plushables.block.BasketBlock;
import com.seacroak.plushables.block.BuilderBlock;
import com.seacroak.plushables.block.CodexBlock;
import com.seacroak.plushables.block.plushable.*;
import com.seacroak.plushables.item.*;
import com.seacroak.plushables.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

public final class MainRegistry {
  /* Item Settings */
  static final Item.Settings defaultItemSettings = new Item.Settings().maxCount(64);
  static final Item.Settings plushableItemSettings = new Item.Settings().maxCount(8).equipmentSlot(stack -> EquipmentSlot.HEAD);
  static final Item.Settings unstackableItemSettings = new Item.Settings().maxCount(1);

  /* Standard Blocks */
  public static final BuilderBlock BUILDER_BLOCK = registerBlock("builder_block", new BuilderBlock(), defaultItemSettings);
  public static final BasketBlock BASKET_BLOCK = registerBlock("basket_block", new BasketBlock(), defaultItemSettings);

  /* Items */
  public static final Item HEART_OF_GOLD = registerItem("heart_of_gold");
  public static final Item POWERED_HEART = registerItem("powered_heart");

  /* Plushables Codex */
  public static final Block CODEX_BLOCK = registerBlockOnly("codex", new CodexBlock());
  public static final BlockItem CODEX_ITEM = registerBlockItem("codex", new CodexItem(CODEX_BLOCK,unstackableItemSettings));


  /* Complex Plushables */
  public static final CluckyBlock CLUCKY_PLUSHABLE = registerPlushable("clucky_plushable", new CluckyBlock(), plushableItemSettings);
  public static final RupertBlock RUPERT_PLUSHABLE = registerPlushable("rupert_plushable", new RupertBlock(), plushableItemSettings);
  public static final DragonBlock DRAGON_PLUSHABLE = registerPlushable("dragon_plushable", new DragonBlock(), plushableItemSettings);
  public static final OrangutanBlock ORANGUTAN_PLUSHABLE = registerPlushable("orangutan_plushable", new OrangutanBlock(), plushableItemSettings);
  public static final OwlBlock OWL_PLUSHABLE = registerPlushable("owl_plushable", new OwlBlock(), plushableItemSettings);
  public static final StatuetteBlock STATUETTE_PLUSHABLE = registerPlushable("statuette_plushable", new StatuetteBlock(), plushableItemSettings);

  /* Simple Plushables */
  public static final PenguinBlock PENGUIN_PLUSHABLE = registerPlushable("penguin_plushable", new PenguinBlock(), plushableItemSettings);
  public static final FroglinBlock FROGLIN_PLUSHABLE = registerPlushable("froglin_plushable", new FroglinBlock(), plushableItemSettings);
  public static final FoxBlock FOX_PLUSHABLE = registerPlushable("fox_plushable", new FoxBlock(), plushableItemSettings);
  public static final PigBlock PIG_PLUSHABLE = registerPlushable("pig_plushable", new PigBlock(), plushableItemSettings);
  public static final TrufflesBlock TRUFFLES_PLUSHABLE = registerPlushable("truffles_plushable", new TrufflesBlock(), plushableItemSettings);
  public static final DjungelskogBlock DJUNGELSKOG_PLUSHABLE = registerPlushable("djungelskog_plushable", new DjungelskogBlock(), plushableItemSettings);
  public static final RattiamBlock RATTIAM_PLUSHABLE = registerPlushable("rattiam_plushable", new RattiamBlock(), plushableItemSettings);
  public static final TriceratopsBlock TRICERATOPS_PLUSHABLE = registerPlushable("triceratops_plushable", new TriceratopsBlock(), plushableItemSettings);
  public static final UnicornBlock UNICORN_PLUSHABLE = registerPlushable("unicorn_plushable", new UnicornBlock(), plushableItemSettings);
  public static final WhelplingBlock WHELPLING_PLUSHABLE = registerPlushable("whelpling_plushable", new WhelplingBlock(), plushableItemSettings);
  public static final RaptorBlock RAPTOR_PLUSHABLE = registerPlushable("raptor_plushable", new RaptorBlock(), plushableItemSettings);
  public static final WizardBlock WIZARD_PLUSHABLE = registerPlushable("wizard_plushable", new WizardBlock(), plushableItemSettings);
  public static final BeauxBlock BEAUX_PLUSHABLE = registerPlushable("beaux_plushable", new BeauxBlock(), plushableItemSettings);
  public static final GoblinBlock GOBLIN_PLUSHABLE = registerPlushable("goblin_plushable", new GoblinBlock(), plushableItemSettings);
  public static final BigTaterBlock BIG_TATER_PLUSHABLE = registerPlushable("big_tater_plushable", new BigTaterBlock(), plushableItemSettings);
  public static final BigIrritaterBlock BIG_IRRITATER_PLUSHABLE = registerPlushable("big_irritater_plushable", new BigIrritaterBlock(), plushableItemSettings);
  public static final OtterBlock OTTER_PLUSHABLE = registerPlushable("otter_plushable", new OtterBlock(), plushableItemSettings);
  public static final ShrumpBlock SHRUMP_PLUSHABLE = registerPlushable("shrump_plushable", new ShrumpBlock(), plushableItemSettings);
  public static final OctoplushBlock OCTOPLUSH_PLUSHABLE = registerPlushable("octoplush_plushable", new OctoplushBlock(), plushableItemSettings);
  public static final SnailBlock SNAIL_PLUSHABLE = registerPlushable("snail_plushable", new SnailBlock(), plushableItemSettings);
  public static final WhaleBlock WHALE_PLUSHABLE = registerPlushable("whale_plushable", new WhaleBlock(), plushableItemSettings);
  public static final GoldfishBlock GOLDFISH_PLUSHABLE = registerPlushable("goldfish_plushable", new GoldfishBlock(), plushableItemSettings);
  public static final TraterBlock TRATER_PLUSHABLE = registerPlushable("trater_plushable", new TraterBlock(), plushableItemSettings);
  public static final ConductorBlock CONDUCTOR_PLUSHABLE = registerPlushable("conductor_plushable", new ConductorBlock(), plushableItemSettings);
  public static final CapybaraBlock CAPYBARA_PLUSHABLE = registerPlushable("capybara_plushable", new CapybaraBlock(), plushableItemSettings);
  public static final AnimatronicBlock ANIMATRONIC_PLUSHABLE = registerPlushable("animatronic_plushable", new AnimatronicBlock(), plushableItemSettings);
  public static final MoobloomBlock MOOBLOOM_PLUSHABLE = registerPlushable("moobloom_plushable", new MoobloomBlock(), plushableItemSettings);
  public static final FrogeBlock FROGE_PLUSHABLE = registerPlushable("froge_plushable", new FrogeBlock(), plushableItemSettings);
  public static final HampterBlock HAMPTER_PLUSHABLE = registerPlushable("hampter_plushable", new HampterBlock(), plushableItemSettings);
  public static final DormouseBlock DORMOUSE_PLUSHABLE = registerPlushable("dormouse_plushable", new DormouseBlock(), plushableItemSettings);
  public static final SeaBunnyBlock SEA_BUNNY_PLUSHABLE = registerPlushable("sea_bunny_plushable", new SeaBunnyBlock(), plushableItemSettings);
  public static final MammothBlock MAMMOTH_PLUSHABLE = registerPlushable("mammoth_plushable", new MammothBlock(), plushableItemSettings);
  public static final TigerBlock TIGER_PLUSHABLE = registerPlushable("tiger_plushable", new TigerBlock(), plushableItemSettings);
  public static final WalrusBlock WALRUS_PLUSHABLE = registerPlushable("walrus_plushable", new WalrusBlock(), plushableItemSettings);
  public static final BlahajBlock BLAHAJ_PLUSHABLE = registerPlushable("blahaj_plushable", new BlahajBlock(), plushableItemSettings);
  public static final WispBlock WISP_PLUSHABLE = registerPlushable("wisp_plushable", new WispBlock(), plushableItemSettings);
  public static final CooperBlock COOPER_PLUSHABLE = registerPlushable("cooper_plushable", new CooperBlock(), plushableItemSettings);
  public static final ZiggyBlock ZIGGY_PLUSHABLE = registerPlushable("ziggy_plushable", new ZiggyBlock(), plushableItemSettings);
  public static final PotsyBlock POTSY_PLUSHABLE = registerPlushable("potsy_plushable", new PotsyBlock(), plushableItemSettings);


  /* Caps */
  public static final RegistryEntry<ArmorMaterial> FROGLIN_MATERIAL = registerArmorMaterial("cap_froglin", FroglinCapItem.MATERIAL);
  public static final Item FROGLIN_CAP = registerCap("cap_froglin", new FroglinCapItem());
  public static final RegistryEntry<ArmorMaterial> FOX_MATERIAL = registerArmorMaterial("cap_fox", FoxCapItem.MATERIAL);
  public static final Item FOX_CAP = registerCap("cap_fox", new FoxCapItem());
  public static final RegistryEntry<ArmorMaterial> UNICORN_MATERIAL = registerArmorMaterial("cap_unicorn", UnicornCapItem.MATERIAL);
  public static final Item UNICORN_CAP = registerCap("cap_unicorn", new UnicornCapItem());
  public static final RegistryEntry<ArmorMaterial> MUSHROOM_MATERIAL = registerArmorMaterial("cap_mushroom", MushroomCapItem.MATERIAL);
  public static final Item MUSHROOM_CAP = registerCap("cap_mushroom", new MushroomCapItem());
  public static final RegistryEntry<ArmorMaterial> BEAUX_MATERIAL = registerArmorMaterial("cap_beaux", BeauxCapItem.MATERIAL);
  public static final Item BEAUX_CAP = registerCap("cap_beaux", new BeauxCapItem());
  public static final RegistryEntry<ArmorMaterial> TRUFFLES_MATERIAL = registerArmorMaterial("cap_truffles", TrufflesCapItem.MATERIAL);
  public static final Item TRUFFLES_CAP = registerCap("cap_truffles", new TrufflesCapItem());


  public static void init() {
    /* Codex registering with custom block item */
  }

  /* Default Block Item */
  private static <B extends Block> B registerBlock(String name, B block, Item.Settings itemSettings) {
    return RegistryHelper.registerBlock(name, block, itemSettings);
  }


  private static <B extends Block> B registerPlushable(String name, B block, Item.Settings itemSettings) {
    return RegistryHelper.registerPlushableBlock(name, block, itemSettings);
  }

  /* Custom Block Item */
  private static <B extends Block> B registerBlockOnly(String name, B block) {
    return RegistryHelper.registerBlockOnly(name, block);
  }

  private static <I extends BlockItem> BlockItem registerBlockItem(String name, I blockItem) {
    return RegistryHelper.registerBlockItem(name, blockItem);
  }

  private static Item registerItem(String name) {
    return RegistryHelper.registerItem(name, new Item(defaultItemSettings));
  }

  private static Item registerCap(String name, CapArmorItem capType) {
    return RegistryHelper.registerItem(name, capType);
  }

  private static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, ArmorMaterial material) {
    return RegistryHelper.registerArmorMaterial(name, material);
  }

}
