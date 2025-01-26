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
 * 1289. Minimum Falling Path Sum II
 * @see <a href="https://leetcode.com/problems/minimum-falling-path-sum-ii/">Source</a>
 */
fun interface MinimumFallingPathSum3 {
    fun minFallingPathSum(grid: Array<IntArray>): Int
}

class MinimumFallingPathSum3DP : MinimumFallingPathSum3 {
    override fun minFallingPathSum(grid: Array<IntArray>): Int {
        val n: Int = grid.size
        val m: Int = grid[0].size
        val dp = Array(n) { IntArray(m) }
        var min = Int.MAX_VALUE
        var minIndex = -1
        var secondMin = Int.MAX_VALUE
        for (i in 0 until n) {
            for (j in 0 until m) {
                dp[i][j] = Int.MAX_VALUE
                if (i == 0) {
                    dp[i][j] = grid[i][j]
                } else {
                    if (j == minIndex) {
                        dp[i][j] = min(secondMin + grid[i][j], dp[i][j])
                    } else {
                        dp[i][j] = min(min + grid[i][j], dp[i][j])
                    }
                }
            }
            min = Int.MAX_VALUE
            minIndex = -1
            secondMin = Int.MAX_VALUE
            for (j in 0 until m) {
                if (min >= dp[i][j]) {
                    secondMin = min
                    min = dp[i][j]
                    minIndex = j
                } else if (secondMin > dp[i][j]) {
                    secondMin = dp[i][j]
                }
            }
        }
        return min
    }
}
