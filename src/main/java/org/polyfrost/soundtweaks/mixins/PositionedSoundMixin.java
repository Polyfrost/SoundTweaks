package org.polyfrost.soundtweaks.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.ResourceLocation;
import org.polyfrost.soundtweaks.SoundTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PositionedSound.class)
public abstract class PositionedSoundMixin {

    @Shadow
    public abstract ResourceLocation getSoundLocation();

    @ModifyReturnValue(method = "getVolume", at = @At("RETURN"))
    private float soundtweaks$modifyVolume(float original) {
        Float modifier = SoundTweaks.Companion.getVolumes().get(getSoundLocation());

        if (modifier == null || modifier == 100.0f) {
            return original;
        }

        return original * (modifier / 100);
    }

}
