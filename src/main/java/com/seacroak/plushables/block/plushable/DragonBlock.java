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

public class DragonBlock extends BaseInteractablePlushable {

  public DragonBlock() {
  }


  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.1875, 0.40625, 0.125, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.1875, 0.59375, 0.125, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.59375, 0.4375, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.3125, 0.171875, 0.59375, 0.5, 0.421875));

    return shape;
  }

  @Override
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPayload.sendPlayerPacketToClients(serverWorld, new SoundPayload(player, pos, SoundRegistry.LIGHTFURY, 1f));
    ParticlePayload.sendParticlePacketToClients(serverWorld, new ParticlePayload
        (player, pos, "minecraft:note", 1, new Vec3d(0, 0.5, 0), 0f));
    ParticlePayload.sendParticlePacketToClients(serverWorld, new ParticlePayload
        (player, pos, "minecraft:glow", 5, new Vec3d(0, 0, 0), 0.05f));
    return ActionResult.CONSUME;
  }

  @Override
  protected ActionResult clientRunEffects(World world, BlockPos pos) {
    PlushablesNetworking.playSoundOnClient(SoundRegistry.LIGHTFURY, world, pos, 1f, 1f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.NOTE, world, pos, 1, new Vec3d(0, 0.5, 0), 0);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.GLOW, world, pos, 5, new Vec3d(0, 0, 0), 0.05f);
    return ActionResult.SUCCESS;
  }

}