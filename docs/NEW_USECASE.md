### Creating a new UseCase

A `UseCase` can extend one of the following base classes:

* `FlowUseCase` - For `UseCase`s that have observable results, in the form of a `Flow<T>`, when they are triggered.
* `NoResultUseCase` - For `UseCase`s that will execute a given task without returning a `Result<T>`. This `UseCase` will execute its work in `workDispatcher`.
* `ResultUseCase` - For `UseCase`s that will execute a given task and return a `Result<T>`. This `UseCase` will execute its work in `workDispatcher`.
* `ScopedNoResultUseCase` - For `UseCase`s that will execute a given task, on a specified scope, without returning a `Result<T>`. The `UseCase` will `launch` its
work in `workScope`.

* A `UseCase` constructor should accept an instance of `AppCoroutineDispatchers` (usually through DI), which describe the actual `Dispatcher`s for our application's 
runtime, or their counterparts when writing unit tests.

* A `UseCase` should only have dependencies on abstractions of local and/or remote repositories.
