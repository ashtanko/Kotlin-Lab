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

import dev.shtanko.concurrency.coroutines.problems.stonks.model.StockPrice
import kotlinx.coroutines.delay

// Constant representing the number of milliseconds in a day
private const val MILLIS_IN_DAY = 86400000L

/**
 * This suspend function fetches the historical prices for a given stock symbol for a certain number of days.
 * It simulates a network delay before returning the data.
 * Each day's price is simulated by adding the day number to a base price of 100.0.
 * The date for each day is calculated as the current time minus the number of days times the number of milliseconds
 * in a day.
 * @param symbol The stock symbol for which the historical prices are fetched.
 * @param days The number of days for which the historical prices are fetched.
 * @return A list of StockPrice objects representing the historical prices for the given stock symbol.
 */
suspend fun fetchHistoricalPrices(symbol: String, days: Int): List<StockPrice> {
    delay(100) // Simulate network delay
    return List(days) { day ->
        StockPrice(symbol, 100.0 + day, System.currentTimeMillis() - day * MILLIS_IN_DAY)
    }
}
