plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "26.3" /* [SC] DO NOT EDIT */

stonecutter parameters {
    replacements {
        string(current.parsed > "1.8.9") {
            replace("net.minecraft.resource", "net.minecraft.resources")
        }

        string(current.parsed <= "1.8.9" || current.parsed >= "1.21.11") {
            replace("ResourceLocation", "Identifier")
        }

        string(current.parsed >= "26.1") {
            replace("classTweaker v2 named", "classTweaker v2 official")
        }
    }
}

stonecutter tasks {
    order("publishModrinth")
}
