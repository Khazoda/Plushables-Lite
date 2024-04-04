package com.seacroak.plushables.block.plushie;

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

public class StatuetteBlock extends BaseInteractablePlushable {

  public StatuetteBlock() { }


  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.40625, 0.59375, 0.28125, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0.28125, 0.3828, 0.59375, 0.5625, 0.5234));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.59375, 0.28125, 0.40625, 0.6875, 0.5625, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.28125, 0.40625, 0.40625, 0.5625, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0, 0.40625, 0.5, 0.28125, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0.5625, 0.3828, 0.59375, 0.75, 0.5234));
    return shape;
  }

  @Override
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.STATUETTE, 1f));
    ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
        (player, pos, "minecraft:note", 1, new Vec3d(0, 0.5, 0), 0f));
    ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
        (player, pos, "minecraft:glow", 5, new Vec3d(0, 0, 0), 0.05f));
    return ActionResult.CONSUME;
  }

  @Override
  protected ActionResult clientRunEffects(World world, BlockPos pos) {
    PlushablesNetworking.playSoundOnClient(SoundRegistry.STATUETTE, world, pos, 1f, 1f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.NOTE, world, pos, 1, new Vec3d(0, 0.5, 0), 0);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.GLOW, world, pos, 5, new Vec3d(0, 0, 0), 0.05f);
    return ActionResult.SUCCESS;
  }
}