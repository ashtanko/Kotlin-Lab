# Job Overview

A background job in Kotlin coroutines is a cancellable entity with a life-cycle that concludes upon completion.

Jobs can be structured into parent-child hierarchies, where cancelling a parent results in immediate cancellation of all its children recursively. 
If a child fails with an exception other than `CancellationException`, it triggers immediate cancellation of its parent and all its other children. 
This behavior can be customized using the `SupervisorJob`.

## Creating Jobs

- **Coroutine job**: Created with the [launch][CoroutineScope.launch] coroutine builder, executing a specified block of code and completing upon its finish.
- **[CompletableJob]**: Created using the `Job()` factory function, completed by calling [CompletableJob.complete].

Jobs are primarily launched for their side effects, without producing a result value. For jobs that produce results, see the [Deferred] interface.

## Job States

A job can exist in several states:

| **State**                        | isActive | isCompleted | isCancelled |
| -------------------------------- | -------- | ----------- | ----------- |
| _New_ (optional initial state)   | `false`  | `false`     | `false`     |
| _Active_ (default initial state) | `true`   | `false`     | `false`     |
| _Completing_ (transient state)   | `true`   | `false`     | `false`     |
| _Cancelling_ (transient state)   | `false`  | `false`     | `true`      |
| _Cancelled_ (final state)        | `false`  | `true`      | `true`      |
| _Completed_ (final state)        | `false`  | `true`      | `false`     |

Jobs are typically created in the _active_ state, though coroutine builders with a `start` parameter set to [CoroutineStart.LAZY] create jobs in the _new_ state. 
Such jobs can be activated with [start] or [join].

## Job Lifecycle

A job remains _active_ while its coroutine is running or until [CompletableJob] completion, failure, or cancellation. 
Failure of an _active_ job with an exception moves it to the _cancelling_ state. A job can be cancelled using [cancel], transitioning it to the _cancelling_ state immediately. 
It enters the _cancelled_ state upon completion of its work and all its children.

When a coroutine's body completes or [CompletableJob.complete] is called, the job enters the _completing_ state. 
It waits here until all children complete before transitioning to _completed_.

```
                                      wait children
+-----+ start  +--------+ complete   +-------------+  finish  +-----------+
| New | -----> | Active | ---------> | Completing  | -------> | Completed |
+-----+        +--------+            +-------------+          +-----------+
                 |  cancel / fail       |
                 |     +----------------+
                 |     |
                 V     V
             +------------+                           finish  +-----------+
             | Cancelling | --------------------------------> | Cancelled |
             +------------+                                   +-----------+
```

A `Job` instance in the [coroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/coroutine-context.html) represents the coroutine itself.

## Cancellation Cause

A coroutine job completes exceptionally if its body throws an exception or [CompletableJob.completeExceptionally] is called. 
Exceptionally completed jobs are cancelled, with the exception becoming the cancellation cause.

Normal cancellation occurs when a job throws [CancellationException]. If another exception causes cancellation, the job is considered to have failed. 
In such cases, the parent job is also cancelled with the same exception type, ensuring consistent delegation to children.

Note that [cancel] on a job only accepts [CancellationException], resulting in normal job cancellation without affecting its parent. 
This allows parents to cancel their children recursively without cancelling themselves.

## Concurrency and Synchronization

All functions on this interface and its derivatives are thread-safe, making them safe for invocation from concurrent coroutines without external synchronization.

## Stability

The `Job` interface and its derived interfaces are stable for use but not for inheritance in third-party libraries, as new methods may be added in the future.

## Key Features of a Job

### Lifecycle Management:

- A Job goes through different states: New, Active, Completing, Cancelling, and Cancelled/Completed.
- You can check if a Job is active, cancelled, or completed using properties like isActive, isCompleted, and isCancelled.

### Cancellation:

- A Job can be cancelled by calling the cancel() method. Cancelling a Job will cancel all its children if it has any.
- Cancellation is cooperative. The coroutine code must regularly check for cancellation and handle it appropriately, 
typically by calling yield() or ensureActive().

### Parent-Child Hierarchies:

- Jobs can have parent-child relationships, which means cancelling a parent job will cancel all its child jobs.
- This hierarchy helps in structured concurrency, ensuring that no coroutines are left running in the background unexpectedly.

### Waiting for Completion:

- You can use the join() method to wait for a job to complete.

## Creating and Managing Jobs

When you launch a coroutine, you often get a Job back:

```kotlin
val job: Job = GlobalScope.launch {
    // Coroutine code
}
```

## Managing Coroutines with a Job

You can then use this Job to manage the coroutine:

```kotlin
job.cancel() // Cancels the coroutine
job.join() // Waits for the coroutine to complete
```

### Example

Here is a simple example demonstrating how to use a Job:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    // Launching a coroutine and getting its Job
    val job = launch {
        repeat(1000) { i ->
            println("Job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    
    delay(1300L) // Delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // Cancels the job and waits for its completion
    println("main: Now I can quit.")
}
```
