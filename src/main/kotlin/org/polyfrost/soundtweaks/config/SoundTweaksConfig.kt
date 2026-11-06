package org.polyfrost.soundtweaks.config

import net.minecraft.client.Minecraft
import net.minecraft.resources.Identifier
//? if > 1.8.9 {
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.client.resources.sounds.SoundInstance
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundSource
//?} else {
// import net.minecraft.client.sound.instance.SimpleSoundInstance
//?}
import org.polyfrost.oneconfig.api.config.v1.Config
import org.polyfrost.oneconfig.api.config.v1.Properties
import org.polyfrost.oneconfig.api.config.v1.Tree
import org.polyfrost.oneconfig.api.config.v1.Visualizer
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch
import org.polyfrost.oneconfig.api.config.v1.dsl.category
import org.polyfrost.oneconfig.api.config.v1.dsl.visualizer
import org.polyfrost.oneconfig.api.notifications.v1.Notifications
import org.polyfrost.soundtweaks.SoundTweaks
import org.polyfrost.soundtweaks.SoundTweaks.getSounds
import org.polyfrost.soundtweaks.SoundTweaks.volumes

class SoundTweaksConfig : Config("${SoundTweaks.ID}.json", SoundTweaks.NAME, Category.QOL) {
    @Switch(
        title = "Enabled",
        subcategory = "General"
    )
    var enabled = true

    // TODO: Debug the below (does it still happen?)
    // This shit does not safe/load correctly and idfk how
    // might have something to do with @JvmField
    @Switch(
        title = "Remove Volume Cap",
        description = "Allows sounds to bypass Minecraft's 100% volume limit.\nAlready played sounds require a restart to become louder.",
        subcategory = "General"
    )
    @JvmField
    var removeVolumeCap: Boolean = false

    @Suppress("UnstableApiUsage")
    override fun makeTree(): Tree {
        return super.makeTree().apply {
            getSounds().forEach { (location, _) ->
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
                        // thank you scope
                        this.category = groupName
                        addMetadata("min", 0f)
                        addMetadata("max", 200f)
                        addMetadata("step", 0f)
                    }
                )
                put(
                    Properties.dummy(
                        location.toString().replace(":", "_").replace(".", "_") + "_button",
                        "Play $optionTitle",
                        description = "Plays $location at the given volume and default pitch.",

                    ).apply {
                        visualizer = Visualizer.ButtonVisualizer::class.java
                        // thank you scope
                        this.category = groupName
                        addMetadata("runnable", Runnable {
                            runCatching {
                                playSoundPreview(location)
                            }.onFailure {
                                Notifications.error("SoundTweaks", "Failed to play sound $location")
                                SoundTweaks.LOGGER.warn("Failed to play sound {}", location)
                            }
                        })
                        addMetadata("textKey", "Play")
                    }
                )
            }
        }
    }

    //@TODO sometimes doesn't work on 1.8.9
    //@TODO doesn't compile on 1.21.1
    private fun playSoundPreview(location: Identifier) {
        //? if > 1.8.9 {
         val instance = SimpleSoundInstance(location, SoundSource.MASTER, 1f, 1f, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true)
        //?} else {
        /*val instance = SimpleSoundInstance(location, 1f, 1f, 0f, 0f, 0f)
        *///?}
        Minecraft.getInstance().soundManager.play(instance)

    }

    private fun String.toTitleCase() = replace("_", " ")
        .split(" ")
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.titlecase() }
        }
}
