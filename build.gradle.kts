import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.api.extension.LibraryAndroidComponentsExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter()

    }
    dependencies {
        classpath(GradlePlugins.ANDROID)
        classpath(kotlin(module = GradlePlugins.Kotlin.ID, version = GradlePlugins.Kotlin.VERSION))
    }
}

plugins {
    GradlePlugins.plugins.forEach { gradlePlugin ->
        id(gradlePlugin.ID).version(gradlePlugin.VERSION).apply(gradlePlugin.APPLY)
    }
}

dependencyAnalysis {
    issues {
        onUsedTransitiveDependencies {
            fail("androidx.sqlite:sqlite", "androidx.drawerlayout:drawerlayout")
        }
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}

allprojects {
    repositories {
        google()
        maven("https://androidx.dev/snapshots/builds/7081083/artifacts/repository")
        jcenter()
    }
}

val projectJvmTarget = JavaVersion.VERSION_1_8.toString()

val analysisDir = files(projectDir)
val configFiles = files("$rootDir/config/detekt/detekt.yml")

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"
val depsFiles = "**/*Deps.kt"

subprojects {
    pluginManager.apply(DetektPlugin::class.java)

    tasks.withType<Detekt> {
        jvmTarget = projectJvmTarget
    }

    pluginManager.configureSpotlessIntegration(subProject = project)

    tasks.withType<KotlinCompile> {
        tasks.findByName("spotlessKotlinApply")
            ?.let { dependsOn(it) }
        sourceCompatibility = projectJvmTarget
        targetCompatibility = projectJvmTarget

        kotlinOptions {
            jvmTarget = projectJvmTarget
            useIR = true
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
    }

    pluginManager.configureKaptCache(subProject = project)
    project.plugins.configureAppAndModules(subProject = project)
}

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs the whole project at once."
    parallel = true
    config.setFrom(configFiles)
    setSource(analysisDir)
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    exclude(depsFiles)
    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
    }
}

fun PluginManager.configureSpotlessIntegration(subProject: Project) = apply {
    val spotlessConfiguration: (AppliedPlugin) -> Unit = spotless@{
        if (subProject.name.contains("design-system")) return@spotless
        subProject.pluginManager.apply(SpotlessPlugin::class.java)
        subProject.configure<SpotlessExtension> {
            kotlin {
                target("src/**/*.kt")
                ktlint()
                trimTrailingWhitespace()
                endWithNewline()
            }
        }
    }

    withPlugin("org.jetbrains.kotlin.android", spotlessConfiguration)
    withPlugin("org.jetbrains.kotlin.jvm", spotlessConfiguration)
}

fun PluginManager.configureKaptCache(subProject: Project) = apply {
    withPlugin("kotlin-kapt") {
        subProject.extensions
            .getByType<KaptExtension>()
            .apply { useBuildCache = true }
    }
}

fun PluginContainer.configureAppAndModules(subProject: Project) = apply {
    whenPluginAdded {
        val subprojectExtensions = subProject.extensions
        when (this) {
            is AppPlugin -> {
                with(subprojectExtensions) {
                    getByType<BaseExtension>().setupAndroidCommons()
                }
            }
            is LibraryPlugin -> {
                with(subprojectExtensions) {
                    getByType<LibraryAndroidComponentsExtension>().disableAndroidTests()
                    getByType<BaseExtension>().setupAndroidCommons()
                }
            }
        }
    }
}

fun LibraryAndroidComponentsExtension.disableAndroidTests() = apply {
    beforeAndroidTest(selector().withBuildType("debug")) {
        it.enabled = false
    }
}

fun BaseExtension.setupAndroidCommons() = apply {
    val composeVersion = SharedVersions.Compose.COMPOSE

    compileSdkVersion(Android.Sdk.COMPILE)

    defaultConfig.apply {
        minSdkVersion(Android.Sdk.MIN)
        targetSdkVersion(Android.Sdk.TARGET)

        composeOptions {
            kotlinCompilerVersion = GradlePlugins.Kotlin.VERSION
            kotlinCompilerExtensionVersion = composeVersion
        }
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
