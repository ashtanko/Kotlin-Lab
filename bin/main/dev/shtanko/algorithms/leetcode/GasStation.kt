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
 * 134. Gas Station
 * @see <a href="https://leetcode.com/problems/gas-station/">Source</a>
 */
fun interface GasStation {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int
}

class GasStationBruteForce : GasStation {
    override fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        val n: Int = gas.size
        var totalSurplus = 0
        var surplus = 0
        var start = 0

        for (i in 0 until n) {
            totalSurplus += gas[i] - cost[i]
            surplus += gas[i] - cost[i]
            if (surplus < 0) {
                surplus = 0
                start = i + 1
            }
        }
        return if (totalSurplus < 0) -1 else start
    }
}
