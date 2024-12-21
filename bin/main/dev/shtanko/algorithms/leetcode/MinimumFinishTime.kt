/*
 * Copyright 2022 Oleksii Shtanko
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

import kotlin.math.min

/**
 * 2188. Minimum Time to Finish the Race
 * @see <a href="https://leetcode.com/problems/minimum-time-to-finish-the-race/">Source</a>
 */
fun interface MinimumFinishTime {
    operator fun invoke(tires: Array<IntArray>, changeTime: Int, numLaps: Int): Int
}

class MinimumFinishTimeDP : MinimumFinishTime {
    override operator fun invoke(tires: Array<IntArray>, changeTime: Int, numLaps: Int): Int {
        val minTime = IntArray(numLaps + 1) { Int.MAX_VALUE }
        for (tire in tires) {
            checkMinTime(tire, minTime, changeTime, numLaps)
        }
        for (i in 2..numLaps) {
            for (j in 1 until i) {
                val remain = i % j
                // Greedy, in order to get the minimal runtime, we should repeat the same loop as much as possible.
                val currMin: Int = if (remain != 0) {
                    i / j * (minTime[j] + changeTime) + minTime[remain]
                } else {
                    // The last changeTime is not required if remain is 0
                    i / j * (minTime[j] + changeTime) - changeTime
                }
                minTime[i] = min(minTime[i], currMin)
            }
        }

        return minTime[numLaps]
    }

    private fun checkMinTime(tire: IntArray, minTime: IntArray, changeTime: Int, numLaps: Int) {
        val base = tire[0]
        var lap = 1
        var curr = base
        minTime[lap] = min(minTime[lap], curr)
        var sum = base
        // Greedy, if changeTime + base is smaller, the minimal runtime for the next lap
        // will not be better than minTime[lap - 1] + changeTime + minTime[1]
        while (curr * tire[1] - base <= changeTime && lap++ < numLaps) {
            curr *= tire[1]
            sum += curr
            minTime[lap] = min(minTime[lap], sum)
        }
    }
}
