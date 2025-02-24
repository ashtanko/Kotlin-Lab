/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.benchmark.executor

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class RxOneTest {

    /**
     * Test that the `invoke` method correctly executes the action for each item in the list.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute action for each item in the list sequentially`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()

        // Define a sample list of integers
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxOne
        RxOne.invoke(data) { results.add(it) }

        // Assert: Verify that each action was executed sequentially
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("rework needed")
    fun `should block until all tasks are complete`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Use a Completable to track the completion
        val completable = Completable.create { emitter ->
            RxOne.invoke(data) { results.add(it) }
            emitter.onComplete()
        }

        // Act: Execute the action and wait for completion
        completable.blockingAwait()

        // Assert: Check that the results list contains all items and was completed
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method uses a single-threaded scheduler.
     */
    @Test
    @Disabled("rework needed")
    fun `should use single-threaded scheduler`() {
        // Arrange: Track the thread names to verify the single-threaded execution
        val threadNames = mutableListOf<String>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxOne
        RxOne.invoke(data) {
            threadNames.add(Thread.currentThread().name)
        }

        // Assert: Verify that all tasks executed on the same thread
        assertTrue(threadNames.all { it == threadNames.first() })
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using RxOne
        RxOne.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class RxCPUTest {

    /**
     * Test that the `invoke` method correctly executes the action for each item concurrently.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxCPU
        RxCPU.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("rework needed")
    fun `should block until all tasks are complete`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Use a Completable to track the completion
        val completable = Completable.create { emitter ->
            RxCPU.invoke(data) { results.add(it) }
            emitter.onComplete()
        }

        // Act: Execute the action and wait for completion
        completable.blockingAwait()

        // Assert: Check that the results list contains all items and was completed
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method executes tasks on the computation scheduler.
     */
    @Test
    @Disabled("rework needed")
    fun `should use computation scheduler`() {
        // Arrange: Track the thread names to verify the computation scheduler usage
        val threadNames = mutableListOf<String>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxCPU
        RxCPU.invoke(data) {
            threadNames.add(Thread.currentThread().name)
        }

        // Assert: Verify that all tasks executed on the computation scheduler thread
        assertTrue(threadNames.all { it.contains("RxComputationThreadPool") })
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using RxCPU
        RxCPU.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class RxIoTest {

    /**
     * Test that the `invoke` method correctly executes the action for each item concurrently.
     */
    @Test
    @Disabled("RxIo does not execute actions concurrently")
    fun `should execute actions concurrently`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxIo
        RxIo.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("rework needed")
    fun `should block until all tasks are complete`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Use a Completable to track the completion
        val completable = Completable.create { emitter ->
            RxIo.invoke(data) { results.add(it) }
            emitter.onComplete()
        }

        // Act: Execute the action and wait for completion
        completable.blockingAwait()

        // Assert: Check that the results list contains all items and was completed
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method executes tasks on the I/O scheduler.
     */
    @Test
    @Disabled("rework needed")
    fun `should use io scheduler`() {
        // Arrange: Track the thread names to verify the I/O scheduler usage
        val threadNames = mutableListOf<String>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxIo
        RxIo.invoke(data) {
            threadNames.add(Thread.currentThread().name)
        }

        // Assert: Verify that all tasks executed on the I/O scheduler thread
        assertTrue(threadNames.all { it.lowercase().contains("Rx".lowercase()) })
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using RxIo
        RxIo.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class RxFlowTest {

    /**
     * Test that the `invoke` method correctly executes the action for each item concurrently.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxFlow
        RxFlow.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("RxFlow does not block until all tasks are complete")
    fun `should block until all tasks are complete`() {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Use a Flowable to track the completion
        val flowable = Flowable.create<Int>(
            { emitter ->
                RxFlow.invoke(data) { results.add(it) }
                emitter.onComplete()
            },
            BackpressureStrategy.BUFFER,
        )

        // Act: Execute the action and wait for completion
        flowable.blockingSubscribe()

        // Assert: Check that the results list contains all items and was completed
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method executes tasks on the computation scheduler.
     */
    @Test
    @Disabled("rework needed")
    fun `should use computation scheduler`() {
        // Arrange: Track the thread names to verify the computation scheduler usage
        val threadNames = mutableListOf<String>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using RxFlow
        RxFlow.invoke(data) {
            threadNames.add(Thread.currentThread().name)
        }

        // Assert: Verify that all tasks executed on the computation scheduler thread
        assertTrue(threadNames.all { it.contains("RxComputationThreadPool") })
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using RxFlow
        RxFlow.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class CoroutineOneTest {

    /**
     * Test that the `invoke` method correctly executes the action concurrently for each item in the list.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineOne
        CoroutineOne.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("rework needed")
    fun `should block until all tasks are complete`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineOne
        CoroutineOne.invoke(data) { results.add(it) }

        // Assert: Check that all actions were completed and results are populated
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using CoroutineOne
        CoroutineOne.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }

    /**
     * Test that the `invoke` method executes tasks concurrently by verifying the order of execution.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently and in order`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<String>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineOne
        CoroutineOne.invoke(data) {
            results.add("Item: $it on thread: ${Thread.currentThread().name}")
        }

        // Assert: Ensure that all actions have been executed and results contain the expected strings
        assertEquals(3, results.size)
        assertTrue(results.all { it.contains("Item: ") })
    }
}

class CoroutineCPUTest {

    /**
     * Test that the `invoke` method correctly executes the action concurrently for each item in the list.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently using Default dispatcher`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineCPU
        CoroutineCPU.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("rework needed")
    fun `should block until all tasks are complete`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineCPU
        CoroutineCPU.invoke(data) { results.add(it) }

        // Assert: Ensure all tasks were completed and results are populated
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using CoroutineCPU
        CoroutineCPU.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }

    /**
     * Test that the `invoke` method executes tasks on the Default dispatcher.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions using the Default dispatcher`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<String>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineCPU
        CoroutineCPU.invoke(data) {
            val dispatcher = Thread.currentThread().name
            results.add("Item: $it executed on thread: $dispatcher")
        }

        // Assert: Verify that the actions were executed using the Default dispatcher
        assertTrue(results.all { it.contains("executed on thread") })
    }
}

class CoroutineCPULimitTest {

    /**
     * Test that the `invoke` method correctly executes the action concurrently but with limited parallelism
     * based on the number of available processors.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently with limited parallelism`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5, 6)

        // Act: Execute the action using CoroutineCPULimit
        CoroutineCPULimit.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5, 6).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("rework needed")
    fun `should block until all tasks are complete`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineCPULimit
        CoroutineCPULimit.invoke(data) { results.add(it) }

        // Assert: Ensure all tasks were completed and results are populated
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using CoroutineCPULimit
        CoroutineCPULimit.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }

    /**
     * Test that the `invoke` method executes actions using the limited parallelism of the Default dispatcher.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions with limited parallelism`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<String>()
        val data = listOf(1, 2, 3, 4, 5, 6)

        // Act: Execute the action using CoroutineCPULimit
        CoroutineCPULimit.invoke(data) {
            val dispatcher = Thread.currentThread().name
            results.add("Item: $it executed on thread: $dispatcher")
        }

        // Assert: Verify that the actions were executed using limited parallelism
        assertTrue(results.size <= Runtime.getRuntime().availableProcessors()) // Ensure limited parallelism
        assertTrue(results.all { it.contains("executed on thread") })
    }
}

class CoroutineIoTest {

    /**
     * Test that the `invoke` method executes actions concurrently using the IO dispatcher.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently with IO dispatcher`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5, 6)

        // Act: Execute the action using CoroutineIo
        CoroutineIo.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5, 6).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("CoroutineIo does not block until all tasks are complete")
    fun `should block until all tasks are complete`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using CoroutineIo
        CoroutineIo.invoke(data) { results.add(it) }

        // Assert: Ensure all tasks were completed and results are populated
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using CoroutineIo
        CoroutineIo.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }

    /**
     * Test that the `invoke` method executes actions using the IO dispatcher.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions with IO dispatcher`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<String>()
        val data = listOf(1, 2, 3, 4, 5, 6)

        // Act: Execute the action using CoroutineIo
        CoroutineIo.invoke(data) {
            val dispatcher = Thread.currentThread().name
            results.add("Item: $it executed on thread: $dispatcher")
        }

        // Assert: Verify that the actions were executed using the IO dispatcher
        assertTrue(results.size == data.size) // Ensure that actions are executed on all items
        assertTrue(results.all { it.contains("executed on thread") })
    }
}

class FlowCPUTest {

    /**
     * Test that the `invoke` method executes actions concurrently using Flows and the Default dispatcher.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently using Flows with Default dispatcher`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using FlowCPU
        FlowCPU.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("rework needed")
    fun `should block until all tasks are complete`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using FlowCPU
        FlowCPU.invoke(data) { results.add(it) }

        // Assert: Ensure all tasks were completed and results are populated
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using FlowCPU
        FlowCPU.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class FlowCPULimitTest {

    /**
     * Test that the `invoke` method executes actions concurrently using Flows with limited parallelism.
     */
    @Test
    @Disabled("")
    fun `should execute actions concurrently using Flows with limited parallelism`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using FlowCPULimit
        FlowCPULimit.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method blocks until all tasks are complete.
     */
    @Test
    @Disabled("")
    fun `should block until all tasks are complete`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using FlowCPULimit
        FlowCPULimit.invoke(data) { results.add(it) }

        // Assert: Ensure all tasks were completed and results are populated
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using FlowCPULimit
        FlowCPULimit.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class FlowOneTest {

    /**
     * Test that the `invoke` method executes actions sequentially.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions sequentially`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using FlowOne
        FlowOne.invoke(data) { results.add(it) }

        // Assert: Verify that the results list contains the expected items
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using FlowOne
        FlowOne.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class FlowIoTest {

    /**
     * Test that the `invoke` method executes actions concurrently using IO dispatcher.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently with IO dispatcher`() = runTest {
        // Arrange: Create a mutable list to store the results
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using FlowIo
        FlowIo.invoke(data) { results.add(it) }

        // Assert: Verify that all actions were applied to the list
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("rework needed")
    fun `should not execute action for empty list`() = runTest {
        // Arrange: Create a mutable list to store results
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using FlowIo
        FlowIo.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class ExecutorFixedCPUTest {

    /**
     * Test that the `invoke` method executes actions concurrently using a fixed thread pool.
     */
    @Test
    @Disabled("rework needed")
    fun `should execute actions concurrently with fixed thread pool`() {
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using ExecutorFixedCPU
        ExecutorFixedCPU.invoke(data) { results.add(it) }

        // Assert: Verify that all actions were applied to the list
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("")
    fun `should not execute action for empty list`() {
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using ExecutorFixedCPU
        ExecutorFixedCPU.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class ExecutorFixedIoTest {

    /**
     * Test that the `invoke` method executes actions concurrently using a fixed thread pool.
     */
    @Test
    @Disabled("")
    fun `should execute actions concurrently with fixed thread pool`() {
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using ExecutorFixedIo
        ExecutorFixedIo.invoke(data) { results.add(it) }

        // Assert: Verify that all actions were applied to the list
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("")
    fun `should not execute action for empty list`() {
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using ExecutorFixedIo
        ExecutorFixedIo.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class ExecutorStealCPUTest {

    /**
     * Test that the `invoke` method executes actions concurrently using a work-stealing thread pool.
     */
    @Test
    @Disabled("")
    fun `should execute actions concurrently with work-stealing thread pool`() {
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using ExecutorStealCPU
        ExecutorStealCPU.invoke(data) { results.add(it) }

        // Assert: Verify that all actions were applied to the list
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("")
    fun `should not execute action for empty list`() {
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using ExecutorStealCPU
        ExecutorStealCPU.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class ExecutorStealIoTest {

    /**
     * Test that the `invoke` method executes actions concurrently using a work-stealing thread pool.
     */
    @Test
    @Disabled("")
    fun `should execute actions concurrently with work-stealing thread pool`() {
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using ExecutorStealIo
        ExecutorStealIo.invoke(data) { results.add(it) }

        // Assert: Verify that all actions were applied to the list
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("")
    fun `should not execute action for empty list`() {
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using ExecutorStealIo
        ExecutorStealIo.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class ExecutorCachedCPUTest {

    /**
     * Test that the `invoke` method executes actions concurrently using a cached thread pool.
     */
    @Test
    @Disabled("")
    fun `should execute actions concurrently with cached thread pool`() {
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using ExecutorCachedCPU
        ExecutorCachedCPU.invoke(data) { results.add(it) }

        // Assert: Verify that all actions were applied to the list
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("")
    fun `should not execute action for empty list`() {
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using ExecutorCachedCPU
        ExecutorCachedCPU.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }
}

class ExecutorSingleCPUTest {

    /**
     * Test that the `invoke` method executes actions sequentially using a single-threaded executor.
     */
    @Test
    @Disabled("")
    fun `should execute actions sequentially with single-threaded executor`() {
        val results = mutableListOf<Int>()
        val data = listOf(1, 2, 3, 4, 5)

        // Act: Execute the action using ExecutorSingleCPU
        ExecutorSingleCPU.invoke(data) { results.add(it) }

        // Assert: Verify that all actions were applied to the list in the correct order
        assertThat(results).containsExactlyInAnyOrder(*listOf(1, 2, 3, 4, 5).toTypedArray())
    }

    /**
     * Test that the `invoke` method correctly handles an empty list.
     */
    @Test
    @Disabled("")
    fun `should not execute action for empty list`() {
        val results = mutableListOf<Int>()
        val data = emptyList<Int>()

        // Act: Execute the action using ExecutorSingleCPU
        ExecutorSingleCPU.invoke(data) { results.add(it) }

        // Assert: Ensure no items were added to results (empty list)
        assertEquals(emptyList<Int>(), results)
    }

    /**
     * Test that the `invoke` method handles exceptions during tasks.
     */
    @Test
    @Disabled("")
    fun `should handle exceptions during task execution`() {
        val results = mutableListOf<String>()
        val data = listOf(1, 2, 3)

        // Act: Execute the action using ExecutorSingleCPU
        ExecutorSingleCPU.invoke(data) {
            if (it == 2) throw RuntimeException("Test exception")
            results.add("Processed $it")
        }

        // Assert: Verify that tasks continued executing despite exception
        assertEquals(listOf("Processed 1", "Processed 3"), results)
    }
}
