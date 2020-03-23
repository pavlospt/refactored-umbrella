### Creating a new ViewModel

A `ViewModel` for a new screen needs to extent `ViewModel` from Android Arcitecture Components. Following all the work done by a `ViewModel` is delegated
to `UseCase`s that are responsible to fetch/update/modify data. 

A `ViewModel` should not "talk" directly to any other application layer rather than `UseCases/Interactors`.

A `ViewModel` should accept UI intents through a `ConflatedBroadcastChannel`. Those events are then processed as a `Flow` and are processed on the 
`viewModelScope`.

A `ViewModel` should observe results from a `UseCase` and process them on `viewModelScope` before delegating them to UI in the form of `LiveData`.

A `ViewModel` should output any one-off actions to the UI in the form of `*UIEvent`. Those `*UIEvents` are propagated through a different `LiveData` stream
and can be something that can describe a UI event, like: `Show a success snackbar/toast`.
