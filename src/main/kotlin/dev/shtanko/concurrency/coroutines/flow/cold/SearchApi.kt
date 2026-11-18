package dev.shtanko.concurrency.coroutines.flow.cold

import kotlinx.coroutines.delay

interface SearchApi {
    suspend fun search(query: String): List<String>
}

// Simple implementation for testing/demo
@Suppress("MagicNumber")
class FakeSearchApi : SearchApi {
    override suspend fun search(query: String): List<String> {
        delay(500) // Simulate network delay
        val database = listOf("Kotlin", "Coroutines", "Flow", "Ktor", "KMP", "Android")
        return database.filter { it.contains(query, ignoreCase = true) }
    }
}
