/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.DFS

/**
 * 2267. Check if There Is a Valid Parentheses String Path
 * @see <a href="https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path">Source</a>
 */
fun interface ValidParenthesesStringPath {
    operator fun invoke(grid: Array<CharArray>): Boolean
}

@DFS
class ValidParenthesesStringPathDFS : ValidParenthesesStringPath {

    override fun invoke(grid: Array<CharArray>): Boolean {
        val m: Int = grid.size
        val n: Int = grid[0].size
        val dp = Array(m + 1) {
            Array(n + 1) {
                BooleanArray(ARR_SIZE)
            }
        }
        dp[0][0][1] = true
        for (i in 0 until m) for (j in 0 until n) for (k in 1..LIMIT) {
            dp[i][j + 1][k] = dp[i][j + 1][k] or dp[i][j][k + (if (grid[i][j] == '(') -1 else 1)]
            dp[i + 1][j][k] = dp[i + 1][j][k] or dp[i][j][k + (if (grid[i][j] == '(') -1 else 1)]
        }
        return dp[m][n - 1][1]
    }

    companion object {
        private const val LIMIT = 101
        private const val ARR_SIZE = 103
    }
}
