package dev.shtanko.concurrency.coroutines.tasks

import app.cash.turbine.test
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FlowTestingWithTurbine {

    @Test
    fun `test concurrent flow emissions`() = runTest {
        val viewModel = TaskViewModel()
        val task1 = TestConcurrentTask("Task1", 100)
        val task2 = TestConcurrentTask("Task2", 150)

        viewModel.tasks.test {
            assertEquals(emptyList<Task<*>>(), awaitItem())

            viewModel.addTask(task1)
            assertEquals(listOf(task1), awaitItem())

            viewModel.addTask(task2)
            assertEquals(listOf(task1, task2), awaitItem())

            expectNoEvents()
        }
    }

    @Test
    fun `test flow timeout with turbine`() = runTest {
        val task = TestTimeoutTask()

        task.status.test(timeout = 500.milliseconds) {
            assertEquals(TaskStatus.IDLE, awaitItem())

            launch { task.run() }
            assertEquals(TaskStatus.RUNNING, awaitItem())

            // This should timeout
            val error = assertThrows<AssertionError> {
                awaitItem()
            }
            assertTrue(error.message?.contains("No value produced") == true)
        }
    }

    // Helper tasks for turbine tests
    private class TestConcurrentTask(
        override val name: String,
        private val delayMs: Long,
    ) : BaseTask<String>(name, "Concurrent test") {
        override suspend fun execute(): String {
            delay(delayMs)
            return "Result from $name"
        }
    }

    private class TestTimeoutTask : BaseTask<String>("Timeout Task", "Will timeout") {
        override suspend fun execute(): String {
            delay(10000) // Very long delay
            return "Should timeout"
        }
    }
}
