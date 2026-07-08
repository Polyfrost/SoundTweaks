package org.polyfrost.soundtweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.sounds.SoundEngine;
import org.polyfrost.soundtweaks.SoundTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {
    @WrapOperation(
        method = "calculateVolume(FLnet/minecraft/sounds/SoundSource;)F",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F", ordinal = 0)
    )
    private float soundtweaks$uncapVolume(float f, float g, float h, Operation<Float> original) {
        if (SoundTweaks.getConfig() != null && SoundTweaks.getConfig().removeVolumeCap) {
            return Math.max(f, g);
        }
        return original.call(f, g, h);
    }
}
