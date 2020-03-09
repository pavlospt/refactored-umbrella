package com.github.pavlospt.refactoredumbrella.repo

import com.github.pavlospt.refactoredumbrella.db.GithubRepoEntity

object GithubRepoConverter : DomainModelToEntityConverter<GithubRepoModel, GithubRepoEntity> {
    override fun convert(input: GithubRepoModel): GithubRepoEntity = GithubRepoEntity(
        remoteId = input.id,
        name = input.name,
        stars = input.stars,
        url = input.url
    )
}
