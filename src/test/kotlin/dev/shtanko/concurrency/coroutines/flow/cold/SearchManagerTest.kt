package dev.shtanko.concurrency.coroutines.flow.cold

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchManagerTest {

    private val fakeApi = FakeSearchApi()
    private val manager = SearchManager(fakeApi)

    @Test
    fun `search flow should debounce and return results`() = runTest {
        // Use a buffer of 1 so emissions aren't lost during setup
        val queryInput = MutableSharedFlow<String>(replay = 1)
        val testDebounce = 100L

        manager.getSuggestions(queryInput, debounceMillis = testDebounce).test {
            // Emit "K"
            queryInput.emit("K")
            advanceTimeBy(50) // Less than debounce

            // Emit "Ko" - this should reset the timer for "K"
            queryInput.emit("Ko")

            // Advance exactly enough to trigger to debounce
            advanceTimeBy(testDebounce + 1)

            val result = awaitItem()
            assertEquals(listOf("Kotlin"), result)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `search flow should ignore duplicate consecutive queries`() = runTest {
        val queryInput = MutableSharedFlow<String>(replay = 1)
        val testDebounce = 100L

        manager.getSuggestions(queryInput, debounceMillis = testDebounce).test {
            // First search
            queryInput.emit("Kotlin")
            advanceTimeBy(testDebounce + 1)
            awaitItem()

            // Immediate duplicate search
            queryInput.emit("Kotlin")
            advanceTimeBy(testDebounce + 1)

            // Assert that no new list was emitted
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }
}
