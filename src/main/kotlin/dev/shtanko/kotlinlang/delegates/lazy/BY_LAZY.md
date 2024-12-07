// Kotlin by lazy delegate
// https://kotlinlang.org/docs/delegated-properties.html#lazy

The `lazy` function returns a property delegate that implements a lazy property: the first call to get() executes the 
lambda expression passed to `lazy` and remembers the result, subsequent calls to get() simply return the 
remembered result.

```kotlin
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}
```
