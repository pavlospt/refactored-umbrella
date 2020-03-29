package com.github.pavlospt.refactoredumbrella.ui.dashboard

sealed class DashboardViewIntent {
    object Refresh : DashboardViewIntent()
}
