package ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.composeLibrary() {
    dependencies {
        "implementation"(Deps.Compose.UI_TOOLING)
        "implementation"(Deps.Compose.UI)
        "implementation"(Deps.Compose.FOUNDATION)
        "implementation"(Deps.Compose.MATERIAL)
        "implementation"(Deps.Compose.MATERIAL_ICONS_CORE)
        "implementation"(Deps.Compose.MATERIAL_ICONS_EXTENDED)
        "implementation"(Deps.Compose.RUNTIME_LIVEDATA)
    }
}