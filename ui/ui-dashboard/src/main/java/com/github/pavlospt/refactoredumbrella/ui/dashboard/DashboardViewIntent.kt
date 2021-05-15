package com.github.pavlospt.refactoredumbrella.ui.dashboard

sealed class DashboardViewIntent {
    object None : DashboardViewIntent()
    object Refresh : DashboardViewIntent()
}
