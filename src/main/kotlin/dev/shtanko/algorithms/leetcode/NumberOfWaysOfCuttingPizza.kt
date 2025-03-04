/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.annotations.DFS

/**
 * 1444. Number of Ways of Cutting a Pizza
 * @see <a href="https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/">Source</a>
 */
fun interface NumberOfWaysOfCuttingPizza {
    fun ways(pizza: Array<String>, k: Int): Int
}

@DFS
class NumberOfWaysOfCuttingPizzaPrefixSum : NumberOfWaysOfCuttingPizza {
    override fun ways(pizza: Array<String>, k: Int): Int {
        val m: Int = pizza.size
        val n: Int = pizza[0].length
        val dp = Array(k) {
            Array(m) {
                arrayOfNulls<Int>(n)
            }
        }
        val preSum = Array(m + 1) { IntArray(n + 1) }

        for (r in m - 1 downTo 0) {
            for (c in n - 1 downTo 0) {
                preSum[r][c] =
                    preSum[r][c + 1] + preSum[r + 1][c] - preSum[r + 1][c + 1] + if (pizza[r][c] == 'A') 1 else 0
            }
        }
        return dfs(m, n, k - 1, 0, 0, dp, preSum)
    }

    private fun dfs(
        m: Int,
        n: Int,
        k: Int,
        r: Int,
        c: Int,
        dp: Array<Array<Array<Int?>>>,
        preSum: Array<IntArray>,
    ): Int {
        if (preSum[r][c] == 0) return 0 // if the remain piece has no apple -> invalid
        if (k == 0) return 1 // found valid way after using k-1 cuts
        if (dp[k][r][c] != null) return dp[k][r][c]!!
        var ans = 0
        // cut in horizontal
        for (nr in r + 1 until m) {
            // cut if the upper piece contains at least one apple
            if (preSum[r][c] - preSum[nr][c] > 0) {
                ans = (ans + dfs(m, n, k - 1, nr, c, dp, preSum)) % MOD
            }
        }
        // cut in vertical
        for (nc in c + 1 until n) {
            if (preSum[r][c] - preSum[r][nc] > 0) {
                ans = (ans + dfs(m, n, k - 1, r, nc, dp, preSum)) % MOD
            }
        }
        return ans.also { dp[k][r][c] = it }
    }
}
