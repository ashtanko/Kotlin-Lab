package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PerformanceTest {

    @Test
    fun `tasks should complete within expected time`() = runTest {
        val testCases = listOf(
            PrimeCalculationTask(1000) to 1000L,
            MergeSortTask(1000) to 500L,
            HashComputationTask(1000) to 100L
        )

        for ((task, maxTimeMs) in testCases) {
            val startTime = System.currentTimeMillis()
            task.run()
            val duration = System.currentTimeMillis() - startTime

            assertTrue(
                duration < maxTimeMs,
                "${task.name} took ${duration}ms, expected less than ${maxTimeMs}ms"
            )
        }
    }

    @Test
    fun `should handle memory efficiently`() = runTest {
        val runtime = Runtime.getRuntime()
        val beforeMemory = runtime.totalMemory() - runtime.freeMemory()

        // Run memory-intensive tasks
        val tasks = listOf(
            MatrixMultiplicationTask(200),
            ImageProcessingTask(400, 300),
            BinaryTreeTask(10000)
        )

        val jobs = tasks.map { task ->
            async { task.run() }
        }

        jobs.forEach { it.await() }

        // Force garbage collection
        System.gc()
        delay(100)

        val afterMemory = runtime.totalMemory() - runtime.freeMemory()
        val memoryIncrease = afterMemory - beforeMemory

        // Memory increase should be reasonable (less than 100MB)
        assertTrue(
            memoryIncrease < 100 * 1024 * 1024,
            "Memory increased by ${memoryIncrease / 1024 / 1024}MB"
        )
    }
}
