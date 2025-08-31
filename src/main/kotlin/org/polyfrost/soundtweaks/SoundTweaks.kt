package org.polyfrost.soundtweaks

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
//#endif
import dev.deftu.omnicore.client.OmniClient.getInstance
import net.minecraft.client.audio.SoundEventAccessorComposite
import net.minecraft.util.ResourceLocation
import org.polyfrost.soundtweaks.config.SoundTweaksConfig
import org.polyfrost.soundtweaks.mixins.SoundHandlerAccessor
import org.polyfrost.soundtweaks.mixins.SoundRegistryAccessor

//#if FORGE-LIKE
@Mod(modid = SoundTweaks.ID, name = SoundTweaks.NAME, version = SoundTweaks.VERSION)
//#endif
class SoundTweaks
//#if FABRIC
//$$ : ModInitializer
//#endif
{
    //#if FABRIC
    //$$ override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    fun onInitialize(
        //#if FORGE
        event: FMLInitializationEvent
        //#endif
    ) {}

    companion object {
        const val ID = "@MOD_ID@"
        const val NAME = "@MOD_NAME@"
        const val VERSION = "@MOD_VERSION@"
        const val MC = "@MC_VERSION@"

        val volumes = mutableMapOf<ResourceLocation, Float>()

        @JvmStatic
        lateinit var config: SoundTweaksConfig

        @JvmStatic
        fun onReloadResourceManager() {
            config = SoundTweaksConfig()
            println("meow")
        }

        fun getSounds(): MutableMap<ResourceLocation, SoundEventAccessorComposite> {
            val registry = (getInstance().soundHandler as? SoundHandlerAccessor)?.getSoundRegistry()
            return (registry as? SoundRegistryAccessor)?.getSounds() ?: mutableMapOf()
        }
    }
}