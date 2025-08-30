package org.polyfrost.soundtweaks.mixins;

import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(SoundRegistry.class)
public interface SoundRegistryAccessor {

    @Accessor("soundRegistry")
    Map<ResourceLocation, SoundEventAccessorComposite> getSounds();

}
