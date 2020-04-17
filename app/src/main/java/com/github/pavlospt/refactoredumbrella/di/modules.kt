package com.github.pavlospt.refactoredumbrella.di

import androidx.room.Room
import com.github.pavlospt.refactoredumbrella.core.dispatchers.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.db.GithubAppDB
import com.github.pavlospt.refactoredumbrella.localrepo.github.GithubLocalRepo
import com.github.pavlospt.refactoredumbrella.localrepo.github.RealGithubLocalRepo
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubAPIService
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRemoteRepo
import com.github.pavlospt.refactoredumbrella.remoterepo.github.RealGithubRemoteRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.Date
import kotlinx.coroutines.asExecutor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dbModule = module {
    single {
        val transactionQueryExecutor = get<AppCoroutineDispatchers>().io.asExecutor()
        Room
            .databaseBuilder(
                androidApplication(),
                GithubAppDB::class.java,
                GithubAppDB.DB_NAME
            )
            .setQueryExecutor(transactionQueryExecutor)
            .setTransactionExecutor(transactionQueryExecutor)
            .fallbackToDestructiveMigration()
            .build()
    }

    single<GithubLocalRepo> {
        RealGithubLocalRepo(githubRepoDao = get<GithubAppDB>().githubRepoDAO())
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

    single<GithubRemoteRepo> {
        RealGithubRemoteRepo(
            githubAPIService = get<Retrofit>().create(GithubAPIService::class.java)
        )
    }
}
