package com.github.pavlospt.refactoredumbrella.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.staticAmbientOf
import androidx.navigation.NavController

val NavControllerAmbient = staticAmbientOf<NavController>()

val ScaffoldStateAmbient = staticAmbientOf<ScaffoldState>()
