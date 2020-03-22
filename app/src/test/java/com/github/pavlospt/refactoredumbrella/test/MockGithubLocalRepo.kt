package com.github.pavlospt.refactoredumbrella.test

import com.github.pavlospt.refactoredumbrella.db.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.repo.local.GithubLocalRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockGithubLocalRepo : GithubLocalRepo {

    val addRepoRenders = RenderRecorder<GithubRepoEntity>()
    val updateRepoRenders = RenderRecorder<List<GithubRepoEntity>>()

    override suspend fun addRepo(githubRepoEntity: GithubRepoEntity) =
        addRepoRenders.add(githubRepoEntity)

    override suspend fun updateRepos(updatedGithubRepoEntities: List<GithubRepoEntity>) =
        updateRepoRenders.add(updatedGithubRepoEntities)

    override fun observeGithubRepos(): Flow<List<GithubRepoEntity>> = flowOf()

    fun reset() {
        addRepoRenders.reset()
        updateRepoRenders.reset()
    }
}
