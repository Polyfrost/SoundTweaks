package org.polyfrost.soundtweaks.mixins;

import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.ResourceLocation;
import org.polyfrost.soundtweaks.SoundTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PositionedSound.class)
public abstract class PositionedSoundMixin {

    @Shadow
    public abstract ResourceLocation getSoundLocation();

    @Inject(method = "getVolume", at = @At("RETURN"), cancellable = true)
    private void soundtweaks$modifyVolume(CallbackInfoReturnable<Float> cir) {
        Float modifier = SoundTweaks.Companion.getVolumes().get(getSoundLocation());

        if (modifier == null || modifier == 100.0f) {
            return;
        }

        cir.setReturnValue(cir.getReturnValueF() * (modifier / 100));
    }

}
