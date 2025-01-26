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

/**
 * This function calculates the moving average of the last 'period' prices.
 * @param prices The list of prices.
 * @param period The period for which the moving average is calculated.
 * @return The moving average of the last 'period' prices.
 */
fun calculateMovingAverage(prices: List<Double>, period: Int): Double {
    return prices.takeLast(period).average()
}

/**
 * This function calculates the Bollinger Bands for the last 'period' prices.
 * @param prices The list of prices.
 * @param period The period for which the Bollinger Bands are calculated.
 * @param multiplier The multiplier for the standard deviation.
 * @return The Bollinger Bands for the last 'period' prices.
 */
fun calculateBollingerBands(prices: List<Double>, period: Int, multiplier: Double): BollingerBands {
    val movingAverage = calculateMovingAverage(prices, period)
    val stddev = prices.takeLast(period).standardDeviation()
    return BollingerBands(
        upper = movingAverage + multiplier * stddev,
        middle = movingAverage,
        lower = movingAverage - multiplier * stddev,
    )
}

/**
 * This function calculates the Relative Strength Index (RSI) for the last 'period' prices.
 * @param prices The list of prices.
 * @param period The period for which the RSI is calculated.
 * @return The RSI for the last 'period' prices.
 */
fun calculateRSI(prices: List<Double>, period: Int): Double {
    var gainSum = 0.0
    var lossSum = 0.0
    for (i in 1 until period) {
        val change = prices[i] - prices[i - 1]
        if (change > 0) {
            gainSum += change
        } else {
            lossSum -= change
        }
    }
    val avgGain = gainSum / period
    val avgLoss = lossSum / period
    val rs = avgGain / avgLoss
    return 100 - (100 / (1 + rs))
}

/**
 * This function calculates the standard deviation of a list of doubles.
 * @return The standard deviation of the list.
 */
fun List<Double>.standardDeviation(): Double {
    val mean = this.average()
    return kotlin.math.sqrt(this.map { (it - mean) * (it - mean) }.average())
}
