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

import dev.shtanko.concurrency.coroutines.problems.stonks.model.BollingerBands
import dev.shtanko.concurrency.coroutines.problems.stonks.model.FinancialIndicators
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StonksTest {
    @Test
    fun testFetchHistoricalPrices() = runTest {
        val result = fetchHistoricalPrices("AAPL", 30)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(30, result.size)
    }

    @Test
    fun testCalculateMovingAverage() {
        val prices = listOf(100.0, 101.0, 102.0, 103.0, 104.0)
        val result = calculateMovingAverage(prices, 3)
        Assertions.assertEquals(103.0, result, 0.01)
    }

    @Test
    fun testCalculateBollingerBands() {
        val prices = List(20) { it.toDouble() + 100 }
        val result = calculateBollingerBands(prices, 20, 2.0)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(109.5, result.middle, 0.01)
    }

    @Test
    fun testCalculateRSI() {
        val prices =
            listOf(100.0, 101.0, 102.0, 103.0, 104.0, 103.0, 102.0, 101.0, 100.0, 101.0, 102.0, 103.0, 104.0, 105.0)
        val result = calculateRSI(prices, 14)
        Assertions.assertEquals(69.23076923076923, result, 0.01)
    }

    @Test
    fun testMakeTradingRecommendation() {
        val recommendation = makeTradingRecommendation(95.0, BollingerBands(110.0, 100.0, 90.0), 25.0)
        Assertions.assertEquals("Hold", recommendation)

        val recommendation2 = makeTradingRecommendation(105.0, BollingerBands(110.0, 100.0, 90.0), 75.0)
        Assertions.assertEquals("Hold", recommendation2)
    }

    @Test
    fun testDisplayFinancialIndicators() {
        val indicators = FinancialIndicators("AAPL", 150.33, BollingerBands(155.0, 150.0, 145.0), 60.0, "Hold")
        val result = displayFinancialIndicators(indicators)

        val expected = """
            Symbol: AAPL
            Moving Average: 150.33
            Bollinger Bands: Upper=155.0, Middle=150.0, Lower=145.0
            RSI: 60.0
            Recommendation: Hold
        """.trimIndent()

        Assertions.assertEquals(expected, result)
    }
}
