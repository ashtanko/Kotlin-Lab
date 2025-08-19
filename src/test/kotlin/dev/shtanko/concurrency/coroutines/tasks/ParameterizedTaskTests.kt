@file:Suppress("MagicNumber", "SwallowedException")

package dev.shtanko.concurrency.coroutines.tasks

import java.util.stream.Stream
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ParameterizedTaskTests {

    @ParameterizedTest
    @MethodSource("taskProvider")
    fun `all tasks should implement required interface correctly`(task: Task<*>) = runTest {
        assertNotNull(task.name)
        assertNotNull(task.description)
        assertNotNull(task.progress)
        assertNotNull(task.status)

        // Initial state
        assertEquals(0f, task.progress.value)
        assertEquals(TaskStatus.IDLE, task.status.value)
    }

    @ParameterizedTest
    @MethodSource("taskProvider")
    fun `all tasks should be cancellable`(task: Task<*>) = runTest {
        val job = launch {
            try {
                task.run()
            } catch (e: CancellationException) {
                // Expected
            }
        }

        delay(50)
        job.cancel()
        job.join()

        assertTrue(
            task.status.value == TaskStatus.CANCELLED ||
                    task.status.value == TaskStatus.COMPLETED,
        )
    }

    @ParameterizedTest
    @MethodSource("taskProvider")
    fun `all tasks should report progress`(task: Task<*>) = runTest {
        val progressUpdates = mutableListOf<Float>()

        val job = launch {
            task.progress.collect { progressUpdates.add(it) }
        }

        launch { task.run() }
        delay(500)

        job.cancel()
        assertTrue(progressUpdates.first() == 0f)
    }

    companion object {
        @JvmStatic
        fun taskProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(PrimeCalculationTask(100)),
            Arguments.of(MergeSortTask(100)),
            Arguments.of(QuickSortTask(100)),
            Arguments.of(MatrixMultiplicationTask(10)),
            Arguments.of(MandelbrotTask(10, 10)),
            Arguments.of(HashComputationTask(100)),
            Arguments.of(BinaryTreeTask(100)),
            Arguments.of(GraphAlgorithmsTask(10)),
            Arguments.of(StringMatchingTask(100, 5)),
            Arguments.of(ImageProcessingTask(10, 10)),
            Arguments.of(NeuralNetworkTask(10, 5, 3, 1)),
            Arguments.of(CompressionTask(100)),
            Arguments.of(CryptographicTask(10)),
        )
    }
}
