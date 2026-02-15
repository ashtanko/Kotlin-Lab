package dev.shtanko.concurrency.coroutines.tasks

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
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PrimeCalculationTaskTest {

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: TestScope

    @BeforeEach
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
    }

    @Test
    fun `should find correct prime numbers`() = testScope.runTest {
        val task = PrimeCalculationTask(limit = 20, dispatcher = testDispatcher)
        val result = task.run()

        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19)
        assertEquals(expectedPrimes, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should update progress during calculation`() = testScope.runTest {
        val task = PrimeCalculationTask(limit = 10000, dispatcher = testDispatcher)

        val progressValues = mutableListOf<Float>()
        val job = launch {
            task.progress.collect { progressValues.add(it) }
        }

        launch { task.run() }
        advanceUntilIdle()
        job.cancel()

        // Should have multiple progress updates
        assertTrue(progressValues.size > 2)
        assertEquals(0f, progressValues.first())
        assertEquals(1f, progressValues.last())

        // Progress should be monotonically increasing
        for (i in 1 until progressValues.size) {
            assertTrue(progressValues[i] >= progressValues[i - 1])
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [100, 1000, 5000])
    fun `should handle different limits correctly`(limit: Int) = testScope.runTest {
        val task = PrimeCalculationTask(limit = limit, dispatcher = testDispatcher)
        val result = task.run()

        assertTrue(result.isNotEmpty())
        assertTrue(result.all { isPrime(it) })
        assertTrue(result.all { it <= limit })
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should run efficiently with test dispatcher`() = testScope.runTest {
        val task = PrimeCalculationTask(limit = 10000, dispatcher = testDispatcher)

        val startTime = currentTime
        task.run()
        val endTime = currentTime

        // With test dispatcher, should complete instantly (virtual time)
        assertTrue(endTime - startTime < 1000)
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        if (n <= 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false

        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }
}
