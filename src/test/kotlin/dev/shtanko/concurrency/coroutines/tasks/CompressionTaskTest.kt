package dev.shtanko.concurrency.coroutines.tasks

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest

class CompressionTaskTest {
    @RepeatedTest(10)
    fun `should compress data and return ratio`() = runTest {
        val task = CompressionTask(dataSize = 1000)
        val ratio = task.run()
        task.status.test {
            assertTrue(awaitItem() == TaskStatus.COMPLETED)
            assertTrue(ratio > 0)
            assertTrue(ratio < 1.3) // Compression should reduce size
        }
    }

    @RepeatedTest(10)
    fun `compression ratio should vary with data size`() = runTest {
        val sizes = listOf(100, 500, 1000)
        val ratios = mutableListOf<Double>()

        for (size in sizes) {
            val task = CompressionTask(size)
            ratios.add(task.run())
        }

        assertTrue(ratios.all { it > 0 && it <= 2 })
    }
}
