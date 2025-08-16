package org.polyfrost.soundtweaks.config;

import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.soundtweaks.SoundTweaks;

public class SoundTweaksConfig extends Config {
    private final static String CONFIG_NAME = SoundTweaks.ID + "_" + SoundTweaks.MC + ".json";

    public SoundTweaksConfig() {
        super(CONFIG_NAME, SoundTweaks.NAME, Category.QOL);

        // im actually SO CLUELESS
        // on how to add config options
    }
}
