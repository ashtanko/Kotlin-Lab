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

import kotlin.math.abs
import kotlin.math.max

/**
 * 956. Tallest Billboard
 * @see <a href="https://leetcode.com/problems/tallest-billboard/">Source</a>
 */
fun interface TallestBillboard {
    operator fun invoke(rods: IntArray): Int
}

/**
 * Approach 1: Meet in the Middle
 */
class TallestBillboardMiddle : TallestBillboard {
    override operator fun invoke(rods: IntArray): Int {
        val n: Int = rods.size
        val firstHalf = helper(rods, 0, n / 2)
        val secondHalf = helper(rods, n / 2, n)

        var answer = 0
        for (diff in firstHalf.keys) {
            if (secondHalf.containsKey(-diff)) {
                answer = max(answer, firstHalf[diff]!! + secondHalf[-diff]!!)
            }
        }
        return answer
    }

    // Helper function to collect every combination `(left, right)`
    private fun helper(rods: IntArray, leftIndex: Int, rightIndex: Int): Map<Int, Int> {
        val states: MutableSet<Pair<Int, Int>> = HashSet()
        states.add(Pair(0, 0))
        for (i in leftIndex until rightIndex) {
            val r = rods[i]
            val newStates: MutableSet<Pair<Int, Int>> = HashSet()
            for (p in states) {
                newStates.add(Pair(p.first + r, p.second))
                newStates.add(Pair(p.first, p.second + r))
            }
            states.addAll(newStates)
        }
        val dp: MutableMap<Int, Int> = HashMap()
        for (p in states) {
            val left: Int = p.first
            val right: Int = p.second
            dp[left - right] = max(dp.getOrDefault(left - right, 0), left)
        }
        return dp
    }
}

/**
 * Approach 2: Dynamic Programming
 */
class TallestBillboardDP : TallestBillboard {
    override operator fun invoke(rods: IntArray): Int {
        // dp[taller - shorter] = taller
        var dp: MutableMap<Int, Int> = HashMap()
        dp[0] = 0

        for (r in rods) {
            // newDp means we don't add r to these stands.
            val newDp: MutableMap<Int, Int> = HashMap(dp)
            for ((diff, taller) in dp) {
                val shorter = taller - diff

                // Add r to the taller stand, thus the height difference is increased to diff + r.
                val newTaller = newDp.getOrDefault(diff + r, 0)
                newDp[diff + r] = max(newTaller, taller + r)

                // Add r to the shorter stand, the height difference is expressed as abs(shorter + r - taller).
                val newDiff = abs(shorter + r - taller)
                val newTaller2 = max(shorter + r, taller)
                newDp[newDiff] = max(newTaller2, newDp.getOrDefault(newDiff, 0))
            }
            dp = newDp
        }

        // Return the maximum height with 0 difference.
        return dp.getOrDefault(0, 0)
    }
}
