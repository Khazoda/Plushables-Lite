package com.seacroak.plushables.networking;

import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public record ParticlePayload(UUID playerUUID, BlockPos pos, ParticleEffect particle, int particleCount,
                              Vec3d offset,
                              float spread) implements CustomPayload {
  public static final CustomPayload.Id<ParticlePayload> ID = new Id<>(GenericUtils.ID("plushable_particle_packet"));

  public static final PacketCodec<RegistryByteBuf, ParticlePayload> CODEC = PacketCodec.tuple(
      Uuids.PACKET_CODEC, ParticlePayload::playerUUID,
      BlockPos.PACKET_CODEC, ParticlePayload::pos,
      ParticleTypes.PACKET_CODEC, ParticlePayload::particle,
      PacketCodecs.INTEGER, ParticlePayload::particleCount,
      PacketCodecs.codec(Vec3d.CODEC), ParticlePayload::offset,
      PacketCodecs.FLOAT, ParticlePayload::spread,
      ParticlePayload::new);


  @Override
  public Id<? extends CustomPayload> getId() {
    return ID;
  }

  public static void sendParticlePacketToClients(ServerWorld world, ParticlePayload payload) {
    BlockPos builderPos = new BlockPos(payload.pos.getX(), payload.pos.getY(), payload.pos.getZ());
    /* Iterate through players that can see particle event emitter */
    PlayerLookup.tracking(world, builderPos).forEach(player -> {
      if (player.getUuid() == payload.playerUUID)
        return;
      ServerPlayNetworking.send(player, new ParticlePayload(payload.playerUUID, payload.pos, payload.particle, payload.particleCount, payload.offset, payload.spread));
    });
  }
}
