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

/**
 * 2141. Maximum Running Time of N Computers
 * @see <a href="https://leetcode.com/problems/maximum-running-time-of-n-computers/">Source</a>
 */
fun interface MaxRunTime {
    operator fun invoke(n: Int, batteries: IntArray): Long
}

/**
 * Approach 1: Sorting and Prefix Sum
 */
class MaxRunTimePrefixSum : MaxRunTime {
    override operator fun invoke(n: Int, batteries: IntArray): Long {
        // Get the sum of all extra batteries.
        batteries.sort()
        var extra: Long = 0
        for (i in 0 until batteries.size - n) {
            extra += batteries[i]
        }

        // live stands for the n largest batteries we chose for n computers.
        val live = batteries.sliceArray(batteries.size - n until batteries.size)

        // We increase the total running time using 'extra' by increasing
        // the running time of the computer with the smallest battery.
        for (i in 0 until n - 1) {
            // If the target running time is between live[i] and live[i + 1].
            if (extra < (i + 1) * (live[i + 1] - live[i]).toLong()) {
                return live[i] + extra / (i + 1).toLong()
            }

            // Reduce 'extra' by the total power used.
            extra -= (i + 1) * (live[i + 1] - live[i]).toLong()
        }

        // If there is power left, we can increase the running time
        // of all computers.
        return live[n - 1] + extra / n.toLong()
    }
}

/**
 * Approach 2: Binary Search
 */
class MaxRunTimeBS : MaxRunTime {
    override operator fun invoke(n: Int, batteries: IntArray): Long {
        var sumPower: Long = 0
        for (power in batteries)
            sumPower += power

        var left: Long = 1
        var right: Long = sumPower / n

        while (left < right) {
            val target: Long = right - (right - left) / 2
            var extra: Long = 0

            for (power in batteries)
                extra += power.toLong().coerceAtMost(target)

            if (extra >= n * target) {
                left = target
            } else {
                right = target - 1
            }
        }

        return left
    }
}
