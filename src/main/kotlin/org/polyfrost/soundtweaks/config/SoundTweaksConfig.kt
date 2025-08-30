package org.polyfrost.soundtweaks.config

import net.minecraft.util.ResourceLocation
import org.polyfrost.oneconfig.api.config.v1.KtConfig
import org.polyfrost.oneconfig.api.config.v1.Properties
import org.polyfrost.soundtweaks.SoundTweaks

class SoundTweaksConfig : KtConfig("${SoundTweaks.ID}_${SoundTweaks.MC}", SoundTweaks.NAME, Category.QOL) {

    val volumes = mutableMapOf<ResourceLocation, Float>()

    init {
        SoundTweaks.getSounds().forEach { (location, sound) ->
            tree.put(
                Properties.functional(
                    { volumes[location] ?: 1.0f },
                    { volumes[location] = it },
                    location.toString(),
                    location.toString(),
                    type = Float::class.java
                )
            )
        }
    }

}