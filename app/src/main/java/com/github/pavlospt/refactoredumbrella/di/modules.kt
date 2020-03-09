package com.github.pavlospt.refactoredumbrella.di

import androidx.room.Room
import com.github.pavlospt.refactoredumbrella.db.GithubAppDB
import com.github.pavlospt.refactoredumbrella.interactor.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.repo.ObserveGithubReposUseCase
import com.github.pavlospt.refactoredumbrella.repo.RefreshGithubReposUseCase
import com.github.pavlospt.refactoredumbrella.repo.local.GithubLocalRepo
import com.github.pavlospt.refactoredumbrella.repo.local.RealGithubLocalRepo
import com.github.pavlospt.refactoredumbrella.repo.remote.GithubAPIService
import com.github.pavlospt.refactoredumbrella.repo.remote.GithubRemoteRepo
import com.github.pavlospt.refactoredumbrella.repo.remote.RealGithubRemoteRepo
import com.github.pavlospt.refactoredumbrella.ui.dashboard.DashboardViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            GithubAppDB::class.java,
            GithubAppDB.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single {
        RealGithubLocalRepo(githubRepoDao = get<GithubAppDB>().githubRepoDAO()) as GithubLocalRepo
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single { Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build() }

    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .baseUrl("https://api.github.com/")
            .build()
    }

    single {
        RealGithubRemoteRepo(
            githubAPIService = get<Retrofit>().create(GithubAPIService::class.java)
        ) as GithubRemoteRepo
    }
}

val useCaseModule = module {
    single {
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )
    }

    factory {
        ObserveGithubReposUseCase(
            appCoroutineDispatchers = get(),
            githubLocalRepo = get()
        )
    }

    factory {
        RefreshGithubReposUseCase(
            appCoroutineDispatchers = get(),
            githubRemoteRepo = get(),
            githubLocalRepo = get()
        )
    }
}

val viewModelModule = module {
    viewModel {
        DashboardViewModel(
            refreshGithubReposUseCase = get(),
            observeGithubReposUseCase = get()
        )
    }
}
