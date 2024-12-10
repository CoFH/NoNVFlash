package cofh.nonvflash.mixin;

import cofh.nonvflash.NoNVFlash;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.effect.MobEffects.NIGHT_VISION;

@Mixin (GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject (
            method = "getNightVisionScale",
            at = @At (value = "HEAD"),
            cancellable = true
    )
    private static void onGetNightVisionScale(LivingEntity living, float partialTicks, CallbackInfoReturnable<Float> callback) {

        float i = 0.0f;
        if (living.hasEffect(NIGHT_VISION)) {
            i = NoNVFlash.maxBrightness;
            if (NoNVFlash.fadeOut) {
                int duration = living.getEffect(NIGHT_VISION).getDuration();
                i = duration > NoNVFlash.fadeTicks ? NoNVFlash.maxBrightness : duration * NoNVFlash.fadeRate;
            }
        }
        callback.setReturnValue(i);
        callback.cancel();
    }

}
