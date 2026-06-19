package org.polyfrost.soundtweaks

import net.fabricmc.api.ModInitializer
import net.minecraft.client.Minecraft
import net.minecraft.client.sounds.WeighedSoundEvents
import org.polyfrost.oneconfig.api.event.v1.EventManager
import org.polyfrost.oneconfig.api.event.v1.events.ResourceFinishedLoading
import org.polyfrost.soundtweaks.config.SoundTweaksConfig
import org.polyfrost.soundtweaks.mixins.SoundManagerAccessor
import net.minecraft.resources.Identifier

object SoundTweaks : ModInitializer {
    const val ID = "@MOD_ID@"
    const val NAME = "@MOD_NAME@"
    const val VERSION = "@MOD_VERSION@"
    const val MC = "@MC_VERSION@"

    val volumes = mutableMapOf<Identifier, Float>()

    override fun onInitialize() {
        EventManager.register(ResourceFinishedLoading::class) { _ ->
            config = SoundTweaksConfig()
            config?.preload()
            println("meow")
        }
    }

    @JvmStatic
    var config: SoundTweaksConfig? = null

    fun getSounds(): MutableMap<Identifier, WeighedSoundEvents> {
        val soundManager = Minecraft.getInstance().soundManager
        return (soundManager as? SoundManagerAccessor)?.registry ?: mutableMapOf()
    }
}
