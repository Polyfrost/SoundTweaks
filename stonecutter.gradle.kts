plugins {
    id("dev.kikugie.stonecutter")
}
stonecutter active "26.1"

stonecutter parameters {
    swaps["mod_version"] = "\"" + property("version") + "\";"
    swaps["minecraft"] = "\"" + node.metadata.version + "\";"

    replacements.regex {
        direction = eval(current.version, "< 1.21.11")
        replace("import net.minecraft.resources.Identifier(?!;)", "import net.minecraft.resources.ResourceLocation as Identifier")
        reverse("import net.minecraft.resources.ResourceLocation as Identifier", "import net.minecraft.resources.Identifier")
    }
    replacements.regex {
        direction = eval(current.version, "< 26.1")
        id = "identifier"
        replace(
            "Identifier", "ResourceLocation",
            "ResourceLocation", "Identifier"
        )
    }
}
