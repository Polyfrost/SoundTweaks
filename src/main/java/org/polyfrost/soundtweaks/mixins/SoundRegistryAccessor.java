package org.polyfrost.soundtweaks.mixins;


//? if 1.8.9 {
/*import net.minecraft.client.sound.SoundPool;
import net.minecraft.client.sound.system.SoundRegistry;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;



@Mixin(SoundRegistry.class)
public interface SoundRegistryAccessor {
    @Accessor("sounds")
    Map<Identifier, SoundPool> getSounds();
}
*///?}
