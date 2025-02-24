/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.BinarySearch
import dev.shtanko.algorithms.annotations.Math
import dev.shtanko.algorithms.annotations.level.Easy
import kotlin.math.sqrt

/**
 * 441. Arranging Coins
 * @see <a href="https://leetcode.com/problems/arranging-coins/">Source</a>
 */
@Easy("https://leetcode.com/problems/arranging-coins")
fun interface ArrangingCoins {
    operator fun invoke(coins: Int): Int
}

@BinarySearch
class ArrangingCoinsBS : ArrangingCoins {
    override fun invoke(coins: Int): Int {
        var lowerBound = 0
        var upperBound = coins
        var midPoint: Int
        var current: Int

        while (lowerBound <= upperBound) {
            midPoint = lowerBound + (upperBound - lowerBound) / 2
            current = midPoint * (midPoint + 1) / 2
            if (current == coins) return midPoint
            if (coins < current) {
                upperBound = midPoint - 1
            } else {
                lowerBound = midPoint + 1
            }
        }
        return upperBound
    }
}

@Math
class ArrangingCoinsMath : ArrangingCoins {
    override fun invoke(coins: Int): Int {
        val result = sqrt(COEFFICIENT1 * coins + COEFFICIENT2) - COEFFICIENT3
        return result.toInt()
    }

    // todo refactor
    companion object {
        private const val COEFFICIENT1 = 2
        private const val COEFFICIENT2 = 0.25
        private const val COEFFICIENT3 = 0.5
    }
}
