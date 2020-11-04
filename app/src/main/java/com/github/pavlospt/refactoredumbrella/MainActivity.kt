package com.github.pavlospt.refactoredumbrella

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.composable
import com.github.pavlospt.refactoredumbrella.navigation.NavRoute
import com.github.pavlospt.refactoredumbrella.ui.dashboard.DashboardScreen
import com.github.pavlospt.refactoredumbrella.ui.dashboard.DashboardViewModel
import com.github.pavlospt.refactoredumbrella.ui.home.HomeScreen
import com.github.pavlospt.refactoredumbrella.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                navGraphBuilder = {
                    composable(NavRoute.Home.route) {
                        HomeScreen(homeViewModel = homeViewModel)
                    }
                    composable(NavRoute.Dashboard.route) {
                        DashboardScreen(dashboardViewModel = dashboardViewModel)
                    }
                })
        }
    }
}
