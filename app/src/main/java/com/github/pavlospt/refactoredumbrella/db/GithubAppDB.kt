package com.github.pavlospt.refactoredumbrella.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GithubRepoEntity::class], exportSchema = false, version = 1)
abstract class GithubAppDB : RoomDatabase() {
    companion object {
        const val DB_NAME = "github_app.db"
    }

    abstract fun githubRepoDAO(): GithubRepoDao
}
