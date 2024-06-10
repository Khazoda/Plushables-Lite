package com.seacroak.plushables.networking;

import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

/* Similar structure to all packet handlers in plushables.networking */
/* Assures that the serverside config always takes priority over the client's */
/* Packet is always sent from the server to clients */
/* Packet fields should mimic those found in PlushablesConfig.class */

public record ConfigSyncPayload(UUID playerToSync, boolean enableBasket, boolean allowAllBlockItems) implements CustomPayload {
    public static final CustomPayload.Id<ConfigSyncPayload> ID = new CustomPayload.Id<>(GenericUtils.ID("plushable_config_packet"));
    public static final PacketCodec<RegistryByteBuf, ConfigSyncPayload> CODEC = PacketCodec.of(ConfigSyncPayload::write, ConfigSyncPayload::read);

    public static void sendConfigPacketToClient(ServerPlayerEntity player, UUID playerToSync,boolean enableBasket, boolean allowAllBlockItems) {
        ServerPlayNetworking.send(player,new ConfigSyncPayload(playerToSync,enableBasket,allowAllBlockItems));
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static ConfigSyncPayload read(RegistryByteBuf buf) {
        return new ConfigSyncPayload(buf.readUuid(), buf.readBoolean(),buf.readBoolean());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeUuid(playerToSync);
        buf.writeBoolean(enableBasket);
        buf.writeBoolean(allowAllBlockItems);
    }
}
