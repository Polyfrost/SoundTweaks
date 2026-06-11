package org.polyfrost.soundtweaks.config

import org.apache.commons.lang3.text.WordUtils
import org.polyfrost.oneconfig.api.config.v1.Config
import org.polyfrost.oneconfig.api.config.v1.Properties
import org.polyfrost.oneconfig.api.config.v1.Tree
import org.polyfrost.oneconfig.api.config.v1.Visualizer
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch
import org.polyfrost.oneconfig.api.config.v1.dsl.subcategory
import org.polyfrost.oneconfig.api.config.v1.dsl.visualizer
import org.polyfrost.soundtweaks.SoundTweaks
import org.polyfrost.soundtweaks.SoundTweaks.getSounds
import org.polyfrost.soundtweaks.SoundTweaks.volumes

class SoundTweaksConfig : Config("${SoundTweaks.ID}.json", SoundTweaks.NAME, Category.QOL) {

    // This shit does not safe/load correctly and idfk how
    @Switch(
        title = "Remove Volume Cap",
        description = "Allows sounds to bypass Minecrafts 100% volume limit.\nAlready played sounds require a restart to become louder.",
        subcategory = "General"
    )
    @JvmField
    var removeVolumeCap: Boolean = false

    @Suppress("UnstableApiUsage")
    override fun makeTree(): Tree {
        return super.makeTree().apply {
            getSounds().forEach { (location, sound) ->
                val pathParts = location.path.split('.')
                val groupName = pathParts.firstOrNull()?.toTitleCase() ?: "General"

                val optionTitle = if (pathParts.size > 1) {
                    pathParts.drop(1).joinToString(" ").toTitleCase()
                } else {
                    location.path.toTitleCase()
                }

                put(
                    Properties.functional(
                        { volumes[location] ?: 100.0f },
                        { volumes[location] = it },
                        location.toString().replace(":", "_").replace(".", "_"),
                        optionTitle,
                        description = "Internal ID: $location",
                        type = Float::class.javaObjectType
                    ).apply {
                        visualizer = Visualizer.SliderVisualizer::class.java
                        subcategory = groupName
                        metadata?.put("min", 0f)
                        metadata?.put("max", 500f)
                        metadata?.put("step", 5f)
                    }
                )
            }
        }
    }

    private fun String.toTitleCase() = WordUtils.capitalizeFully(this.replace("_", " "))
}
