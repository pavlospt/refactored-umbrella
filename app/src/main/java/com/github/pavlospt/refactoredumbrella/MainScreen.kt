package com.github.pavlospt.refactoredumbrella

import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.github.pavlospt.refactoredumbrella.navigation.NavControllerAmbient
import com.github.pavlospt.refactoredumbrella.navigation.NavRoute
import com.github.pavlospt.refactoredumbrella.navigation.ScaffoldStateAmbient
import com.github.pavlospt.refactoredumbrella.navigation.routeEntries

@Composable
fun MainScreen(navGraphBuilder: NavGraphBuilder.() -> Unit) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                routeEntries.forEach { routeEntry ->
                    BottomNavigationItem(
                        label = { Text(stringResource(routeEntry.routeName)) },
                        icon = {},
                        selected = currentRoute == routeEntry.route,
                        onClick = {
                            // This is the equivalent to popUpTo the start destination
                            navController.popBackStack(
                                navController.graph.startDestination,
                                false
                            )

                            // This if check gives us a "singleTop" behavior where we do not create a
                            // second instance of the composable if we are already on that destination
                            if (currentRoute != routeEntry.route) {
                                navController.navigate(routeEntry.route)
                            }
                        }
                    )
                }
            }
        }
    ) {
        Providers(NavControllerAmbient provides navController) {
            Providers(ScaffoldStateAmbient provides scaffoldState) {
                NavHost(
                    navController = navController,
                    startDestination = NavRoute.Home.route,
                    builder = navGraphBuilder
                )
            }
        }
    }
}
