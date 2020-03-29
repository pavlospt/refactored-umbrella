package com.github.pavlospt.refactoredumbrella.usecase.github

import com.github.pavlospt.refactoredumbrella.core.domain.DomainModelToEntityConverter
import com.github.pavlospt.refactoredumbrella.db.github.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRepoModel

object GithubRepoConverter : DomainModelToEntityConverter<GithubRepoModel, GithubRepoEntity> {
    override fun convert(input: GithubRepoModel): GithubRepoEntity =
        GithubRepoEntity(
            remoteId = input.id,
            name = input.name,
            stars = input.stars,
            url = input.url,
            ownerAvatarUrl = input.owner.avatarUrl
        )
}
