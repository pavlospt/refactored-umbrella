package com.github.pavlospt.refactoredumbrella.db

import androidx.room.Entity
import androidx.room.PrimaryKey

const val GITHUB_REPO_TABLE_NAME = "github_users"

@Entity(tableName = GITHUB_REPO_TABLE_NAME)
data class GithubRepoEntity(
    @PrimaryKey val internalId: Int? = null,
    val remoteId: Int,
    val name: String,
    val stars: Int,
    val url: String
) : DomainEntity
