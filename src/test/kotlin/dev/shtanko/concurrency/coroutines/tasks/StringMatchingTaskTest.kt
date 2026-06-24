package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StringMatchingTaskTest {

    @Test
    fun `should find pattern occurrences`() = runTest {
        val task = StringMatchingTask(textSize = 1000, patternSize = 3)
        val matches = task.run()

        assertTrue(matches >= 0)
        // Statistical expectation for random text
        assertTrue(matches < 100) // Should not match too frequently
    }

    @Test
    fun `should handle edge cases`() = runTest {
        // Empty pattern case
        val task1 = StringMatchingTask(textSize = 100, patternSize = 1)
        val matches1 = task1.run()
        assertTrue(matches1 >= 0)

        // Large pattern case
        val task2 = StringMatchingTask(textSize = 100, patternSize = 50)
        val matches2 = task2.run()
        assertTrue(matches2 >= 0)
    }
}
