package org.polyfrost.soundtweaks.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.spongepowered.asm.mixin.MixinEnvironment
import org.spongepowered.asm.mixin.MixinEnvironment.Option
import org.spongepowered.asm.mixin.transformer.IMixinTransformer

//? if > 1.8.9 {
import net.minecraft.SharedConstants
import net.minecraft.server.Bootstrap
//?}

/**
 * Audits mixins without launching a full Minecraft client
 * Inspired by [Skyblocker](https://github.com/SkyblockerMod/Skyblocker)
 */
class MixinTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setupEnvironment() {
            //? if > 1.8.9 {
            SharedConstants.tryDetectVersion()
            Bootstrap.bootStrap()
            //?}
        }
    }

    @Test
    fun `mixins load successfully`() {
        val environment = MixinEnvironment.getCurrentEnvironment()
        Assertions.assertInstanceOf(
            IMixinTransformer::class.java,
            environment.activeTransformer,
        )
        // Dev refmap remapping retries failed selectors without the descriptor
        // Disable it so the audit matches production strictness
        environment.setOption(Option.REFMAP_REMAP, false)
        environment.audit()
    }
}
