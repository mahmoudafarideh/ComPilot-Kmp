# ComPilot: Type-Safe Navigation for Kotlin Multiplatform

[![Maven Central](https://img.shields.io/maven-central/v/io.github.mahmoudafarideh.compilot.kmp/runtime)](https://mvnrepository.com/artifact/io.github.mahmoudafarideh.compilot.kmp)
[![Version](https://img.shields.io/badge/version-0.0.1--beta01-blue)](https://mvnrepository.com/artifact/io.github.mahmoudafarideh.compilot/runtime/0.0.1-beta01)

Com

ComPilot simplifies navigation in Jetpack Compose by providing type-safe routes and advanced navigation control. Leveraging Kotlin Symbol Processing (KSP), ComPilot automatically generates navigation code, ensuring compile-time safety and reducing errors in backstack management and argument handling.

## Key Features

- **Type-Safe Routes**: Define routes with data classes and ensure all navigation arguments are type-checked.
- **Advanced Backstack Control**: Prevent duplicate navigations, safely pop the backstack, and manage transitions smoothly.
- **Conditional Navigation**: Skip specific routes or check existing routes to prevent redundant navigations.
- **Generated Code with KSP**: Automatically generated navigation functions eliminate manual route definition and ensure type safety.

## Installation

Add ComPilot dependencies to your Gradle configuration:

```gradle
commonMain.dependencies {
   implementation "io.github.mahmoudafarideh.compilot.kmp:common:$version"
   implementation "io.github.mahmoudafarideh.compilot.kmp:runtime:$version"
   implementation "io.github.mahmoudafarideh.compilot.kmp:navigation:$version"
}

dependencies {
    add("kspCommonMainMetadata", "io.github.mahmoudafarideh.compilot.kmp:compiler:$version")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

```

## Defining Routes

To define a type-safe route, annotate a data class with `@RouteNavigation`. Each class must include a `companion object` to enable code generation.

```kotlin
@RouteNavigation(RouteType.Screen)
data class SomeScreenRoute(val title: String) {
    companion object
}
```

## Example with Nested Data

ComPilot supports complex structures like nested data classes and enums:

```kotlin
@RouteNavigation(RouteType.Screen)
data class SomeScreenWithNestedArgRoute(
    val nested: NestedData,
    val child: Child?
) {
    data class NestedData(val id: Int, val name: String)
    enum class Child { Child1, Child2 }
    companion object
}
```

## Types of Routes

ComPilot supports multiple route types by setting the `type` parameter in the `@RouteNavigation` annotation:

- **Standard Screen** (default)
- **Dialog**: `@RouteNavigation(type = RouteType.Dialog)`


### Example

```kotlin
@RouteNavigation(type = RouteType.Dialog)
data class SomeDialogRoute(val id: Int) {
    companion object
}
```

## Using ComPilotNavController

Access `ComPilotNavController` via `LocalNavController` to enable type-safe, controlled navigation.

### Example Usage

```kotlin
val comPilotNavController = LocalNavController.comPilotNavController

// Navigate to a route
comPilotNavController.navigate(SomeScreenRoute(title = "Welcome").navigator())

// Safe backstack pop
comPilotNavController.safePopBackStack()
```
## Conditional Navigation

Use functions like `safeNavigate()` and `checkNotInRoutes()` to prevent duplicate navigations and manage the backstack safely.

## Setting Up NavController in the Composition

To make `ComPilotNavController` accessible throughout your screens, provide the `NavController` at the root of your composable hierarchy:

```kotlin
CompositionLocalProvider(LocalNavController provides navigation) {
    // Your screen content
}
```
This `CompositionLocalProvider` setup allows all child composables to access `LocalNavController` and use `comPilotNavController` for navigation.

## Additional Notes

- **Companion Object Requirement**: Each `@RouteNavigation` annotated data class must include a `companion object` to allow the compiler to extend it with generated functions.
- **Testing Routes**: ComPilot’s KSP-generated code ensures type-safe, structured navigation routes. For complex navigation flows, test routes with `ComPilotNavController` to confirm behavior, especially with nested arguments and different route types.
- **Updating Compose Version**: Ensure that your Compose version is compatible with ComPilot (currently tested with Compose 1.7.5). Compatibility with newer versions may require library updates.

