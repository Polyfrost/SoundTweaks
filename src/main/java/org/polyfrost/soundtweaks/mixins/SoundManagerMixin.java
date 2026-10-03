package org.polyfrost.soundtweaks.mixins;



//? if 1.8.9 {
/*import net.minecraft.client.resource.manager.ResourceManager;
import net.minecraft.client.sound.system.SoundManager;
import org.polyfrost.soundtweaks.SoundTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(SoundManager.class)
public class SoundManagerMixin {
    @Inject(method = "reload", at = @At("RETURN"))
    private void soundtweaks$loadConfig(ResourceManager resourceManager, CallbackInfo ci) {
        SoundTweaks.INSTANCE.loadConfig();
    }
}
*///?}
