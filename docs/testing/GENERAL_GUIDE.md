## General testing guidance

When adding a new test on the project, our test class needs to extend `UnitTest` class.

### UnitTest

`UnitTest` is a base class that offers the following:

* `CoroutinesTestRule` - A JUnit rule that swaps `Dispatchers.main()` and resets it after the tests have completed.
* `TestCoroutineDispatcher` - Coroutine dispatcher for unit tests.
* `TestAppCoroutineDispatchers` - A test double for `AppCoroutineDispatchers`.

All test cases that make use of Kotlin Coroutines or Flow need to execute their flow inside the following block:

```kotlin
coroutinesTestRule.testDispatcher.runBlockingTest {}
```
