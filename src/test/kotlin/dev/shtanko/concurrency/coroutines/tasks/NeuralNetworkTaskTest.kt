package dev.shtanko.concurrency.coroutines.tasks

import app.cash.turbine.test
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NeuralNetworkTaskTest {
    @Test
    fun `should train network and return loss`() = runTest {
        val task = NeuralNetworkTask(
            inputSize = 10,
            hiddenSize = 5,
            outputSize = 3,
            epochs = 2,
        )

        val loss = task.run()

        assertTrue(loss >= 0)
        assertTrue(loss <= 1.0) // Loss should be normalized
    }

    @Test
    fun `should update progress during training`() = runTest {
        val task = NeuralNetworkTask(epochs = 3)

        task.progress.test {
            assertEquals(0f, awaitItem())

            launch { task.run() }

            // Collect progress updates
            val updates = mutableListOf<Float>()
            while (true) {
                val item = awaitItem()
                updates.add(item)
                if (item >= 0.9f) break
            }

            assertTrue(updates.size > 2)
            // Progress should increase
            for (i in 1 until updates.size) {
                assertTrue(updates[i] >= updates[i - 1])
            }
        }
    }
}
