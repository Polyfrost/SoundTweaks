package org.polyfrost.soundtweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//? if 1.8.9 {
/*import net.minecraft.client.sound.system.SoundEngine;
*///?} else {
 import net.minecraft.client.sounds.SoundEngine;
//?}

import org.polyfrost.soundtweaks.SoundTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(SoundEngine.class)
public class SoundEngineMixin {
    //? if 1.8.9 {
    /*@WrapOperation(
        method = "getVolume(Lnet/minecraft/client/sound/instance/SoundInstance;Lnet/minecraft/client/sound/Sound;Lnet/minecraft/client/sound/SoundCategory;)F",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D")
    )
    private double soundtweaks$uncapVolume(double f, double g, double h, Operation<Double> original) {
    *///?} else {
    @WrapOperation(
        method = "calculateVolume(FLnet/minecraft/sounds/SoundSource;)F",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F", ordinal = 0)
    )
    private float soundtweaks$uncapVolume(float f, float g, float h, Operation<Float> original) {
        //?}
        if (SoundTweaks.getConfig() != null && SoundTweaks.getConfig().removeVolumeCap) {
            return Math.max(f, g);
        }
        return original.call(f, g, h);
    }
}
