package com.github.pavlospt.refactoredumbrella.core.domain

interface DomainModelToEntityConverter<Input : DomainModel, Output : DomainEntity> {
    fun convert(input: Input): Output
}
