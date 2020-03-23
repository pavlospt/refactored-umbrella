## Testing a NoResultUseCase

A test for a `UseCase` that does not return a `Result` just needs to be executed inside a `coroutinesTestRule.testDispatcher.runBlockingTest`
and check the work done by verifying that the underlying sources the `UseCase` is modifying, are actually modified.

This can be done by mocking any `Repository` and adding a `RenderRecorder` for each, of the `Repository`s test double, method.
