import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.api.extension.LibraryAndroidComponentsExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()

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
        all {
            onAny {
                exclude("androidx.sqlite:sqlite", "androidx.drawerlayout:drawerlayout")
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val projectJvmTarget = JavaVersion.VERSION_1_8
val projectJvmTargetString = projectJvmTarget.toString()

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
        jvmTarget = projectJvmTargetString
    }

    pluginManager.configureSpotlessIntegration(subProject = project)

    tasks.withType<KotlinCompile> {
        dependsOn("spotlessKotlinApply")
        sourceCompatibility = projectJvmTargetString
        targetCompatibility = projectJvmTargetString

        kotlinOptions {
            jvmTarget = projectJvmTargetString
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
        }
    }

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
    val spotlessConfiguration: (AppliedPlugin) -> Unit = {
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

fun PluginContainer.configureAppAndModules(subProject: Project) = apply {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                subProject.extensions
                    .getByType<AppExtension>()
                    .applyBaseCommons()
            }
            is LibraryPlugin -> {
                subProject.disableAndroidTests()
                subProject.extensions
                    .getByType<LibraryExtension>()
                    .applyLibraryCommons()
            }
        }
    }
}

fun Project.disableAndroidTests() {
    val extension = project.extensions
        .getByName("androidComponents") as LibraryAndroidComponentsExtension
    extension.beforeAndroidTests(extension.selector().withBuildType("debug")) {
        it.enabled = false
    }
}

fun AppExtension.applyAppCommons() = apply { applyBaseCommons()}
fun LibraryExtension.applyLibraryCommons() = apply { applyBaseCommons() }

fun BaseExtension.applyBaseCommons() = apply {
    compileSdkVersion(Android.Sdk.COMPILE)

    defaultConfig.apply {
        minSdkVersion(Android.Sdk.MIN)
        targetSdkVersion(Android.Sdk.TARGET)
    }

    compileOptions.apply {
        sourceCompatibility = projectJvmTarget
        targetCompatibility = projectJvmTarget
    }
}
