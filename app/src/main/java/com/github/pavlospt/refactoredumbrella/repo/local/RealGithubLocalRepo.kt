package com.github.pavlospt.refactoredumbrella.repo.local

import com.github.pavlospt.refactoredumbrella.db.GithubRepoDao
import com.github.pavlospt.refactoredumbrella.db.GithubRepoEntity
import kotlinx.coroutines.flow.Flow

class RealGithubLocalRepo(private val githubRepoDao: GithubRepoDao) : GithubLocalRepo {
    override suspend fun addRepo(githubRepoEntity: GithubRepoEntity) =
        githubRepoDao.addRepo(githubRepoEntity = githubRepoEntity)

    override fun observeGithubRepos(): Flow<List<GithubRepoEntity>> =
        githubRepoDao.observeGithubRepos()

    override suspend fun updateRepos(updatedGithubRepoEntities: List<GithubRepoEntity>) {
        githubRepoDao.deleteAllRepos()
        githubRepoDao.addRepos(updatedRepos = updatedGithubRepoEntities)
    }
}
