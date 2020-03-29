package com.github.pavlospt.refactoredumbrella.remoterepo.github

class RealGithubRemoteRepo(private val githubAPIService: GithubAPIService) : GithubRemoteRepo {
    override suspend fun fetchGithubReposFor(username: String): List<GithubRepoModel> =
        githubAPIService.fetchGithubReposFor(username = username)
}
