### Domain models & entities

Any data that we want to store in our local DB should implement a marker interface named `DomainEntity`.

Any data coming from an external source, like a REST API should implement a marker interface named `DomainModel`.

By utilizing the above we can have converters implementing `DomainModelToEntityConverter<Input : DomainModel, Output : DomainEntity>` that will
be injected to our `UseCase`s, that are responsible to handle any conversion of data between the remote source and our local one.
