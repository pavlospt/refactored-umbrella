## Testing a Flow UseCase

A test for a `UseCase` that returns a `Flow` needs a way to observe the items emitted by `Flow`.

In order to observe those results, we `launch` inside `coroutinesTestRule.testDispatcher.runBlockingTest` block and make use of `FlowAssert`
for the verifications.

`FlowAssert` is a helper class created by Square's Engineering team for usage in `SQLDelight`, so all copyrights belong to them.

More info about FlowAssert here: 

* https://github.com/cashapp/sqldelight/blob/master/extensions/coroutines-extensions/src/commonTest/kotlin/com/squareup/sqldelight/runtime/coroutines/FlowAssert.kt
* https://proandroiddev.com/kotlin-flow-assert-ff45465c01c0
