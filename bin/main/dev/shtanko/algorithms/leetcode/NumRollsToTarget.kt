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

import dev.shtanko.algorithms.MOD

/**
 * 1155. Number of Dice Rolls With Target Sum
 * @see <a href="https://leetcode.com/problems/number-of-dice-rolls-with-target-sum">Source</a>
 */
fun interface NumRollsToTarget {
    operator fun invoke(n: Int, k: Int, target: Int): Int
}

class NumRollsToTargetBase : NumRollsToTarget {
    override fun invoke(n: Int, k: Int, target: Int): Int {
        return numRollsToTarget(n, k, target)
    }

    private fun numRollsToTarget(d: Int, f: Int, target: Int, res: Int = 0): Int {
        if (d == 0 || target <= 0) return if (d == target) 1 else 0

        var result = res
        for (i in 1..f) {
            result = (result + numRollsToTarget(d - 1, f, target - i)) % MOD
        }
        return result
    }
}

class NumRollsToTargetTopDownDp : NumRollsToTarget {

    companion object {
        private const val DP_DIMENSION_X = 31
        private const val DP_DIMENSION_Y = 1001
    }

    val dp = Array(DP_DIMENSION_X) { IntArray(DP_DIMENSION_Y) }

    override fun invoke(n: Int, k: Int, target: Int): Int {
        return numRollsToTarget(n, k, target)
    }

    private fun numRollsToTarget(d: Int, f: Int, target: Int, res: Int = 0): Int {
        if (d == 0 || target <= 0) return if (d == target) 1 else 0
        if (dp[d][target] != 0) return dp[d][target] - 1

        var result = res
        for (i in 1..f) {
            result = (result + numRollsToTarget(d - 1, f, target - i)) % MOD
        }
        dp[d][target] = result + 1
        return result
    }
}

class NumRollsToTargetBottomUpDp : NumRollsToTarget {
    override fun invoke(n: Int, k: Int, target: Int): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1

        for (i in 1..n) {
            val dp1 = IntArray(target + 1)
            for (j in 1..k) {
                for (d in j..target) {
                    dp1[d] = (dp1[d] + dp[d - j]) % MOD
                }
            }
            dp1.copyInto(dp)
        }

        return dp[target]
    }
}
