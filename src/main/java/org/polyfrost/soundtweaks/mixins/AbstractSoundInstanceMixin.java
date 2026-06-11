package org.polyfrost.soundtweaks.mixins;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.resources.Identifier;
import org.polyfrost.soundtweaks.SoundTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSoundInstance.class)
public abstract class AbstractSoundInstanceMixin {

    @Shadow
    public abstract Identifier getIdentifier();

    @Inject(method = "getVolume", at = @At("RETURN"), cancellable = true)
    private void soundtweaks$modifyVolume(CallbackInfoReturnable<Float> cir) {
        Float modifier = SoundTweaks.INSTANCE.getVolumes().get(getIdentifier());

        if (modifier == null || modifier == 100.0f) {
            return;
        }

        cir.setReturnValue(cir.getReturnValueF() * (modifier / 100));
    }

}
