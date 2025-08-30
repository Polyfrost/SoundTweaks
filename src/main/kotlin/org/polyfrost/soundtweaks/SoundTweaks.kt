package org.polyfrost.soundtweaks

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
//#endif
import dev.deftu.omnicore.client.OmniClient.getInstance
import net.minecraft.client.audio.SoundEventAccessorComposite
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
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
    ) {
        // Forge requires the config to be initialized during boot, fabric doesn't
        // since im alr doing this, a lateinit var might be better..?
        config
    }
    companion object {
        const val ID = "@MOD_ID@"
        const val NAME = "@MOD_NAME@"
        const val VERSION = "@MOD_VERSION@"
        const val MC = "@MC_VERSION@"

        @JvmStatic
        val config: SoundTweaksConfig by lazy { SoundTweaksConfig() }

        fun getSounds(): MutableMap<ResourceLocation, SoundEventAccessorComposite> {
            val registry = (getInstance().soundHandler as SoundHandlerAccessor).getSoundRegistry()
            return (registry as SoundRegistryAccessor).getSounds()
        }
    }
}