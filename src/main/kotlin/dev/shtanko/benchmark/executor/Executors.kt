/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.benchmark.executor

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Represents a sealed interface for executing tasks on a list of items.
 *
 * Implementers of this interface are expected to define how the tasks are executed
 * for each item in the provided list.
 */
sealed interface TaskExecutor {

    /**
     * Executes a given action on each item in the provided list.
     *
     * @param T The type of items in the list.
     * @param data The list of items to be processed.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    operator fun <T> invoke(data: List<T>, action: (T) -> Unit)
}

/**
 * A data object that implements the `TaskExecutor` interface using RxJava.
 *
 * This implementation processes tasks sequentially on a single-threaded scheduler.
 * Each item in the provided list is processed as a `Completable`, ensuring tasks
 * are executed in a serialized manner.
 */
data object RxOne : TaskExecutor {

    /**
     * Executes the provided action on each item in the list sequentially.
     *
     * This implementation creates a `Completable` for each item in the list,
     * schedules the tasks on a single-threaded scheduler, and merges all
     * `Completable` streams. The method blocks until all tasks are complete.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        val scheduler = Schedulers.single()
        val completables = data.map {
            Completable.fromAction {
                action(it)
            }.subscribeOn(scheduler)
        }
        Completable.merge(completables).blockingAwait()
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using RxJava.
 *
 * This implementation processes tasks concurrently using a computation scheduler.
 * Each item in the provided list is processed as a `Completable`, leveraging
 * RxJava's computation scheduler, which is optimized for CPU-intensive tasks.
 */
data object RxCPU : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently.
     *
     * This implementation creates a `Completable` for each item in the list,
     * schedules the tasks on the computation scheduler, and merges all
     * `Completable` streams. The method blocks until all tasks are complete.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Define the computation scheduler, ideal for CPU-intensive tasks
        val scheduler = Schedulers.computation()

        // Create a Completable for each item, executing the action on the computation scheduler
        val completables = data.map {
            Completable.fromAction {
                action(it)
            }.subscribeOn(scheduler)
        }

        // Merge all Completables and execute them, blocking until all are complete
        Completable.merge(completables).blockingAwait()
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using RxJava.
 *
 * This implementation processes tasks concurrently using an I/O scheduler.
 * The I/O scheduler is optimized for tasks involving asynchronous I/O operations,
 * such as reading or writing files or making network requests.
 */
data object RxIo : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently.
     *
     * This implementation creates a `Completable` for each item in the list,
     * schedules the tasks on the I/O scheduler, and merges all `Completable` streams.
     * The method blocks until all tasks are complete.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Define the I/O scheduler, suitable for tasks involving I/O operations
        val scheduler = Schedulers.io()

        // Create a Completable for each item, executing the action on the I/O scheduler
        val completables = data.map {
            Completable.fromAction {
                action(it)
            }.subscribeOn(scheduler)
        }

        // Merge all Completables and execute them, blocking until all are complete
        Completable.merge(completables).blockingAwait()
    }
}

/**
 * This implementation processes tasks concurrently on a computation scheduler.
 * Each item in the provided list is processed using a `Flowable` to leverage
 * RxJava's reactive programming capabilities.
 */
