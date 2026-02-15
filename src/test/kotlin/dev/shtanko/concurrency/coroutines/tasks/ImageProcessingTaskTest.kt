package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull

class ImageProcessingTaskTest {
    @Test
    fun `should process image and calculate brightness`() = runTest {
        val task = ImageProcessingTask(width = 100, height = 100)
        val result = task.run()

        assertTrue(result.contains("Avg brightness:"))

        val brightness = result.substringAfter("Avg brightness: ").toDouble()
        assertTrue(brightness >= 0)
        assertTrue(brightness <= 255)
    }

    @Test
    fun `should handle different image sizes`() = runTest {
        val sizes = listOf(
            50 to 50,
            100 to 100,
            200 to 150,
        )

        for ((width, height) in sizes) {
            val task = ImageProcessingTask(width, height)
            val result = task.run()
            assertNotNull(result)
        }
    }
}
