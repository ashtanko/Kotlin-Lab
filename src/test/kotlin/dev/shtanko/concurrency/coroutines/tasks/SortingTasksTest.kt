package dev.shtanko.concurrency.coroutines.tasks

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

// Sorting Tasks Tests with TestDispatcher
@OptIn(ExperimentalCoroutinesApi::class)
class SortingTasksTest {

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: TestScope

    @BeforeEach
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
    }

    @RepeatedTest(10)
    fun `MergeSortTask should sort array correctly`() = testScope.runTest {
        val task = MergeSortTask(arraySize = 1000, dispatcher = testDispatcher)
        val comparisons = task.run()

        task.status.test {
            assertEquals(TaskStatus.COMPLETED, awaitItem())
            assertTrue(comparisons > 0)
            val expectedMinComparisons = 1000 * 8
            assertTrue(comparisons > expectedMinComparisons)
        }
    }

    @Test
    fun `QuickSortTask should sort array correctly`() = testScope.runTest {
        val task = QuickSortTask(arraySize = 1000, dispatcher = testDispatcher)
        val swaps = task.run()

        assertTrue(swaps > 0)
        // Quick sort makes between O(n log n) and O(n^2) swaps
        assertTrue(swaps < 1000 * 1000) // Less than n^2
    }

    @Test
    fun `sorting tasks should update progress`() = testScope.runTest {
        val mergeSortTask = MergeSortTask(arraySize = 5000, dispatcher = testDispatcher)
        val quickSortTask = QuickSortTask(arraySize = 5000, dispatcher = testDispatcher)

        // Test MergeSort progress
        mergeSortTask.progress.test {
            assertEquals(0f, awaitItem())

            launch { mergeSortTask.run() }
            advanceUntilIdle()

            // Should receive multiple progress updates
            val updates = mutableListOf<Float>()
            while (true) {
                val item = awaitItem()
                updates.add(item)
                if (item == 1f) break
            }

            assertTrue(updates.size > 2)
            assertTrue(updates.all { it in 0f..1f })
        }
    }

    @Test
    fun `tasks should complete instantly with test dispatcher`() = testScope.runTest {
        val task = MergeSortTask(arraySize = 10000, dispatcher = testDispatcher)

        val startTime = currentTime
        task.run()
        val endTime = currentTime

        // Virtual time should advance minimally
        assertEquals(0L, endTime - startTime)
    }

    @ParameterizedTest
    @CsvSource(
        "100, MergeSort",
        "100, QuickSort",
        "1000, MergeSort",
        "1000, QuickSort",
    )
    fun `sorting tasks should handle different sizes`(size: Int, algorithm: String) = testScope.runTest {
        val task = when (algorithm) {
            "MergeSort" -> MergeSortTask(size, dispatcher = testDispatcher)
            "QuickSort" -> QuickSortTask(size, dispatcher = testDispatcher)
            else -> throw IllegalArgumentException("Unknown algorithm")
        }

        val result = task.run()
        assertNotNull(result)
    }
}
