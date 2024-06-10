package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;


public class BasketBlockEntity extends BlockEntity {
  public static final int max_stack_size = 8;
  private final ItemStack[] plushStack = new ItemStack[8];
  private int top_pointer = 0;
  private int[] seeds = new int[8];

  public BasketBlockEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.BASKET_TILE, pos, state);
    Random random = Random.create();
    Arrays.fill(plushStack, ItemStack.EMPTY);

    for (int i = 0; i < max_stack_size; i++) {
      seeds[i] = random.nextInt(100);
    }
    sync();
  }

  public static void tick(World world, BlockPos pos, BlockState state, BasketBlockEntity be) {
  }

  public boolean pushPlush(PlayerEntity player) {
    /* If stack is full, cancel interaction */
    if (top_pointer == max_stack_size) return false;
    /* If emptied totally, reset pointer */
    if (top_pointer == -1) top_pointer = 0;
    /* Account for last operation */
    if (!plushStack[top_pointer].isOf(Items.AIR)) top_pointer += 1;
    plushStack[top_pointer] = player.getEquippedStack(EquipmentSlot.MAINHAND).copyWithCount(1);
    player.getEquippedStack(EquipmentSlot.MAINHAND)
        .setCount(player.getEquippedStack(EquipmentSlot.MAINHAND).getCount() - 1);
    top_pointer += 1;
    sync();
    return true;
  }

  public boolean popPlush(PlayerEntity player) {
    /* If stack is empty, cancel interaction */
    if (top_pointer == -1) return false;
    /* If full totally, reset pointer */
    if (top_pointer == max_stack_size) top_pointer = max_stack_size - 1;
    /* Account for empty basket */
    if (plushStack[top_pointer].isOf(Items.AIR) && top_pointer == 0) return false;
    /* Account for last operation */
    if (plushStack[top_pointer].isOf(Items.AIR)) top_pointer -= 1;
    player.giveItemStack(plushStack[top_pointer].copyWithCount(1));
    plushStack[top_pointer] = ItemStack.EMPTY;
    top_pointer -= 1;
    sync();
    return true;
  }

  public ItemStack[] popAll() {
    ItemStack[] poppedItems = Arrays.copyOf(plushStack, 8);
    /* Reset plush stack to empty values */
    Arrays.fill(plushStack, ItemStack.EMPTY);
    top_pointer = 0;
    sync();
    return poppedItems;
  }

  public ItemStack[] getPlushStack() {
    return this.plushStack;
  }

  public int getTopPointer() {
    return this.top_pointer;
  }

  public int[] getSeeds() {
    return this.seeds;
  }


  /* Data Serialization */
  @Override
  protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
    super.writeNbt(nbt, registryLookup);
    NbtList nbtList = new NbtList();
    for (ItemStack plush : plushStack
    ) {
      if (plush != ItemStack.EMPTY) {
        NbtElement nbtBuilder = plush.encode(registryLookup);
        nbtList.add(nbtBuilder);
      }
    }
    nbt.put("plush_stack", nbtList);
    nbt.putInt("top_pointer", top_pointer);
    nbt.putIntArray("seeds", seeds);
  }

  @Override
  public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
    super.readNbt(nbt, registryLookup);
    NbtList nbtList = nbt.getList("plush_stack", 10);
    for (int i = 0; i < nbtList.size(); i++) {
      NbtCompound itemNbt = nbtList.getCompound(i);
      ItemStack itemStack = ItemStack.fromNbt(registryLookup, itemNbt).orElse(ItemStack.EMPTY);
      plushStack[i] = itemStack;
    }
    top_pointer = nbt.getInt("top_pointer");
    seeds = nbt.getIntArray("seeds");
    sync();
  }

  public void sync() {
    markDirty();
    if (world == null) return;
    world.updateListeners(pos, this.getCachedState(), this.getCachedState(), 3);
  }

  @Nullable
  @Override
  public Packet<ClientPlayPacketListener> toUpdatePacket() {
    return BlockEntityUpdateS2CPacket.create(this);
  }


  @Override
  public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
    return createNbt(registryLookup);
  }

}



