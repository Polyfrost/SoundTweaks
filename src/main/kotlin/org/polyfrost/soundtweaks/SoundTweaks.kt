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

    val volumes = mutableMapOf<Identifier, Float>()

    override fun onInitialize() {
        // Event never fires on 1.21.1 and 1.21.4 (OneConfig #749)
        EventManager.register(ResourceFinishedLoading::class) { _ ->
            if (config == null) {
                config = SoundTweaksConfig()
            }
            config?.preload()
        }
    }

    @JvmStatic
    var config: SoundTweaksConfig? = null

    fun getSounds(): MutableMap<Identifier, WeighedSoundEvents> {
        val soundManager = Minecraft.getInstance().soundManager
        return (soundManager as? SoundManagerAccessor)?.registry ?: mutableMapOf()
    }
}
