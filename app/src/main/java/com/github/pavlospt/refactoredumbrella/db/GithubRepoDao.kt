package com.github.pavlospt.refactoredumbrella.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepo(githubRepoEntity: GithubRepoEntity)

    @Query("SELECT * FROM $GITHUB_REPO_TABLE_NAME")
    fun observeGithubRepos(): Flow<List<GithubRepoEntity>>

    @Query("DELETE FROM $GITHUB_REPO_TABLE_NAME")
    suspend fun deleteAllRepos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepos(updatedRepos: List<GithubRepoEntity>)
}
