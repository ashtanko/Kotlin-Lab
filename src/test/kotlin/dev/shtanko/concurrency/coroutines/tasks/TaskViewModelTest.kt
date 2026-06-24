package dev.shtanko.concurrency.coroutines.tasks

import app.cash.turbine.test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var viewModel: TaskViewModel

    private class SuccessfulTask : BaseTask<String>("SuccessTask", "Always succeeds") {
        override suspend fun execute(): String {
            delay(50)
            updateProgress(1f)
            return "done"
        }
    }

    private class FailingTask : BaseTask<String>("FailTask", "Always fails") {
        override suspend fun execute(): String {
            delay(50)
            throw RuntimeException("Boom!")
        }
    }

    private class CancellableTask : BaseTask<String>("CancelTask", "Can be cancelled") {
        override suspend fun execute(): String {
            repeat(10) {
                delay(100)
                updateProgress((it + 1) / 10f)
            }
            return "not reached"
        }
    }

    private class TestSimpleTask(
        override val name: String = "Test Task",
    ) : BaseTask<String>(name, "Test Description") {
        override suspend fun execute(): String {
            delay(100)
            return "Test Result"
        }
    }

    private class TestLongRunningTask : BaseTask<String>("Long Task", "Long running") {
        override suspend fun execute(): String {
            delay(5000)
            return "Should be cancelled"
        }
    }

    private class TestErrorTask : BaseTask<String>("Error Task", "Will fail") {
        override suspend fun execute(): String {
            throw RuntimeException("Test error")
        }
    }

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

    @Test
    fun `addTask adds tasks to list`() = testScope.runTest {
        viewModel.tasks.test {
            assertEquals(emptyList(), awaitItem())

            val task = SuccessfulTask()
            viewModel.addTask(task)

            val updated = awaitItem()
            assertEquals(1, updated.size)
            assertEquals("SuccessTask", updated[0].name)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should add tasks correctly`() = runTest {
        viewModel.tasks.test {
            assertEquals(emptyList(), awaitItem())

            val task1 = PrimeCalculationTask(100)
            val task2 = MergeSortTask(100)

            viewModel.addTask(task1)
            assertEquals(listOf(task1), awaitItem())

            viewModel.addTask(task2)
            assertEquals(listOf(task1, task2), awaitItem())
        }
    }

    @Test
    fun `runTask stores successful result`() = testScope.runTest {
        val task = SuccessfulTask()
        viewModel.addTask(task)

        viewModel.results.test {
            assertEquals(emptyList(), awaitItem()) // initial

            viewModel.runTask(task)
            advanceUntilIdle() // finish execution

            val results = awaitItem()
            assertEquals(1, results.size)
            assertEquals("SuccessTask", results[0].taskName)
            assertEquals("done", results[0].result)
            assertEquals(TaskStatus.COMPLETED, results[0].status)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should run single task and store result`() = runTest {
        val task = TestSimpleTask()
        viewModel.addTask(task)

        viewModel.results.test {
            assertEquals(emptyList<TaskResult<*>>(), awaitItem())

            viewModel.runTask(task)

            val result = awaitItem().first()
            assertEquals("Test Task", result.taskName)
            assertEquals("Test Result", result.result)
            assertEquals(TaskStatus.COMPLETED, result.status)
            assertTrue(result.executionTime >= 0)
        }
    }

    @Test
    fun `runTask stores error result when task fails`() = testScope.runTest {
        val task = FailingTask()
        viewModel.addTask(task)

        viewModel.results.test {
            assertEquals(emptyList(), awaitItem()) // initial

            viewModel.runTask(task)
            advanceUntilIdle()

            val results = awaitItem()
            assertEquals(1, results.size)
            assertEquals("FailTask", results[0].taskName)
            assertNull(results[0].result)
            assertEquals(TaskStatus.ERROR, results[0].status)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `cancelAllTasks cancels running tasks`() = testScope.runTest {
        val task = CancellableTask()
        viewModel.addTask(task)

        viewModel.results.test {
            assertEquals(emptyList(), awaitItem()) // initial

            viewModel.runTask(task)
            advanceTimeBy(150) // let it start a bit

            viewModel.cancelAllTasks()
            advanceUntilIdle()

            val results = awaitItem()
            assertEquals(1, results.size)
            assertEquals("CancelTask", results[0].taskName)
            assertEquals(TaskStatus.COMPLETED, results[0].status)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should cancel all tasks`() = runTest {
        val task = TestLongRunningTask()
        viewModel.addTask(task)

        task.status.test {
            assertEquals(TaskStatus.IDLE, awaitItem())

            viewModel.runTask(task)
            assertEquals(TaskStatus.RUNNING, awaitItem())

            viewModel.cancelAllTasks()
            assertEquals(TaskStatus.CANCELLED, awaitItem())
        }
    }

    @Test
    fun `should handle task errors correctly`() = runTest {
        val task = TestErrorTask()
        viewModel.addTask(task)

        viewModel.results.test {
            assertEquals(emptyList<TaskResult<*>>(), awaitItem())

            viewModel.runTask(task)

            val result = awaitItem().first()
            assertEquals(TaskStatus.ERROR, result.status)
            assertNull(result.result)
        }
    }

    @Test
    fun `runAllTasks runs all tasks`() = testScope.runTest {
        val t1 = SuccessfulTask()
        val t2 = SuccessfulTask()
        viewModel.addTask(t1)
        viewModel.addTask(t2)

        viewModel.results.test {
            assertEquals(emptyList(), awaitItem()) // initial

            viewModel.runAllTasks()
            advanceUntilIdle()

            val results = awaitItem()
            assertEquals(1, results.size)
            assertEquals(setOf("SuccessTask"), results.map { it.taskName }.toSet())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `clearResults clears stored results`() = testScope.runTest {
        val task = SuccessfulTask()
        viewModel.addTask(task)

        viewModel.results.test {
            assertEquals(emptyList(), awaitItem()) // initial

            viewModel.runTask(task)
            advanceUntilIdle()
            assertTrue(awaitItem().isNotEmpty())

            viewModel.clearResults()
            assertTrue(awaitItem().isEmpty())

            cancelAndIgnoreRemainingEvents()
        }
    }
}
