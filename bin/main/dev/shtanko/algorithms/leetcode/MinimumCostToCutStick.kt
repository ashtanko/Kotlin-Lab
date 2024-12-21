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

import java.util.Arrays
import kotlin.math.min

/**
 * 1547. Minimum Cost to Cut a Stick
 * @see <a href="https://leetcode.com/problems/minimum-cost-to-cut-a-stick">Source</a>
 */
fun interface MinimumCostToCutStick {
    fun minCost(n: Int, cuts: IntArray): Int
}

/**
 * Approach 1: Top-down Dynamic Programming
 */
class MinimumCostToCutStickTopDown : MinimumCostToCutStick {

    private lateinit var memo: Array<IntArray>
    private lateinit var newCuts: IntArray
    override fun minCost(n: Int, cuts: IntArray): Int {
        val m: Int = cuts.size
        newCuts = IntArray(m + 2)
        System.arraycopy(cuts, 0, newCuts, 1, m)
        newCuts[m + 1] = n
        Arrays.sort(newCuts)

        memo = Array(m + 2) { IntArray(m + 2) }
        for (r in 0 until m + 2) {
            Arrays.fill(memo[r], -1)
        }

        return cost(0, newCuts.size - 1)
    }

    private fun cost(left: Int, right: Int): Int {
        if (memo[left][right] != -1) {
            return memo[left][right]
        }
        if (right - left == 1) {
            return 0
        }
        var ans = Int.MAX_VALUE
        for (mid in left + 1 until right) {
            val cost = cost(left, mid) + cost(mid, right) + newCuts[right] - newCuts[left]
            ans = min(ans, cost)
        }
        memo[left][right] = ans
        return ans
    }
}

/**
 * Approach 2: Bottom-up Dynamic Programming
 */
class MinimumCostToCutStickBottomUp : MinimumCostToCutStick {
    override fun minCost(n: Int, cuts: IntArray): Int {
        val m: Int = cuts.size
        val newCuts = IntArray(m + 2)
        System.arraycopy(cuts, 0, newCuts, 1, m)
        newCuts[m + 1] = n
        newCuts.sort()

        val dp = Array(m + 2) { IntArray(m + 2) }

        for (diff in 2 until m + 2) {
            for (left in 0 until m + 2 - diff) {
                val right = left + diff
                var ans = Int.MAX_VALUE
                for (mid in left + 1 until right) {
                    ans = min(ans, dp[left][mid] + dp[mid][right] + newCuts[right] - newCuts[left])
                }
                dp[left][right] = ans
            }
        }

        return dp[0][m + 1]
    }
}
