package dev.shtanko.concurrency.coroutines.flow.cold

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class SearchManager(private val api: SearchApi) {
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun getSuggestions(
        queryFlow: Flow<String>,
        debounceMillis: Long = 300L, // Default for production
    ): Flow<List<String>> = queryFlow
        .debounce(debounceMillis)
        .filter { it.isNotBlank() }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            flow { emit(api.search(query)) }
        }
        .catch { emit(emptyList()) }
}
