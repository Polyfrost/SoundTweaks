package org.polyfrost.soundtweaks

import net.fabricmc.fabric.api.resource.v1.ResourceLoader
import net.fabricmc.api.ModInitializer
import net.kyori.adventure.platform.modcommon.impl.client.BossHealthOverlayBridge.listener
import net.minecraft.client.Minecraft
import net.minecraft.client.sounds.WeighedSoundEvents
import net.minecraft.resources.Identifier
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import org.polyfrost.soundtweaks.config.SoundTweaksConfig
import org.polyfrost.soundtweaks.mixins.SoundManagerAccessor


object SoundTweaks : ModInitializer, ResourceManagerReloadListener {
    const val ID = "@MOD_ID@"
    const val NAME = "@MOD_NAME@"
    const val VERSION = "@MOD_VERSION@"
    const val MC = "@MC_VERSION@"

    val volumes = mutableMapOf<Identifier, Float>()

    override fun onInitialize() {
        val id = Identifier.fromNamespaceAndPath(ID, "sound_getter")
        //? >= 26.1 {
        ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(id, this)
        //? } else
        //ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloader(id, this)
    }

    override fun onResourceManagerReload(resourceManager: ResourceManager) {
        config = SoundTweaksConfig()
        config.preload()
        println("meow")
    }


    @JvmStatic
    lateinit var config: SoundTweaksConfig

    fun getSounds(): MutableMap<Identifier, WeighedSoundEvents> {
        val soundManager = Minecraft.getInstance().soundManager
        return (soundManager as? SoundManagerAccessor)?.registry ?: mutableMapOf()
    }
}
