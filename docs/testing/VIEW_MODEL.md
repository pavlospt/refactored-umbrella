## Testing a ViewModel


### Setup

When testing a `ViewModel` we need to check if our `ViewModel` makes any use of `LiveData`. In case we do make use of `LiveData` we need to add
the following rule: 

```kotlin
@get:Rule
val instantTaskExecutorRule = InstantTaskExecutorRule()
```

What this rule does, as mentioned in the rule's JavaDoc: 

```
A JUnit Test Rule that swaps the background executor used by the Architecture Components with a different one which executes each task synchronously.
```

### Dependencies

In order to add the dependencies required by our `ViewModel`, we need to add mocks for the underlying `Repository` each of the `UseCase` we depend on, requires.

### Test case

The test case still needs to be executed inside a `coroutinesRule.testDispatcher.runBlockingTest`.

### LiveDat testing

If our `ViewModel` outputs any `LiveData` we need to make use of `observeForTesting` extension in order to wait until our `LiveData` has emitted a value.
After the block in `observeForTesting` has been invoked, we can use `LiveData.forceGet` to get the value of that `LiveData` and assert on it.
