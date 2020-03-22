package com.github.pavlospt.refactoredumbrella.test

import com.github.pavlospt.refactoredumbrella.repo.GithubRepoModel
import com.github.pavlospt.refactoredumbrella.repo.remote.GithubRemoteRepo

class MockGithubRemoteRepo(
    private val fetchedRepos: List<GithubRepoModel>
) : GithubRemoteRepo {
    override suspend fun fetchGithubReposFor(username: String): List<GithubRepoModel> = fetchedRepos
}
