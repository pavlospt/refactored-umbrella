package com.github.pavlospt.refactoredumbrella

import android.app.Application
import com.github.pavlospt.refactoredumbrella.core.dispatchers.dispatchersModule
import com.github.pavlospt.refactoredumbrella.di.dbModule
import com.github.pavlospt.refactoredumbrella.di.networkModule
import com.github.pavlospt.refactoredumbrella.ui.dashboard.dashboardVMModule
import com.github.pavlospt.refactoredumbrella.ui.home.homeVMModule
import com.github.pavlospt.refactoredumbrella.usecase.github.githubUseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RefactoredUmbrellaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(androidContext = this@RefactoredUmbrellaApp)

            modules(
                dbModule,
                networkModule,
                dispatchersModule,
                githubUseCaseModule,
                dashboardVMModule,
                homeVMModule
            )
        }
    }
}
