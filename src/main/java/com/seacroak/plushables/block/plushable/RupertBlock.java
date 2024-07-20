package com.seacroak.plushables.block.plushable;

import com.seacroak.plushables.block.BaseInteractablePlushable;
import com.seacroak.plushables.networking.ParticlePayload;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPayload;
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
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3438, 0.1094, 0.25, 0.5938, 0.3594, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3281, 0.2656, 0.1094, 0.6094, 0.5156, 0.3906));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0.01563, 0.6406, 0.625, 0.2656, 0.7656));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.2344, 0.625, 0.25, 0.3594));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.2344, 0.4375, 0.25, 0.3594));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.01563, 0.6406, 0.4375, 0.2656, 0.7656));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.2656, 0.07813, 0.5625, 0.3906, 0.1406));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3438, 0.5156, 0.1719, 0.4063, 0.5781, 0.2969));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5313, 0.5156, 0.1719, 0.5938, 0.5781, 0.2969));

    return shape;
  }

  @Override
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPayload.sendPlayerPacketToClients(serverWorld, new SoundPayload(player.getUuid(), pos, SoundRegistry.RUPERT_PURR, 1f));
    ParticlePayload.sendParticlePacketToClients(serverWorld, new ParticlePayload
        (player.getUuid(), pos, ParticleTypes.NOTE, 1, new Vec3d(0, 0.5, 0), 0f));
    ParticlePayload.sendParticlePacketToClients(serverWorld, new ParticlePayload
        (player.getUuid(), pos, ParticleTypes.GLOW, 5, new Vec3d(0, 0, 0), 0.05f));
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