data object RxFlow : TaskExecutor {
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        val scheduler = Schedulers.computation()
        val flowables = data.map {
            io.reactivex.rxjava3.core.Flowable.fromCallable {
                action(it)
            }.subscribeOn(scheduler)
        }
        // Merge all Flowables and execute them, blocking until all are complete
        io.reactivex.rxjava3.core.Flowable.merge(flowables).blockingSubscribe()
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Coroutines.
 *
 * This implementation processes tasks concurrently using a coroutine context.
 * It ensures that all tasks are executed and completed within the same coroutine scope.
 */
data object CoroutineOne : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently.
     *
     * This implementation uses `runBlocking` to create a blocking coroutine scope,
     * launches an asynchronous task for each item using `async`, and waits for all tasks
     * to complete using `awaitAll`.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        runBlocking {
            // Launch async tasks for each item and wait for all to complete
            data.map {
                async {
                    action(it)
                }
            }.awaitAll()
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Coroutines.
 *
 * This implementation processes tasks concurrently using the `Dispatchers.Default` dispatcher.
 * The `Default` dispatcher is optimized for CPU-intensive tasks, making it suitable for
 * computational workloads that require concurrent processing.
 */
data object CoroutineCPU : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using the `Default` dispatcher.
     *
     * This implementation creates a blocking coroutine scope with `runBlocking`, launches asynchronous
     * tasks for each item using `async`, and switches to the `Default` dispatcher with `withContext`.
     * It ensures all tasks are completed before returning.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Use the Default dispatcher, optimized for CPU-intensive tasks
        val dispatcher = Dispatchers.Default

        runBlocking {
            // Launch async tasks for each item, execute with the Default dispatcher, and wait for all to complete
            data.map {
                async {
                    withContext(dispatcher) {
                        action(it)
                    }
                }
            }.awaitAll()
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Coroutines.
 *
 * This implementation processes tasks concurrently with a limited parallelism
 * based on the number of available processors. The `limitedParallelism` ensures
 * that the number of concurrent tasks does not exceed the number of CPU cores,
 * making it efficient for CPU-bound tasks.
 */
data object CoroutineCPULimit : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently, with limited parallelism.
     *
     * This implementation uses `Dispatchers.Default` with `limitedParallelism` to restrict
     * the number of concurrent tasks based on the number of available processors. It launches
     * asynchronous tasks for each item in the list using `async`, and executes them within the
     * defined parallelism limits. The method blocks until all tasks are complete.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Get the number of available processors (CPU cores) and limit parallelism accordingly
        val threadCount = Runtime.getRuntime().availableProcessors()

        // Create a dispatcher with limited parallelism
        val dispatcher = Dispatchers.Default.limitedParallelism(threadCount)

        runBlocking {
            // Launch async tasks for each item, execute them with the limited dispatcher, and wait for all to complete
            data.map {
                async {
                    withContext(dispatcher) {
                        action(it)
                    }
                }
            }.awaitAll()
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Coroutines.
 *
 * This implementation processes tasks concurrently using the `Dispatchers.IO` dispatcher.
 * The `IO` dispatcher is optimized for offloading tasks that are primarily I/O-bound, such as
 * network or file operations.
 */
data object CoroutineIo : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using the `IO` dispatcher.
     *
     * This implementation creates a blocking coroutine scope with `runBlocking`, launches asynchronous
     * tasks for each item using `async`, and executes the tasks using the `IO` dispatcher, which is
     * suitable for I/O-bound operations. The method blocks until all tasks are complete.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Use the IO dispatcher, optimized for I/O-bound tasks (e.g., network or file operations)
        val dispatcher = Dispatchers.IO

        runBlocking {
            // Launch async tasks for each item, execute with the IO dispatcher, and wait for all to complete
            data.map {
                async {
                    withContext(dispatcher) {
                        action(it)
                    }
                }
            }.awaitAll()
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Flows and Coroutines.
 *
 * This implementation processes tasks concurrently using a Flow-based approach with `Dispatchers.Default`.
 * The `Default` dispatcher is optimized for CPU-bound tasks, making this implementation suitable for
 * computational workloads that require concurrent processing with the reactive Flow API.
 */
data object FlowCPU : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using Kotlin Flows.
     *
     * This implementation creates a blocking coroutine scope with `runBlocking`, generates a Flow for each
     * item using `flow {}`, and emits the result of the action applied to each item. It uses `flowOn` to specify
     * the `Default` dispatcher for each Flow, which is suitable for CPU-intensive tasks. The method collects all
     * flows concurrently by calling `collect()` on each one.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Use the Default dispatcher, optimized for CPU-intensive tasks
        val dispatcher = Dispatchers.Default

        runBlocking {
            // Create a Flow for each item and emit the result of the action
            val flows = data.map {
                flow {
                    val result = action(it)
                    emit(result) // Emit the result of the action
                }.flowOn(dispatcher) // Specify the dispatcher for each Flow
            }

            // Collect all flows concurrently
            flows.forEach { it.collect() }
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Flows and Coroutines,
 * with limited parallelism based on the number of available processors.
 *
 * This implementation processes tasks concurrently using a Flow-based approach with `Dispatchers.Default`,
 * while limiting the number of concurrent tasks based on the number of CPU cores. The `limitedParallelism`
 * ensures that no more than the available number of CPU threads are used, optimizing for CPU-bound tasks.
 */
data object FlowCPULimit : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using Kotlin Flows,
     * with limited parallelism based on the available number of processors.
     *
     * This implementation uses `Dispatchers.Default.limitedParallelism` to limit the number of concurrent tasks
     * based on the system's available processors. Each item in the list is processed as a Flow, and the result
     * of the action is emitted for each item. The method blocks until all flows are collected.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Get the number of available processors (CPU cores) and limit parallelism accordingly
        val threadCount = Runtime.getRuntime().availableProcessors()

        // Create a dispatcher with limited parallelism
        val dispatcher = Dispatchers.Default.limitedParallelism(threadCount)

        runBlocking {
            // Create a Flow for each item and emit the result of the action
            val flows = data.map {
                flow {
                    val result = action(it)
                    emit(result) // Emit the result of the action
                }.flowOn(dispatcher) // Specify the dispatcher for each Flow
            }

            // Merge and collect all flows concurrently
            flows.merge().collect()
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Flows and Coroutines.
 *
 * This implementation processes tasks concurrently using a Flow-based approach. It creates a Flow for each item
 * in the list and collects the results sequentially. Each task is executed in its own Flow, and the `collect()`
 * method is called on each Flow in order to start the execution of the action and gather the results.
 */
data object FlowOne : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using Kotlin Flows.
     *
     * This implementation creates a Flow for each item in the list. The action is applied to each item,
     * and the result is emitted in the Flow. The method then sequentially collects each Flow, which causes
     * the action to be executed for each item.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        runBlocking {
            // Create a Flow for each item in the list
            val flows = data.map {
                flow {
                    val result = action(it)
                    emit(result) // Emit the result of the action
                }
            }

            // Sequentially collect each Flow
            flows.forEach {
                it.collect() // Start the execution of the action for each item
            }
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using Kotlin Flows and Coroutines,
 * optimized for IO-bound tasks.
 *
 * This implementation processes tasks concurrently using Kotlin Flows, with IO-bound operations offloaded
 * to `Dispatchers.IO`. It creates a Flow for each item in the list, and each Flow is executed on the IO dispatcher.
 * The results of each Flow are then merged and collected concurrently.
 */
data object FlowIo : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using Kotlin Flows,
     * with IO-bound operations dispatched to `Dispatchers.IO`.
     *
     * This implementation creates a Flow for each item in the list, applies the action to each item,
     * and emits the result in the Flow. Each Flow is dispatched to the IO dispatcher (`Dispatchers.IO`)
     * to optimize the execution of IO-bound tasks. After the Flows are created, they are merged and
     * collected concurrently.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Define the dispatcher for IO-bound tasks
        val dispatcher = Dispatchers.IO

        runBlocking {
            // Create a Flow for each item and offload the task to the IO dispatcher
            val flows = data.map {
                flow {
                    val result = action(it)
                    emit(result) // Emit the result of the action
                }.flowOn(dispatcher) // Offload to the IO dispatcher
            }

            // Merge and collect all Flows concurrently
            flows.merge().collect()
        }
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using a fixed thread pool.
 *
 * This implementation uses a fixed-size thread pool, where the number of threads is equal to the number of available
 * processors on the system. Each task from the list is submitted to the thread pool for execution. The tasks are
 * executed concurrently, and the `get()` method is called on each `Future` to wait for completion.
 */
data object ExecutorFixedCPU : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using a fixed thread pool.
     *
     * This implementation creates a fixed-size thread pool with a number of threads equal to the number of available
     * processors. It submits each task in the list to the thread pool for concurrent execution. The method waits
     * for all tasks to complete by calling `get()` on each `Future` object returned by the `submit()` method.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Create a fixed thread pool with the number of available processors
        val threadCount = Runtime.getRuntime().availableProcessors()
        val executorService = Executors.newFixedThreadPool(threadCount)

        executeGracefully(data, executorService, action)
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using a fixed thread pool with 100 threads.
 *
 * This implementation uses a fixed-size thread pool with 100 threads. Each task from the list is submitted to
 * the thread pool for concurrent execution. The method waits for all tasks to complete by calling `get()` on
 * each `Future` object returned by the `submit()` method.
 */
data object ExecutorFixedIo : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using a fixed thread pool with 100 threads.
     *
     * This implementation creates a fixed-size thread pool with 100 threads. It submits each task in the list to
     * the thread pool for concurrent execution. The method waits for all tasks to complete by calling `get()` on
     * each `Future` object returned by the `submit()` method.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Create a fixed thread pool with 100 threads
        val executorService = Executors.newFixedThreadPool(100)

        executeGracefully(data, executorService, action)
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using a work-stealing thread pool.
 *
 * This implementation uses a work-stealing thread pool with a number of threads equal to the available processors on
 * the machine. The work-stealing pool allows idle threads to "steal" work from busy threads, making it well-suited for
 * handling dynamic workloads where tasks may vary in execution time.
 */
data object ExecutorStealCPU : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using a work-stealing thread pool.
     *
     * This implementation creates a work-stealing thread pool with a number of threads equal to the available
     * processors on the machine. Each task from the list is submitted to the pool for concurrent execution. The method
     * waits for all tasks to complete by calling `get()` on each `Future` object returned by the `submit()` method.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Get the number of available processors
        val threadCount = Runtime.getRuntime().availableProcessors()

        // Create a work-stealing thread pool with a size based on available processors
        val executorService = Executors.newWorkStealingPool(threadCount)

        executeGracefully(data, executorService, action)
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using a work-stealing thread pool for I/O-bound tasks.
 *
 * This implementation uses a work-stealing thread pool with a fixed size of 100 threads to handle I/O-bound tasks.
 * A work-stealing pool is ideal for scenarios where tasks have unpredictable durations, allowing idle threads to
 * "steal" work from busy threads, optimizing resource utilization.
 */
data object ExecutorStealIo : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using a work-stealing thread pool.
     *
     * This implementation creates a work-stealing thread pool with a fixed size of 100 threads to handle I/O-bound
     * tasks.
     * Each task from the list is submitted to the pool for concurrent execution. The method waits for all tasks to
     * complete by calling `get()` on each `Future` object returned by the `submit()` method.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Create a work-stealing thread pool with 100 threads
        val executorService = Executors.newWorkStealingPool(100)

        executeGracefully(data, executorService, action)
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using a cached thread pool for CPU-bound tasks.
 *
 * This implementation uses a cached thread pool to handle CPU-bound tasks. A cached thread pool can dynamically adjust
 * the number of threads it uses based on demand, creating new threads when necessary and reusing previously constructed
 * threads when they become available.
 */
data object ExecutorCachedCPU : TaskExecutor {

    /**
     * Executes the provided action on each item in the list concurrently using a cached thread pool.
     *
     * This implementation creates a cached thread pool that allows the system to reuse existing threads for tasks
     * and creates new threads when there are no idle threads available. Each task from the list is submitted to the
     * pool for concurrent execution. The method waits for all tasks to complete by calling `get()` on each `Future`
     * object returned by the `submit()` method.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Create a cached thread pool
        val executorService = Executors.newCachedThreadPool()

        executeGracefully(data, executorService, action)
    }
}

/**
 * A data object that implements the `TaskExecutor` interface using a single-threaded executor for CPU-bound tasks.
 *
 * This implementation uses a single-threaded executor service, meaning that all tasks are executed sequentially on the
 * same thread. This is suitable for scenarios where the tasks are CPU-bound and need to be processed one after another
 * without concurrent execution.
 */
data object ExecutorSingleCPU : TaskExecutor {

    /**
     * Executes the provided action on each item in the list sequentially using a single-threaded executor.
     *
     * This implementation creates a single-threaded executor, which processes each task one by one in the order they
     * are submitted. The method waits for each task to complete by calling `get()` on each `Future` object returned
     * by the `submit()` method, ensuring that all tasks are completed before continuing.
     *
     * @param T The type of items in the list.
     * @param data The list of items to process.
     * @param action A lambda function that defines the task to be executed for each item.
     */
    override fun <T> invoke(data: List<T>, action: (T) -> Unit) {
        // Create a single-threaded executor service
        val executorService = Executors.newSingleThreadExecutor()

        executeGracefully(data, executorService, action)
    }
}

@Suppress("MaxLineLength", "TooGenericExceptionCaught", "MagicNumber")
private fun <T> executeGracefully(
    data: List<T>,
    executorService: ExecutorService,
    action: (T) -> Unit,
) {
    try {
        // Submit each task to the executor service and get the futures
        val futures: List<Future<*>> = data.map { item ->
            executorService.submit {
                try {
                    action(item) // Execute the action for the item
                } catch (e: Exception) {
                    // Handle the exception (you can log it or rethrow it based on your needs)
                    e.printStackTrace()
                }
            }
        }

        // Wait for all tasks to complete by calling get() on each Future
        futures.forEach { future ->
            try {
                future.get() // Block until the task completes
            } catch (e: Exception) {
                // Handle any exception thrown during task execution
                e.printStackTrace()
            }
        }
    } finally {
        // Shut down the executor service gracefully
        executorService.shutdown()
        try {
            // Wait for existing tasks to terminate
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow() // Force shutdown if tasks don't complete
            }
        } catch (e: InterruptedException) {
            // If the current thread is interrupted while waiting, force shutdown
            executorService.shutdownNow()
            Thread.currentThread().interrupt() // Re-interrupt the current thread
        }
    }
}
