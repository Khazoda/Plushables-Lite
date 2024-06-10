package com.seacroak.plushables;

import com.seacroak.plushables.config.PlushablesConfig;
import com.seacroak.plushables.networking.*;
import com.seacroak.plushables.registry.ItemGroupRegistry;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import com.seacroak.plushables.util.GenericUtils;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlushablesMod implements ModInitializer {

    public static final String MOD_ID = "plushables";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ItemGroup PLUSHABLES_GROUP = ItemGroupRegistry.createItemGroup();

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, GenericUtils.ID("plushables"), PLUSHABLES_GROUP);
        MidnightConfig.init(MOD_ID, PlushablesConfig.class);
        PlushablesNetworking.registerServersideClientJoinListener();

        MainRegistry.init();
        SoundRegistry.init();
        /* Keep this in */
        new TileRegistry();


        PayloadTypeRegistry.playS2C().register(ConfigSyncPayload.ID, ConfigSyncPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ParticlePayload.ID, ParticlePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SoundPayload.ID, SoundPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SoundPayloadPlayerless.ID, SoundPayloadPlayerless.CODEC);

        PlushablesNetworking.registerGlobalSoundPacketReceiverWithPlayer();
        PlushablesNetworking.registerGlobalSoundPacketReceiverWithoutPlayer();
        PlushablesNetworking.registerGlobalParticlePacketReceiver();

        LOGGER.info("[Plushables] Finished loading!");
    }
}