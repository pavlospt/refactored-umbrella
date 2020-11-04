import ext.composeProject

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android.buildFeatures.compose = true

composeProject()

dependencies {
    implementation(Deps.AndroidX.Annotation.ANNOTATION)
}
