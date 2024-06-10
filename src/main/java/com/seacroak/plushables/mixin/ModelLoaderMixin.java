package com.seacroak.plushables.mixin;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
  @Shadow
  protected abstract void addModelToBake(ModelIdentifier modelId, UnbakedModel model);

  @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModelToBake(Lnet/minecraft/client/util/ModelIdentifier;Lnet/minecraft/client/render/model/UnbakedModel;)V", ordinal = 0, shift = At.Shift.AFTER))
  public void addCodex(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SpriteGetter>> blockStates, CallbackInfo ci) {
    this.addModelToBake(ModelIdentifier.ofInventoryVariant(Identifier.of(PlushablesMod.MOD_ID, "codex_3d")), jsonUnbakedModels.get(Identifier.of(PlushablesMod.MOD_ID, "models/item/codex_3d.json")));
  }
}