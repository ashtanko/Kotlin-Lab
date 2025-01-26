/*
 * Copyright 2023 Oleksii Shtanko
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

import kotlin.math.max

/**
 * 1011. Capacity To Ship Packages Within D Days
 * @see <a href="https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/">Source</a>
 */
fun interface ShipWithinDays {
    operator fun invoke(weights: IntArray, days: Int): Int
}

/**
 * BINARY Search
 */
class ShipWithinDaysBS : ShipWithinDays {
    override operator fun invoke(weights: IntArray, days: Int): Int {
        var left = 0
        var right = 0
        for (i in weights) {
            left = max(left, i)
            right += i
        }
        var mid: Int
        var ans = right
        while (left <= right) {
            mid = (left + right) / 2
            if (check(weights, days, mid)) {
                ans = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return ans
    }

    private fun check(weights: IntArray, days: Int, capacity: Int): Boolean {
        var requiredDays = 1
        var currWeight = 0
        for (i in weights) {
            if (currWeight + i > capacity) {
                requiredDays++
                currWeight = 0
            }
            currWeight += i
        }
        return requiredDays <= days
    }
}
