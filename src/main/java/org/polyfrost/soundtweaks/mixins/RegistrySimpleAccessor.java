package org.polyfrost.soundtweaks.mixins;

import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RegistrySimple.class)
public interface RegistrySimpleAccessor {

    @Accessor("registryObjects")
    Map<ResourceLocation, SoundEventAccessorComposite> getRegistryObjects();

}
