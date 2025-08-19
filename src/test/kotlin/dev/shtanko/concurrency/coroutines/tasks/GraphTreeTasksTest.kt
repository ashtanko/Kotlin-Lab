package dev.shtanko.concurrency.coroutines.tasks

import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull

class GraphTreeTasksTest {

    @Test
    fun `BinaryTreeTask should build and analyze tree`() = runTest {
        val task = BinaryTreeTask(nodeCount = 100)
        val result = task.run()

        assertTrue(result.contains("Height:"))
        assertTrue(result.contains("Sum:"))

        // Parse results
        val height = result.substringAfter("Height: ").substringBefore(",").toInt()
        assertTrue(height > 0)
        assertTrue(height < 100) // Height should be logarithmic
    }

    @Test
    fun `GraphAlgorithmsTask should find shortest paths`() = runTest {
        val task = GraphAlgorithmsTask(vertices = 50)
        val result = task.run()

        assertTrue(result.contains("Max dist:"))
        assertTrue(result.contains("Avg:"))
    }

    @Test
    fun `tree and graph tasks should complete within reasonable time`() = runTest(timeout = 10.seconds) {
        val treeTask = BinaryTreeTask(nodeCount = 1000)
        val graphTask = GraphAlgorithmsTask(vertices = 100)

        val treeResult = async { treeTask.run() }
        val graphResult = async { graphTask.run() }

        assertNotNull(treeResult.await())
        assertNotNull(graphResult.await())
    }
}
