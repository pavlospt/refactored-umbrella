import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        jcenter()

    }
    dependencies {
        classpath(GradlePlugins.ANDROID)
        classpath(kotlin("gradle-plugin", version = GradlePlugins.Versions.KOTLIN))
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version GradlePlugins.Versions.DETEKT apply false
    id("com.diffplug.gradle.spotless") version GradlePlugins.Versions.SPOTLESS apply false
}

tasks.withType<Detekt>().all {
    jvmTarget = "1.8"
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    pluginManager.configureKotlinModules(subProject = project)
    pluginManager.configureKaptCache(subProject = project)
    project.plugins.configureAppAndModules(subProject = project)
}

fun PluginManager.configureKotlinModules(subProject: Project) = apply {
    val kotlinModulesConfiguration: (AppliedPlugin) -> Unit = {
        subProject.pluginManager.apply(DetektPlugin::class.java)
        subProject.configure<DetektExtension> {
            toolVersion = GradlePlugins.Versions.DETEKT
            input = subProject.files("src/main/java")
            config = subProject.rootProject.files("config/detekt/detekt.yml")
            failFast = true
            reports {
                val rootProjectDir = subProject.rootProject.projectDir
                html.destination =
                    subProject.file("$rootProjectDir/build/reports/detekt/detekt.html")
                html.enabled = true
            }
        }
        subProject.pluginManager.apply(SpotlessPlugin::class.java)
        subProject.configure<SpotlessExtension> {
            kotlin {
                target("src/**/*.kt")
                ktlint()
                trimTrailingWhitespace()
                endWithNewline()
            }
        }
        subProject.tasks.withType<KotlinCompile>().configureEach {
            dependsOn("spotlessKotlinApply")
            kotlinOptions {
                javaParameters = true
                jvmTarget = "1.8"
            }
        }
    }

    withPlugin("kotlin-android", kotlinModulesConfiguration)
    withPlugin("kotlin-jvm", kotlinModulesConfiguration)
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
        when (this) {
            is AppPlugin -> {
                subProject.extensions
                    .getByType<AppExtension>()
                    .apply { applyAppCommons() }
            }
            is LibraryPlugin -> {
                subProject.extensions
                    .getByType<LibraryExtension>()
                    .apply { applyLibraryCommons() }
            }
        }
    }
}

fun AppExtension.applyAppCommons() = applyBaseCommons()
fun LibraryExtension.applyLibraryCommons() = applyBaseCommons()

fun BaseExtension.applyBaseCommons() {
    compileSdkVersion(Android.Sdk.COMPILE)

    defaultConfig.apply {
        minSdkVersion(Android.Sdk.MIN)
        targetSdkVersion(Android.Sdk.TARGET)
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
