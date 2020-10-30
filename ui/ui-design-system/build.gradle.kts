import ext.composeLibrary

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android.buildFeatures.compose = true

composeLibrary()