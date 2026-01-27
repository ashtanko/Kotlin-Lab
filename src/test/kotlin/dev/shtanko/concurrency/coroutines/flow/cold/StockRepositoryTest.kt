package dev.shtanko.concurrency.coroutines.flow.cold

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class StockRepositoryTest {

    private val mockApi = mock<StockApi>()

    @Test
    fun `test will not hang because turbine cancels the scope`() = runTest {
        // We set a small interval
        val repository = StockRepository(mockApi, refreshInterval = 100L)
        val symbol = "AAPL"

        whenever(mockApi.fetchPrice(symbol)).thenReturn(150.0, 151.0)

        // The 'test' extension starts the 'while(true)' loop
        repository.getStockPrice(symbol).test {

            assertEquals(150.0, awaitItem())

            advanceTimeBy(100L)

            assertEquals(151.0, awaitItem())

            // This is the "Kill Switch"
            // It throws a CancellationException inside the Repository's loop
            cancelAndIgnoreRemainingEvents()
        }

        // If we reach here, the loop is confirmed DEAD.
    }

    @Test
    fun `alternative using take to force a finite flow`() = runTest {
        val repository = StockRepository(mockApi, refreshInterval = 0L)
        whenever(mockApi.fetchPrice("AAPL")).thenReturn(100.0, 101.0, 102.0)

        // 'take(3)' turns the endless flow into a finite one that
        // automatically closes after 3 emissions.
        val results = repository.getStockPrice("AAPL")
            .take(3)
            .toList() // Collects everything into a list

        assertEquals(3, results.size)
        assertEquals(102.0, results.last())
    }
}
