package com.github.pavlospt.refactoredumbrella.remoterepo.github

interface GithubRemoteRepo {
    suspend fun fetchGithubReposFor(username: String): List<GithubRepoModel>
}
