## Dependency Injection

In order to make use of the DI pattern in `Refactored Umbrella` we utilize Koin. Our Koin setup consists of the following modules:

* `DBModule`
* `NetworkModule`
* `UseCaseModule`
* `ViewModelModule`

### DBModule

This module is responsible to create our Database instance and expose as singleton any `Repository` that makes use of our Database.

### NetworkModule

This module is responsible to setup our network stack (OkHttp + Retrofit + Moshi) and expose as singleton any `Repository` that is communicating
with a REST API.

### UseCaseModule

This module is responsible to expose as singleton an instance of `AppCoroutineDispatchers`.

This module is also responsible to eagerly create an instance of a given `UseCase` when needed.

### ViewModelModule

This module is responsible to eagerly create an instance for a required `ViewModel` by fetching its dependencies from the previous Koin modules.
