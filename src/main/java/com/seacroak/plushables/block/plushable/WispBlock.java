package com.seacroak.plushables.block.plushable;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.BaseInteractablePlushable;
import com.seacroak.plushables.networking.ParticlePayload;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPayload;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import java.util.List;

public class WispBlock extends BaseInteractablePlushable {

  public WispBlock() {
    super(AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOL).strength(0.7f).nonOpaque().luminance(value -> 14).pistonBehavior(PistonBehavior.DESTROY));
  }

  public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    double d = (double) pos.getX() + 0.55 - (double) (random.nextFloat() * 0.25F);
    double e = (double) pos.getY() + 0.55 - (double) (random.nextFloat() * 0.25F);
    double f = (double) pos.getZ() + 0.55 - (double) (random.nextFloat() * 0.25F);
    if (random.nextInt(5) == 0) {
      world.addParticle(ParticleTypes.END_ROD, d, e, f, random.nextGaussian() * 0.01, random.nextGaussian() * 0.01, random.nextGaussian() * 0.01);
    }

  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.375, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.1875, 0.75, 0.25, 0.3125, 0.875));

    return shape;
  }

  @Override
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPayload.sendPlayerPacketToClients(serverWorld, new SoundPayload(player, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, 1f));
    ParticlePayload.sendParticlePacketToClients(serverWorld, new ParticlePayload
        (player, pos, "minecraft:end_rod", 5, new Vec3d(0, -0.1, 0), 0.1f));
    return ActionResult.CONSUME;
  }

  @Override
  protected ActionResult clientRunEffects(World world, BlockPos pos) {
    PlushablesNetworking.playSoundOnClient(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, world, pos, 1f, 1f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.END_ROD, world, pos, 5, new Vec3d(0, -0.1, 0), 0.1f);
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
    tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".wisp.tooltip"));
    super.appendTooltip(stack, context, tooltip, options);
  }
}