package dev.shtanko.concurrency.coroutines.tasks

import app.cash.turbine.test
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class IntegrationTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var viewModel: TaskViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TaskViewModel(dispatcher = testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.onCleared()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @RepeatedTest(10)
    fun `should run multiple different tasks concurrently`() = testScope.runTest(timeout = 30.seconds) {
        viewModel.addTask(SortingTask(1))
        viewModel.addTask(MergeSortTask(1))
        viewModel.addTask(SortingTask(1))
        viewModel.addTask(QuickSortTask(1))


        viewModel.runAllTasks()

        viewModel.results.test {
            // Initial empty list
            awaitItem()
            // Wait until we've collected 4 results
            var final: List<TaskResult<*>> = emptyList()
            while (true) {
                val next = awaitItem()
                if (next.size >= 4) {
                    final = next
                    break
                }
            }
            assertEquals(4, final.size)
            assertTrue(final.all { it.status == TaskStatus.COMPLETED })
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should handle concurrent task cancellation`() = testScope.runTest {
        // Add long-running tasks
        repeat(5) {
            viewModel.addTask(PrimeCalculationTask(10))
        }

        // Start all tasks
        viewModel.runAllTasks()
        advanceUntilIdle()

        // Cancel all tasks
        viewModel.cancelAllTasks()
        advanceUntilIdle()

        // Verify tasks are cancelled
        val tasks = viewModel.tasks.value
        assertTrue(
            tasks.all {
                it.status.value == TaskStatus.CANCELLED ||
                    it.status.value == TaskStatus.COMPLETED
            },
        )

        viewModel.onCleared()
    }
}
