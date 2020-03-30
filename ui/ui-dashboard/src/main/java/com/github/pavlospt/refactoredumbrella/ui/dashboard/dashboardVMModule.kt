package com.github.pavlospt.refactoredumbrella.ui.dashboard

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardVMModule = module {
    viewModel {
        DashboardViewModel(
            refreshGithubReposUseCase = get(),
            observeGithubReposUseCase = get()
        )
    }
}
