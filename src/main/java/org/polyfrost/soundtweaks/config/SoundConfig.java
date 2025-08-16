package org.polyfrost.soundtweaks.config;

import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

// the config for each sound, I think?
// idek if this can be used this way :sob:
public class SoundConfig {
    public final String title;

    public SoundConfig(String title) {
        this.title = title;
    }

    @Switch(title = "Enabled")
    public boolean enabled = false;

    @Slider(title = "Volume", max = 1.0f)
    public float volume = 1.0f;
}
