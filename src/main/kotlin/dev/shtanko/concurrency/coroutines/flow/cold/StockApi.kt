package dev.shtanko.concurrency.coroutines.flow.cold

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface StockApi {
    suspend fun fetchPrice(symbol: String): Double
}

fun tickerFlow(interval: Long) = flow {
    while (true) {
        emit(Unit)
        delay(interval)
    }
}

class StockRepository(
    private val api: StockApi,
    private val refreshInterval: Long = 5000L,
) {
    fun getStockPrice(symbol: String): Flow<Double> =
        tickerFlow(refreshInterval).map { api.fetchPrice(symbol) }
}
