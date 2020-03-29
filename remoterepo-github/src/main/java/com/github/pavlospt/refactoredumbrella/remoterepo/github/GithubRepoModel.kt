package com.github.pavlospt.refactoredumbrella.remoterepo.github

import com.github.pavlospt.refactoredumbrella.core.domain.DomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubRepoModel(
    val id: Int,
    val name: String,
    @Json(name = "stargazers_count")
    val stars: Int,
    @Json(name = "html_url")
    val url: String,
    @Json(name = "owner")
    val owner: GithubRepoOwner
) : DomainModel

@JsonClass(generateAdapter = true)
data class GithubRepoOwner(
    @Json(name = "avatar_url")
    val avatarUrl: String
)
