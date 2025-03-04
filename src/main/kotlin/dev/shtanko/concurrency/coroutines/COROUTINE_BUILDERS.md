# Coroutine Builders in Kotlin

## 1. `launch`

The `launch` coroutine builder is used to start a new coroutine that runs concurrently with the current coroutine scope. 
It returns a `Job` object that represents the coroutine and can be used to control its lifecycle.

### Example

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        delay(1000)
        println("Coroutine completed")
    }
    job.join() // Wait for the coroutine to complete
    println("Main function completed")
}
```

## 2. `async`

The async coroutine builder is used to start a coroutine that computes a result asynchronously. It returns a Deferred
object representing a future result. This builder is useful when you need to perform a computation concurrently and retrieve its result later.

### Example

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    val deferred = async {
        delay(1000)
        "Deferred result"
    }
    println("Waiting for async computation...")
    println("Result: ${deferred.await()}")
}
```

## 3. `runBlocking`

The `runBlocking` coroutine builder is used to start a new coroutine and block the current thread until its completion. 
It is primarily used in main functions and tests to launch a coroutine and wait for its completion before proceeding.

### Example

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000)
        println("Coroutine completed")
    }
    println("Main function completed")
}
```

## 4. coroutineScope

The `coroutineScope` coroutine builder is used to create a new coroutine scope within a suspending function. 
It suspends the coroutine until all launched child coroutines complete. It is used to structure concurrent operations within suspending functions.

### Example

```kotlin
import kotlinx.coroutines.*

suspend fun doWork() = coroutineScope {
    launch {
        delay(1000)
        println("Task 1 completed")
    }
    launch {
        delay(2000)
        println("Task 2 completed")
    }
}

fun main() = runBlocking {
    doWork()
    println("Main function completed")
}
```
