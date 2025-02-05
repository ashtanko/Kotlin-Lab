/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.concurrency.coroutines.problems.stonks

import dev.shtanko.concurrency.coroutines.problems.stonks.model.FinancialIndicators
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val symbol = "AAPL"
    val days = 30

    val prices = fetchHistoricalPrices(symbol, days)
    val priceList = prices.map { it.price }

    val movingAverageDeferred = async { calculateMovingAverage(priceList, 20) }
    val bollingerBandsDeferred = async { calculateBollingerBands(priceList, 20, 2.0) }
    val rsiDeferred = async { calculateRSI(priceList, 14) }

    val movingAverage = movingAverageDeferred.await()
    val bollingerBands = bollingerBandsDeferred.await()
    val rsi = rsiDeferred.await()
    val currentPrice = prices.last().price
    val recommendation = makeTradingRecommendation(currentPrice, bollingerBands, rsi)

    val indicators = FinancialIndicators(symbol, movingAverage, bollingerBands, rsi, recommendation)
    displayFinancialIndicators(indicators)
}

fun displayFinancialIndicators(indicators: FinancialIndicators): String {
    val result = """
        Symbol: ${indicators.symbol}
        Moving Average: ${indicators.movingAverage}
        Bollinger Bands: Upper=${indicators.bollingerBands.upper}, Middle=${indicators.bollingerBands.middle}, Lower=${indicators.bollingerBands.lower}
        RSI: ${indicators.rsi}
        Recommendation: ${indicators.recommendation}
    """.trimIndent()
    println(result)
    return result
}
