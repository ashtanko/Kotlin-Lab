### How Coroutines Work under the Hood

1. **Continuations**:
   - Coroutines are based on continuations, which capture the execution state of a suspended computation.
   - Continuations include the current execution context like local variables, program counter, and stack frame, 
     allowing coroutines to suspend and later resume from where they left off.

2. **Coroutine Builders**:
   - **launch**: Starts a new coroutine that performs a task asynchronously. It returns a `Job` object representing the coroutine.
   - **async**: Similar to `launch`, but returns a `Deferred` object representing a future result that can be awaited.

3. **Suspending Functions**:
   - Coroutines use functions marked with `suspend` to suspend their execution without blocking the underlying thread.
   - Suspending functions are transformed into state machines that can be paused and resumed.

4. **Coroutine Context and Dispatchers**:
   - Each coroutine runs within a `CoroutineContext`, providing information about its execution context.
   - Dispatchers control on which thread or threads coroutines run, allowing for main thread, background thread, or custom context execution.

5. **Cooperative Multitasking**:
   - Coroutines use cooperative multitasking, where they voluntarily suspend themselves.
   - This differs from traditional threads that are managed by the OS scheduler, allowing multiple coroutines to efficiently share threads.

6. **Coroutine Scope**:
   - Coroutines are typically scoped within a `CoroutineScope` that manages their lifecycle and cancellation.
   - When a `CoroutineScope` is cancelled, all coroutines launched within it are also cancelled, ensuring cleanup and resource management.

### Example

Here's a simplified example illustrating coroutines in Kotlin:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    // Coroutine scope
    launch {
        println("Coroutine 1: Start")
        delay(1000L) // Suspending function
        println("Coroutine 1: End")
    }

    launch {
        println("Coroutine 2: Start")
        delay(500L) // Suspending function
        println("Coroutine 2: End")
    }

    println("Main thread continues") // This runs in the main thread
}
```

### Explanation
- **runBlocking**: Starts a coroutine that blocks the main thread until all coroutines launched within it complete.
- **launch**: Creates and starts a coroutine within the `runBlocking` scope.
- **delay**: Suspends the coroutine for a specified time without blocking the thread.

### Conclusion
Coroutines in Kotlin provide a flexible and efficient way to write asynchronous code. By leveraging continuations, 
cooperative multitasking, and coroutine builders, Kotlin coroutines enable developers to write concurrent code that is 
both readable and maintainable, while efficiently managing resources and improving application responsiveness.
