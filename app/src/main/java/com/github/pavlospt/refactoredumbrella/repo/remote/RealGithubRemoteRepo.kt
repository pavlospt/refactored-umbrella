package com.github.pavlospt.refactoredumbrella.repo.remote

import com.github.pavlospt.refactoredumbrella.repo.GithubRepoModel

class RealGithubRemoteRepo(
    private val githubAPIService: GithubAPIService
) : GithubRemoteRepo {
    override suspend fun fetchGithubReposFor(username: String): List<GithubRepoModel> =
        githubAPIService.fetchGithubReposFor(username = username)
}
