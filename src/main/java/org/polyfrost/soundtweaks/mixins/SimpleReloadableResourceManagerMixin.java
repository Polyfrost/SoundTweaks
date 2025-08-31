package org.polyfrost.soundtweaks.mixins;

import net.minecraft.client.resources.SimpleReloadableResourceManager;
import org.polyfrost.soundtweaks.SoundTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SimpleReloadableResourceManager.class)
public class SimpleReloadableResourceManagerMixin {

    @Inject(method = "reloadResources", at = @At("TAIL"))
    private void onReload(CallbackInfo ci) {
        SoundTweaks.onReloadResourceManager();
    }

}
