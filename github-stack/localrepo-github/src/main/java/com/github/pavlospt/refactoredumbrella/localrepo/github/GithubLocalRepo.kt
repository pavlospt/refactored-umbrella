package com.github.pavlospt.refactoredumbrella.localrepo.github

import com.github.pavlospt.refactoredumbrella.db.github.GithubRepoEntity
import kotlinx.coroutines.flow.Flow

interface GithubLocalRepo {
    suspend fun addRepo(githubRepoEntity: GithubRepoEntity)
    suspend fun updateRepos(updatedGithubRepoEntities: List<GithubRepoEntity>)
    fun observeGithubRepos(): Flow<List<GithubRepoEntity>>
}
