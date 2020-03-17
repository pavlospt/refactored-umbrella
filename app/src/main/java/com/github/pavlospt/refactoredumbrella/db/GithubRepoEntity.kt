package com.github.pavlospt.refactoredumbrella.db

import androidx.room.Entity
import androidx.room.PrimaryKey

const val GITHUB_REPO_TABLE_NAME = "github_users"

@Entity(tableName = GITHUB_REPO_TABLE_NAME)
data class GithubRepoEntity(
    val internalId: Int? = null,
    @PrimaryKey
    val remoteId: Int,
    val name: String,
    val stars: Int,
    val url: String,
    val ownerAvatarUrl: String
) : DomainEntity
