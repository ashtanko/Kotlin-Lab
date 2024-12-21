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

package dev.shtanko.algorithms.leetcode

/**
 * 1482. Minimum Number of Days to Make m Bouquets
 * @see <a href="https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/">Source</a>
 */
fun interface MinDaysToMakeBouquets {
    operator fun invoke(bloomDay: IntArray, m: Int, k: Int): Int
}

class MinDaysToMakeBouquetsBinarySearch : MinDaysToMakeBouquets {
    override fun invoke(bloomDay: IntArray, m: Int, k: Int): Int {
        if (m.toLong() * k > bloomDay.size) {
            return -1
        }

        var low = 1
        var high = 1_000_000_000
        while (low < high) {
            val mid = low + (high - low) / 2

            if (isPossibleBouquets(bloomDay, m, k, mid)) {
                high = mid
            } else {
                low = mid + 1
            }
        }

        return low
    }

    private fun isPossibleBouquets(bloomDay: IntArray, m: Int, k: Int, day: Int): Boolean {
        var totalBouquets = 0
        var flowers = 0

        for (currentDay in bloomDay) {
            if (currentDay <= day) {
                flowers++
                if (flowers == k) {
                    totalBouquets++
                    flowers = 0
                }
            } else {
                flowers = 0
            }

            if (totalBouquets >= m) {
                return true
            }
        }

        return false
    }
}
