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

import kotlin.math.ceil

/**
 * 1870. Minimum Speed to Arrive on Time
 * @see <a href="https://leetcode.com/problems/minimum-speed-to-arrive-on-time/">Source</a>
 */
fun interface MinSpeedToArriveOnTime {
    operator fun invoke(dist: IntArray, hour: Double): Int
}

/**
 * Approach: Binary Search
 */
class MinSpeedToArriveOnTimeBS : MinSpeedToArriveOnTime {

    companion object {
        private const val UPPER_BOUND = 10000000
    }

    override operator fun invoke(dist: IntArray, hour: Double): Int {
        var left = 1
        var right = UPPER_BOUND
        var minSpeed = -1

        while (left <= right) {
            val mid = (left + right) / 2

            // Move to the left half.
            if (calculateRequiredTime(dist, mid) <= hour) {
                minSpeed = mid
                right = mid - 1
            } else {
                // Move to the right half.
                left = mid + 1
            }
        }
        return minSpeed
    }

    private fun calculateRequiredTime(dist: IntArray, speed: Int): Double {
        var time = 0.0
        for (i in dist.indices) {
            val t = dist[i].toDouble() / speed.toDouble()
            // Round off to the next integer, if not the last ride.
            time += if (i == dist.size - 1) t else ceil(t)
        }
        return time
    }
}
