plugins {
    idea
    java
    id("gg.essential.loom") version "0.10.0.+"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.8.22"
}

// Constants
group = "net.jerrydev"
version = "0.5.5"

// val baseGroup = "net.jerrydev"
val mcVersion: String = "1.8.9"
// val version: String = "0.1.2-beta.2"
val mixinGroup = "$group.mixin"
val modid: String = "baputils"

// Toolchains:
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

// Minecraft configuration:
loom {
    log4jConfigs.from(file("log4j2.xml"))
    launchConfigs {
        "client" {
            // If you don't want mixins, remove these lines
            property("mixin.debug", "true")
            property("asmhelper.verbose", "true")
            //arg("--tweakClass", "org.spongepowered.asm.launch.MixinTweaker")
            arg("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker")
            arg("--mixin", "mixins.$modid.json")
        }
    }
    forge {
        pack200Provider.set(dev.architectury.pack200.java.Pack200Adapter())
        // If you don't want mixins, remove this lines
        mixinConfig("mixins.$modid.json")
    }
    // If you don't want mixins, remove these lines
    mixin {
        defaultRefmapName.set("mixins.$modid.refmap.json")
    }
}

tasks.compileJava {
    dependsOn(tasks.processResources)
}

sourceSets.main {
    kotlin.destinationDirectory.set(java.destinationDirectory)
    java.srcDir(file("$projectDir/src/main/kotlin"))
    output.setResourcesDir(java.destinationDirectory)
}

// Dependencies:

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/maven/")
    maven("https://repo.essential.gg/repository/maven-public")

    // If you don't want to log in with your real minecraft account, remove this line
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

val shadowImpl: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
    implementation("gg.essential:vigilance-1.8.9-forge:284")
    // vigilance dependencies
    implementation("gg.essential:elementa-1.8.9-forge:590")
    // vigilance elementa
    implementation("gg.essential:universalcraft-1.8.9-forge:211")

    compileOnly("gg.essential:essential-1.8.9-forge:12132+g6e2bf4dc5")
    shadowImpl("gg.essential:loader-launchwrapper:1.2.1")

    //shadowImpl("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")

    // If you don't want mixins, remove these lines
    shadowImpl("org.spongepowered:mixin:0.7.11-SNAPSHOT") {
        isTransitive = false
    }
    annotationProcessor("org.spongepowered:mixin:0.8.5-SNAPSHOT")

    // If you don't want to log in with your real minecraft account, remove this line
    runtimeOnly("me.djtheredstoner:DevAuth-forge-legacy:1.1.2")

}

// Tasks:

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}

tasks.withType(Jar::class) {
    archiveBaseName.set(modid)
    manifest.attributes.run {
        this["FMLCorePluginContainsFMLMod"] = "true"
        this["ForceLoadAsMod"] = "true"

        // If you don't want mixins, remove these lines
        //this["TweakClass"] = "org.spongepowered.asm.launch.MixinTweaker"
        this["TweakClass"] = "gg.essential.loader.stage0.EssentialSetupTweaker"
        this["MixinConfigs"] = "mixins.$modid.json"
    }
}

tasks.processResources {
    inputs.property("version", project.version)
    inputs.property("mcversion", mcVersion)
    inputs.property("modid", modid)
    inputs.property("mixinGroup", mixinGroup)

    filesMatching(listOf("mcmod.info", "mixins.$modid.json")) {
        expand(inputs.properties)
    }

    rename("(.+_at.cfg)", "META-INF/$1")
}


val remapJar by tasks.named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
    archiveClassifier.set("")
    from(tasks.shadowJar)
    input.set(tasks.shadowJar.get().archiveFile)
}


tasks.jar {
    archiveClassifier.set("without-deps")
    destinationDirectory.set(layout.buildDirectory.dir("badjars"))
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    destinationDirectory.set(layout.buildDirectory.dir("badjars")) // is this needed
    archiveBaseName.set("BapUtils")
    archiveClassifier.set("dev")
    configurations = listOf(shadowImpl)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    doLast {
        configurations.forEach {
            println("Copying jars into mod: ${it.files}")
        }
    }

    println("Relocating packages to net.jerrydev.<>")
    relocate("gg.essential:vigilance", "net.jerrydev.vigilance")
    // vigilance dependencies
    relocate("gg.essential:elementa", "net.jerrydev.elementa")
    // elementa dependencies
    relocate("gg.essential:universalcraft", "net.jerrydev.universalcraft")

    // If you want to include other dependencies and shadow them, you can relocate them in here
    fun relocate(name: String) = relocate(name, "$group.deps.$name")
}

tasks.assemble.get().dependsOn(tasks.remapJar)
