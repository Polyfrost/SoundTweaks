package org.polyfrost.soundtweaks.config

import org.polyfrost.oneconfig.api.config.v1.Config
import org.polyfrost.oneconfig.api.config.v1.Properties
import org.polyfrost.oneconfig.api.config.v1.Tree
import org.polyfrost.oneconfig.api.config.v1.Visualizer
import org.polyfrost.oneconfig.utils.v1.dsl.visualizer
import org.polyfrost.soundtweaks.SoundTweaks
import org.polyfrost.soundtweaks.SoundTweaks.Companion.getSounds
import org.polyfrost.soundtweaks.SoundTweaks.Companion.volumes

class SoundTweaksConfig : Config("${SoundTweaks.ID}_${SoundTweaks.MC}.json", SoundTweaks.NAME, Category.QOL) {

    @Suppress("UnstableApiUsage")
    override fun makeTree(): Tree {
        return super.makeTree().apply {
            getSounds().forEach { (location, sound) ->
                put(
                    Properties.functional(
                        { volumes[location] ?: 100.0f },
                        { volumes[location] = it },
                        location.toString().replace(".", "_"),
                        location.toString(),
                        type = Float::class.java
                    ).apply {
                        visualizer = Visualizer.SliderVisualizer::class.java
                        metadata?.put("min", 0f)
                        metadata?.put("max", 200f)
                        metadata?.put("step", 1f)
                    }
                )
            }
        }
    }

}