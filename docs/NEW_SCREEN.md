### Creating a new screen

A new screen in `refactored-umbrella` consists of the following:

* `Fragment/Activity`
* `ViewModel + UseCases (if needed)`
* `Adapter (if needed)`

A new screen should make use of `ViewBinding` extensions in order to access any UI elements.

A new screen should make use of `FlowBinding` extensions in order to observe actions on UI elements, convert them to UI intents and delegate them to a `ViewModel`s
UI intent processor.
