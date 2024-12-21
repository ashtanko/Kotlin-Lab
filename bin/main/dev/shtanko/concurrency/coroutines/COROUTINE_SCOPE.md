### Coroutine Scope

A Coroutine Scope in Kotlin is an essential concept for managing the lifecycle of coroutines. 
It provides a context for running coroutines, ensuring proper structure and management of concurrent tasks. 
Coroutine Scopes are used to launch coroutines and define their boundaries, making it easier to handle coroutine lifecycles, cancellation, and error propagation.

#### Key Characteristics of Coroutine Scope

1. **Lifecycle Management**:
   - Coroutine Scopes manage the lifecycle of coroutines launched within them. When the scope is cancelled, 
     all coroutines within that scope are also cancelled.

2. **Context Propagation**:
   - Coroutine Scopes propagate their context to the coroutines launched within them. This context can include 
     information like the dispatcher, job, and other elements.

3. **Structured Concurrency**:
   - Coroutine Scopes help enforce structured concurrency by ensuring that all coroutines within a scope complete 
     before the scope itself completes. This helps prevent resource leaks and makes the code more predictable.

#### Creating and Using Coroutine Scopes

There are several ways to create and use Coroutine Scopes in Kotlin, including using the built-in scope functions like 
`runBlocking`, `coroutineScope`, and `GlobalScope`.

1. **runBlocking**:

   - `runBlocking` creates a new coroutine scope and blocks the current thread until all coroutines within the scope 
     complete. It is typically used in main functions and tests.
   ```kotlin
   fun main() = runBlocking {
       launch {
           delay(1000)
           println("Coroutine 1 completed")
       }
       println("Main function completed")
   }
   ```

### coroutineScope

`coroutineScope` creates a new coroutine scope and suspends the current coroutine until all coroutines within the scope complete. 
It is often used within suspending functions to create a new scope.

```kotlin
suspend fun doWork() = coroutineScope {
    launch {
        delay(1000)
        println("Coroutine 2 completed")
    }
}

fun main() = runBlocking {
    doWork()
    println("Main function completed")
}
```
## GlobalScope

`GlobalScope` is a global coroutine scope that is not bound to any specific lifecycle. Coroutines launched in `GlobalScope` 
live until the application process is terminated. Use it with caution, as it can lead to resource leaks if not managed properly.

```kotlin
fun main() {
    GlobalScope.launch {
        delay(1000)
        println("Global coroutine completed")
    }
    println("Main function completed")
    Thread.sleep(2000)  // Prevents the main thread from exiting before the coroutine completes
}
```
