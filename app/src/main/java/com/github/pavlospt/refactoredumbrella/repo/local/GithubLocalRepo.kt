package com.github.pavlospt.refactoredumbrella.repo.local

import com.github.pavlospt.refactoredumbrella.db.GithubRepoEntity
import kotlinx.coroutines.flow.Flow

interface GithubLocalRepo {
    suspend fun addRepo(githubRepoEntity: GithubRepoEntity)
    suspend fun updateRepos(updatedGithubRepoEntities: List<GithubRepoEntity>)
    fun observeGithubRepos(): Flow<List<GithubRepoEntity>>
}
