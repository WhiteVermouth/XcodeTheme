import org.jetbrains.changelog.Changelog
import org.jetbrains.intellij.tasks.RunPluginVerifierTask

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.13.3"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "2.0.0"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    updateSinceUntilBuild.set(false)
}

changelog {
    version.set(properties("pluginVersion"))
    path.set("${project.projectDir}/CHANGELOG.md")
    groups.set(emptyList())
}

tasks {
    buildSearchableOptions {
        enabled = false
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))

        val description = """
            <div>
              <p>Xcode Light &amp; Dark themes for JetBrains</p>
              <h2>Licence</h2>
              <p>
                <a href="https://raw.githubusercontent.com/WhiteVermouth/XcodeTheme/master/LICENSE">Apache-2.0 Licence</a>
              </p>
              <h2>Donation</h2>
              <p>If you like this plugin, you can <a href="https://www.buymeacoffee.com/nszihan">buy me a cup of coffee</a>. Thank you!</p>
            </div>
        """.trimIndent()

        pluginDescription.set(description)
        changeNotes.set(provider { changelog.renderItem(changelog.getLatest(), Changelog.OutputType.HTML) })
    }

    runPluginVerifier {
        ideVersions.set(properties("pluginVerifierIdeVersions").split(",").map(String::trim).filter(String::isNotEmpty))
        failureLevel.set(listOf(RunPluginVerifierTask.FailureLevel.COMPATIBILITY_PROBLEMS, RunPluginVerifierTask.FailureLevel.INVALID_PLUGIN))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getProperty("jetbrains.token"))
    }
}
