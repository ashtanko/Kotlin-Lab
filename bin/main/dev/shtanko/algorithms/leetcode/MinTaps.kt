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

import dev.shtanko.algorithms.E_9
import kotlin.math.max
import kotlin.math.min

/**
 * 1326. Minimum Number of Taps to Open to Water a Garden
 * @see <a href="https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden">Source</a>
 */
fun interface MinTaps {
    operator fun invoke(n: Int, ranges: IntArray): Int
}

/**
 * Approach 1: Dynamic Programming
 */
class MinTapsDP : MinTaps {
    override operator fun invoke(n: Int, ranges: IntArray): Int {
        // Create an array to store the minimum number of taps needed for each position
        val dp = IntArray(n + 1) { E_9.toInt() }

        // Initialize the starting position of the garden
        dp[0] = 0

        for (i in 0..n) {
            // Calculate the leftmost position reachable by the current tap
            val tapStart = max(0, i - ranges[i])
            // Calculate the rightmost position reachable by the current tap
            val tapEnd = min(n, i + ranges[i])
            for (j in tapStart..tapEnd) {
                // Update with the minimum number of taps
                dp[tapEnd] = min(dp[tapEnd].toDouble(), (dp[j] + 1).toDouble()).toInt()
            }
        }

        // Check if the garden can be watered completely
        // Return the minimum number of taps needed to water the entire garden
        return if (dp[n] == E_9.toInt()) {
            // Garden cannot be watered
            -1
        } else {
            dp[n]
        }
    }
}

/**
 * Approach 2: Greedy
 */
class MinTapsGreedy : MinTaps {
    override operator fun invoke(n: Int, ranges: IntArray): Int {
        // Create an array to track the maximum reach for each position
        val maxReach = IntArray(n + 1)

        // Calculate the maximum reach for each tap
        for (i in ranges.indices) {
            // Calculate the leftmost position the tap can reach
            val start = max(0, i - ranges[i])
            // Calculate the rightmost position the tap can reach
            val end = min(n, i + ranges[i])

            // Update the maximum reach for the leftmost position
            maxReach[start] = max(maxReach[start].toDouble(), end.toDouble()).toInt()
        }

        // Number of taps used
        var taps = 0
        // Current rightmost position reached
        var currEnd = 0
        // Next rightmost position that can be reached
        var nextEnd = 0

        // Iterate through the garden
        for (i in 0..n) {
            // Current position cannot be reached
            if (i > nextEnd) {
                return -1
            }

            // Increment taps when moving to a new tap
            if (i > currEnd) {
                taps++
                // Move to the rightmost position that can be reached
                currEnd = nextEnd
            }

            // Update the next rightmost position that can be reached
            nextEnd = max(nextEnd.toDouble(), maxReach[i].toDouble()).toInt()
        }

        // Return the minimum number of taps used
        return taps
    }
}
