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

public class GoldfishBlock extends BaseInteractablePlushable {
  public GoldfishBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.8125, 0.625, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.3125, 0.1875, 0.1875, 0.5, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.3125, 0.1875, 0.9375, 0.5, 0.375));

    return shape;
  }

  @Override
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPayload.sendPlayerPacketToClients(serverWorld, new SoundPayload(player.getUuid(), pos, SoundRegistry.GOLDFISH, 1f));
    ParticlePayload.sendParticlePacketToClients(serverWorld, new ParticlePayload
        (player.getUuid(), pos, ParticleTypes.DOLPHIN, 15, new Vec3d(0, 0.15, 0), 0.05f));
    ParticlePayload.sendParticlePacketToClients(serverWorld, new ParticlePayload
        (player.getUuid(), pos, ParticleTypes.FISHING, 15, new Vec3d(0, 0.15, 0), 0.05f));
    return ActionResult.CONSUME;
  }

  @Override
  protected ActionResult clientRunEffects(World world, BlockPos pos) {
    PlushablesNetworking.playSoundOnClient(SoundRegistry.GOLDFISH, world, pos, 1f, 1f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.DOLPHIN, world, pos, 15, new Vec3d(0, 0.15, 0), 0.05f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.FISHING, world, pos, 15, new Vec3d(0, 0.15, 0), 0.05f);

    return ActionResult.SUCCESS;
  }
}