package com.github.pavlospt.refactoredumbrella.navigation

import androidx.annotation.StringRes

sealed class NavRoute(val route: String, @StringRes val routeName: Int) {
    object Dashboard : NavRoute(route = "dashboard", routeName = R.string.dashboard_route_name)
    object Home : NavRoute(route = "home", routeName = R.string.home_route_name)
}

val routeEntries = listOf(NavRoute.Home, NavRoute.Dashboard)
