package com.seacroak.plushables;

import com.seacroak.plushables.networking.*;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.client.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;

public final class PlushablesModClient implements ClientModInitializer {
    public static boolean onServer = false;

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        PayloadTypeRegistry.playC2S().register(ConfigSyncPayload.ID, ConfigSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ParticlePayload.ID, ParticlePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SoundPayload.ID, SoundPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SoundPayloadPlayerless.ID, SoundPayloadPlayerless.CODEC);
        EntityRendererRegistry.initClient();

        /* Functional Transparency*/
        /* Blocks */
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BUILDER_BLOCK, RenderLayer.getCutout());

        /* Simple Plushables */
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PIG_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.TRUFFLES_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.WHELPLING_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.RAPTOR_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BIG_TATER_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BIG_IRRITATER_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.OTTER_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.SHRUMP_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.WHALE_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.GOLDFISH_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.TRATER_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.FROGE_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.MAMMOTH_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.TIGER_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BLAHAJ_PLUSHABLE, RenderLayer.getCutout());

        /* Complex Plushables */
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.RUPERT_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.OWL_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.STATUETTE_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.CLUCKY_PLUSHABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.DRAGON_PLUSHABLE, RenderLayer.getCutout());

        /* Clientside Commands */
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess)
                        -> dispatcher.register(ClientCommandManager.literal("plushables")
                        .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("command.plushables.root"));
                                    return 1;
                                }
                        )
                        .then(ClientCommandManager.literal("wiki")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("command.plushables.wiki").setStyle(Style.EMPTY.withColor(Formatting.BLUE).withUnderline(true).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://plushables.khazoda.com"))));
                                    return 1;
                                })
                        )
                )
        );

        /* Config Sync Networking Packet Client Receipt */
        ClientPlayNetworking.registerGlobalReceiver(ConfigSyncPayload.ID, (payload, context) -> {
            if (context.client().world == null) return;
            context.client().execute(() -> {
                PlushablesNetworking.priorityConfig(payload.enableBasket(), payload.allowAllBlockItems());
            });
        });

        /* Sound Event Networking Packet Client Receipt */
        ClientPlayNetworking.registerGlobalReceiver(SoundPayload.ID, (payload, context) -> {
            SoundEvent decodedSoundEvent = PacketDecoder.decodeSoundEvent(payload.soundIdentifier());
            if (context.client() == null) return;

            assert context.client().player != null;
            if (payload.playerUUID() == context.client().player.getUuid())
                return;
            context.client().execute(() -> {
                if (context.client().world == null)
                    return;
                PlushablesNetworking.playSoundOnClient(decodedSoundEvent, context.client().world, BlockPos.ofFloored(payload.pos()), 1f, payload.pitch());
            });
        });

        /* Sound Event Networking Packet Client Receipt */
        ClientPlayNetworking.registerGlobalReceiver(SoundPayloadPlayerless.ID, (payload, context) -> {
            SoundEvent decodedSoundEvent = PacketDecoder.decodeSoundEvent(payload.soundIdentifier());
            if (context.client() == null) return;
            context.client().execute(() -> {
                if (context.client().world == null)
                    return;
                PlushablesNetworking.playSoundOnClient(decodedSoundEvent, context.client().world, BlockPos.ofFloored((Position) payload.pos()), 1f, payload.pitch());
            });
        });

        /* Particle Networking Packet Client Receipt */
        ClientPlayNetworking.registerGlobalReceiver(ParticlePayload.ID, (payload, context) -> {
            ParticleEffect decodedParticles = PacketDecoder.decodeParticle(payload.particleIdentifier());
            if (context.client() == null) return;
            assert context.client().player != null;
            if (payload.playerUUID() == context.client().player.getUuid())
                return;
            context.client().execute(() -> {
                if (context.client().world == null)
                    return;
                PlushablesNetworking.spawnParticlesOnClient(decodedParticles, context.client().world, BlockPos.ofFloored(payload.pos()), payload.particleCount(), payload.offset(), payload.spread());
            });
        });
    }
}
