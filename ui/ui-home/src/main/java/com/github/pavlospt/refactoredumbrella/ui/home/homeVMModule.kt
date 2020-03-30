package com.github.pavlospt.refactoredumbrella.ui.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeVMModule = module {
    viewModel { HomeViewModel(addRepoUseCase = get()) }
}
