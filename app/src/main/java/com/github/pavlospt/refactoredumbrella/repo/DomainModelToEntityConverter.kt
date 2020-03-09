package com.github.pavlospt.refactoredumbrella.repo

import com.github.pavlospt.refactoredumbrella.db.DomainEntity

interface DomainModelToEntityConverter<Input : DomainModel, Output : DomainEntity> {
    fun convert(input: Input): Output
}
