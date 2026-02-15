package dev.shtanko.concurrency.coroutines.tasks

import app.cash.turbine.test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalCoroutinesApi::class)
class BaseTaskTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private class SuccessfulTask(dispatcher: CoroutineDispatcher) :
        BaseTask<String>("TestTask", "A successful task", dispatcher) {
        override suspend fun execute(): String {
            delay(100)
            updateProgress(1f)
            return "success"
        }
    }

    private class FailingTask(dispatcher: CoroutineDispatcher) :
        BaseTask<String>("FailTask", "A failing task", dispatcher) {
        override suspend fun execute(): String = error("Boom!")
    }

    private class CancellableTask(dispatcher: CoroutineDispatcher) :
        BaseTask<String>("CancelTask", "A cancellable task", dispatcher) {
        override suspend fun execute(): String {
            repeat(5) {
                delay(100)
                updateProgress((it + 1) / 5f)
            }
            return "done"
        }
    }

    private class TestTask(
        private val delayMs: Long = 100,
        private val progressSteps: Int = 1,
        private val shouldThrowError: Boolean = false,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    ) : BaseTask<String>("Test Task", "Test Description", dispatcher) {
        override suspend fun execute(): String {
            if (shouldThrowError) {
                throw RuntimeException("Test error")
            }

            for (i in 1..progressSteps) {
                delay(delayMs / progressSteps)
                updateProgress(i.toFloat() / progressSteps)
            }

            return "Test Result"
        }
    }

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `task should start with IDLE status`() = runTest {
        val task = TestTask()
        task.status.test {
            Assertions.assertEquals(TaskStatus.IDLE, awaitItem())
        }
    }

    @Test
    fun `task should update status to RUNNING when executed`() = runTest {
        val task = TestTask()

        task.status.test {
            Assertions.assertEquals(TaskStatus.IDLE, awaitItem())

            val job = launch { task.run() }
            Assertions.assertEquals(TaskStatus.RUNNING, awaitItem())

            job.cancel()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `task should update status to COMPLETED when finished`() = runTest {
        val task = TestTask(delayMs = 100)

        task.status.test {
            Assertions.assertEquals(TaskStatus.IDLE, awaitItem())

            launch { task.run() }
            Assertions.assertEquals(TaskStatus.RUNNING, awaitItem())
            Assertions.assertEquals(TaskStatus.COMPLETED, awaitItem())
        }
    }

    @Test
    fun `task should update status to CANCELLED when cancelled`() = runTest {
        val task = TestTask(delayMs = 1000)

        task.status.test {
            Assertions.assertEquals(TaskStatus.IDLE, awaitItem())

            val job = launch { task.run() }
            Assertions.assertEquals(TaskStatus.RUNNING, awaitItem())

            job.cancel()
            Assertions.assertEquals(TaskStatus.CANCELLED, awaitItem())
        }
    }

    @Test
    fun `task should update progress correctly`() = runTest {
        val task = TestTask(progressSteps = 5)

        task.progress.test {
            Assertions.assertEquals(0f, awaitItem())

            launch { task.run() }

            // Should receive progress updates
            Assertions.assertEquals(0.2f, awaitItem(), 0.01f)
            Assertions.assertEquals(0.4f, awaitItem(), 0.01f)
            Assertions.assertEquals(0.6f, awaitItem(), 0.01f)
            Assertions.assertEquals(0.8f, awaitItem(), 0.01f)
            Assertions.assertEquals(1.0f, awaitItem(), 0.01f)
        }
    }

    @Test
    fun `task should handle errors and update status to ERROR`() = runTest {
        val task = TestTask(shouldThrowError = true)

        task.status.test {
            Assertions.assertEquals(TaskStatus.IDLE, awaitItem())

            assertThrows<RuntimeException> {
                runBlocking { task.run() }
            }

            expectMostRecentItem().let { status ->
                Assertions.assertEquals(TaskStatus.ERROR, status)
            }
        }
    }

    @Test
    fun `cancel should update status immediately`() = runTest {
        val task = TestTask()

        task.status.test {
            Assertions.assertEquals(TaskStatus.IDLE, awaitItem())

            task.cancel()
            Assertions.assertEquals(TaskStatus.CANCELLED, awaitItem())
        }
    }

    @Test
    fun `initial state is idle with zero progress`() = testScope.runTest {
        val task = SuccessfulTask(testDispatcher)
        assertEquals(TaskStatus.IDLE, task.status.value)
        assertEquals(0f, task.progress.value)
    }

    @Test
    fun `successful task updates status and progress`() = testScope.runTest {
        val task = SuccessfulTask(testDispatcher)

        // Status
        val statusJob = launch {
            task.status.test {
                assertEquals(TaskStatus.IDLE, awaitItem())
                launch { task.run() }
                assertEquals(TaskStatus.RUNNING, awaitItem())
                advanceTimeBy(100)   // let execute() finish
                assertEquals(TaskStatus.COMPLETED, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        // Progress
        task.progress.test {
            assertEquals(0f, awaitItem()) // initial
            advanceTimeBy(100)
            assertEquals(1f, awaitItem()) // final
            cancelAndIgnoreRemainingEvents()
        }

        statusJob.join()
    }

    @Test
    fun `failing task sets status to ERROR and throws`() = testScope.runTest {
        val task = FailingTask(testDispatcher)
        task.status.test {
            assertEquals(TaskStatus.IDLE, awaitItem())
            assertFailsWith<RuntimeException> { task.run() }
            assertEquals(TaskStatus.RUNNING, awaitItem())
            assertEquals(TaskStatus.ERROR, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `cancelling task sets status to CANCELLED`() = testScope.runTest {
        val task = CancellableTask(testDispatcher)

        val runJob = launch { task.run() }

        task.status.test {
            assertEquals(TaskStatus.IDLE, awaitItem())
            assertEquals(TaskStatus.RUNNING, awaitItem())

            // Start some progress, then cancel
            advanceTimeBy(150)
            task.cancel()
            runJob.join()

            assertEquals(TaskStatus.CANCELLED, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
