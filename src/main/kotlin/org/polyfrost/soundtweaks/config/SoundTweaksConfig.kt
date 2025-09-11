package org.polyfrost.soundtweaks.config

import net.minecraft.client.resources.I18n
import org.apache.commons.lang3.text.WordUtils
import org.polyfrost.oneconfig.api.config.v1.Config
import org.polyfrost.oneconfig.api.config.v1.Properties
import org.polyfrost.oneconfig.api.config.v1.Tree
import org.polyfrost.oneconfig.api.config.v1.Visualizer
import org.polyfrost.oneconfig.utils.v1.dsl.subcategory
import org.polyfrost.oneconfig.utils.v1.dsl.visualizer
import org.polyfrost.soundtweaks.SoundTweaks
import org.polyfrost.soundtweaks.SoundTweaks.Companion.getSounds
import org.polyfrost.soundtweaks.SoundTweaks.Companion.volumes
import kotlin.jvm.java

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
                        location.resourcePath.replace(".", " ").replace("_", "").toTitleCase(),
                        type = Float::class.java
                    ).apply {
                        visualizer = Visualizer.SliderVisualizer::class.java
                        subcategory = sound.soundCategory.categoryName.toTitleCase()
                        metadata?.put("min", 0f)
                        metadata?.put("max", 200f)
                        metadata?.put("step", 1f)
                    }
                )
            }
        }
    }

    private fun String.toTitleCase() = WordUtils.capitalizeFully(this)

}