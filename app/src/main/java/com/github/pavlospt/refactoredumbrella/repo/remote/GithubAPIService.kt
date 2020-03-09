package com.github.pavlospt.refactoredumbrella.repo.remote

import com.github.pavlospt.refactoredumbrella.repo.GithubRepoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPIService {
    @GET("users/{username}/repos")
    suspend fun fetchGithubReposFor(@Path("username") username: String): List<GithubRepoModel>
}
