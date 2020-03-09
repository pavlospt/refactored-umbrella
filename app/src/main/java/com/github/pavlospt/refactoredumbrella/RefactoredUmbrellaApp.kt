package com.github.pavlospt.refactoredumbrella

import android.app.Application
import com.github.pavlospt.refactoredumbrella.di.dbModule
import com.github.pavlospt.refactoredumbrella.di.networkModule
import com.github.pavlospt.refactoredumbrella.di.useCaseModule
import com.github.pavlospt.refactoredumbrella.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RefactoredUmbrellaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(androidContext = this@RefactoredUmbrellaApp)

            modules(dbModule, networkModule, useCaseModule, viewModelModule)
        }
    }

}
