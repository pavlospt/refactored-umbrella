package com.github.pavlospt.refactoredumbrella.remoterepo.github

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPIService {
    @GET("users/{username}/repos")
    suspend fun fetchGithubReposFor(@Path("username") username: String): List<GithubRepoModel>
}
