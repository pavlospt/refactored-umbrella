package com.github.pavlospt.refactoredumbrella.repo.remote

import com.github.pavlospt.refactoredumbrella.repo.GithubRepoModel

interface GithubRemoteRepo {
    suspend fun fetchGithubReposFor(username: String): List<GithubRepoModel>
}
