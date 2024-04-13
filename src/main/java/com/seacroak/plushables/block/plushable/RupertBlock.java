package com.seacroak.plushables.block.plushable;

import com.seacroak.plushables.block.BaseInteractablePlushable;
import com.seacroak.plushables.networking.ParticlePacketHandler;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class RupertBlock extends BaseInteractablePlushable {

  public RupertBlock() {
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.406, 0.109, 0.25, 0.531, 0.296, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.390, 0.265, 0.171, 0.546, 0.390, 0.328));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0.015, 0.672, 0.5625, 0.203, 0.734));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.265, 0.5625, 0.1875, 0.328));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.390, 0, 0.265, 0.453, 0.1875, 0.328));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.015, 0.671, 0.4375, 0.203, 0.734));
    return shape;
  }

  @Override
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.RUPERT_PURR, 1f));
    ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
        (player, pos, "minecraft:note", 1, new Vec3d(0, 0.5, 0), 0f));
    ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
        (player, pos, "minecraft:glow", 5, new Vec3d(0, 0, 0), 0.05f));
    return ActionResult.CONSUME;
  }

  @Override
  protected ActionResult clientRunEffects(World world, BlockPos pos) {
    PlushablesNetworking.playSoundOnClient(SoundRegistry.RUPERT_PURR, world, pos, 1f, 1f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.NOTE, world, pos, 1, new Vec3d(0, 0.5, 0), 0);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.GLOW, world, pos, 5, new Vec3d(0, 0, 0), 0.05f);
    return ActionResult.SUCCESS;
  }
}

