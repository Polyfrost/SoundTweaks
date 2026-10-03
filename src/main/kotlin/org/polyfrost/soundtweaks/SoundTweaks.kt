package org.polyfrost.soundtweaks

import net.fabricmc.api.ModInitializer
import net.minecraft.client.Minecraft
import net.minecraft.resources.Identifier
import org.polyfrost.oneconfig.api.event.v1.EventManager
import org.polyfrost.oneconfig.api.event.v1.events.InitializationEvent
import org.polyfrost.oneconfig.api.event.v1.events.ResourceFinishedLoading
import org.polyfrost.soundtweaks.config.SoundTweaksConfig
import org.polyfrost.soundtweaks.mixins.SoundManagerAccessor
//? if 1.8.9
//import org.polyfrost.soundtweaks.mixins.SoundRegistryAccessor

object SoundTweaks : ModInitializer {
    const val ID = "@MOD_ID@"
    const val NAME = "@MOD_NAME@"

    @JvmStatic
    var config: SoundTweaksConfig? = null
    val volumes = mutableMapOf<Identifier, Float>()

    override fun onInitialize() {
        // on 1.8 this event loads config too early (before sounds are registered) so it's handled separately
        //? if > 1.8.9 {
        EventManager.register(ResourceFinishedLoading::class) { _ ->
            loadConfig()
        }
        //?}
    }

    fun loadConfig() {
        if (config == null) {
            config = SoundTweaksConfig()
        }
        config?.preload()
    }

    fun getSounds(): Map<Identifier, Any> {
        val soundManager = Minecraft.getInstance().soundManager
        val registry = (soundManager as? SoundManagerAccessor)?.registry ?: return mutableMapOf()
        //? if 1.8.9 {
        /*return (registry as SoundRegistryAccessor).sounds
        *///?} else {
         return registry
        //?}
    }
}
