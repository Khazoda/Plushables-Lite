package com.seacroak.plushables.networking;

import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public record ParticlePayload(UUID playerUUID, Vec3d pos, String particleIdentifier, int particleCount, Vec3d offset,
                              float spread) implements CustomPayload {
    public static final Id<ParticlePayload> ID = new Id<>(GenericUtils.ID("plushable_particle_packet"));
    public static final PacketCodec<RegistryByteBuf, ParticlePayload> CODEC = PacketCodec.of(ParticlePayload::write, ParticlePayload::read);


    public ParticlePayload(PlayerEntity player, BlockPos pos, String particleIdentifier, int particleCount, Vec3d offset, float spread) {
        this(player.getUuid(), pos.toCenterPos(), particleIdentifier, particleCount, offset, spread);
    }


    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void sendParticlePacketToClients(ServerWorld world, ParticlePayload payload) {
        BlockPos builderPos = new BlockPos((int) payload.pos.x, (int) payload.pos.y, (int) payload.pos.z);
        /* Iterate through players that can see particle event emitter */
        PlayerLookup.tracking(world, builderPos).forEach(player -> {
            if (player.getUuid() == payload.playerUUID)
                return;
            ServerPlayNetworking.send(player, new ParticlePayload(payload.playerUUID, payload.pos, payload.particleIdentifier, payload.particleCount, payload.offset, payload.spread));
        });
    }

    public void write(PacketByteBuf buf) {
        buf.writeUuid(playerUUID);
        buf.writeDouble(pos.x);
        buf.writeDouble(pos.y);
        buf.writeDouble(pos.z);
        buf.writeString(particleIdentifier);
        buf.writeInt(particleCount);
        buf.writeDouble(offset.x);
        buf.writeDouble(offset.y);
        buf.writeDouble(offset.z);
        buf.writeFloat(spread);
    }

    public static ParticlePayload read(RegistryByteBuf buf) {
        UUID player = buf.readUuid();
        Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        String particleIdentifier = buf.readString();
        int particleCount = buf.readInt();
        Vec3d offset = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        float spread = buf.readFloat();

        return new ParticlePayload(player, pos, particleIdentifier, particleCount, offset, spread);
    }
}
