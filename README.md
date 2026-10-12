# SoundTweaks

[![Modrinth downloads](https://img.shields.io/modrinth/dt/7hJZhArg?style=flat&logo=modrinth&label=Modrinth&color=1bd96a)](https://modrinth.com/mod/soundtweaks)
[![CurseForge downloads](https://img.shields.io/curseforge/dt/1740840?style=flat&logo=curseforge&label=CurseForge&color=f16436)](https://www.curseforge.com/minecraft/mc-mods/soundtweaks)

SoundTweaks gives every sound in the game its own volume slider. You can mute one sound or make it quieter and leave the rest alone.

## Features

- Each sound the game has loaded gets a slider from 0% to 200%. The list includes sounds that resource packs and other mods add.
- A slider at 100% leaves the sound unchanged. 0% mutes it.
- SoundTweaks groups the sliders by the first part of the sound's ID. On current versions, `entity.creeper.primed` appears as Creeper Primed in the Entity group. Each slider's description shows the full ID.
- Minecraft caps the final volume of a sound at 100%. A slider above 100% makes a quiet sound louder, but it cannot pass that cap.
- The "Remove Volume Cap" option lifts the cap. It is off by default.

## Supported versions

- Minecraft 1.8.9 on Ornithe.
- Minecraft 1.21.1, 1.21.4, 1.21.5, 1.21.8, 1.21.10, 1.21.11, 26.1, 26.2 and 26.3 on Fabric.

SoundTweaks needs [OneConfig](https://modrinth.com/mod/oneconfig) and Fabric Language Kotlin. On Fabric it also needs Fabric API.

## Support

- Report bugs and ask questions on the [Polyfrost Discord](https://polyfrost.org/discord).
- Check [Polyfrost Status](https://status.polyfrost.org/) if a Polyfrost service is down.

## Building

```sh
./gradlew ":26.3:build"
```

Replace `26.3` with any supported Minecraft version. The build needs JDK 21 and JDK 25 installed.
