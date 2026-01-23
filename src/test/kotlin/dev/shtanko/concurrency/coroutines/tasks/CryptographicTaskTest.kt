package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CryptographicTaskTest {
    @Test
    fun `should encrypt and decrypt messages correctly`() = runTest {
        val task = CryptographicTask(messageCount = 100)
        val successCount = task.run()

        // All messages should be encrypted and decrypted correctly
        assertEquals(100, successCount)
    }

    @Test
    fun `should handle different message counts`() = runTest {
        val counts = listOf(10, 50, 100)

        for (count in counts) {
            val task = CryptographicTask(count)
            val result = task.run()
            assertEquals(count, result)
        }
    }
}
