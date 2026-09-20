package org.polyfrost.soundtweaks.mixins;

//? if 1.8.9 {
/*import net.minecraft.client.sound.system.SoundManager;
import net.minecraft.client.sound.system.SoundRegistry;
*///?} else {
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.Identifier;
import java.util.Map;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SoundManager.class)
public interface SoundManagerAccessor {
    @Accessor("registry")
    //? if 1.8.9 {
    /*SoundRegistry getRegistry();
    *///?} else
     Map<Identifier, WeighedSoundEvents> getRegistry();
}
