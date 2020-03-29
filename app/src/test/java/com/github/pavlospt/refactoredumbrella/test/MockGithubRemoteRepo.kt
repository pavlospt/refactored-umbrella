package com.github.pavlospt.refactoredumbrella.test

import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRemoteRepo
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRepoModel

class MockGithubRemoteRepo(private val fetchedRepos: List<GithubRepoModel>) : GithubRemoteRepo {
    override suspend fun fetchGithubReposFor(username: String): List<GithubRepoModel> = fetchedRepos
}
