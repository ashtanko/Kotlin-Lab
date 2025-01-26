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
 * 646. Maximum Length of Pair Chain
 * @see <a href="https://leetcode.com/problems/maximum-length-of-pair-chain">Source</a>
 */
fun interface FindLongestChain {
    operator fun invoke(pairs: Array<IntArray>): Int
}

/**
 * Approach 1: Recursive Dynamic Programming
 */
class FindLongestChainRecursive : FindLongestChain {
    override operator fun invoke(pairs: Array<IntArray>): Int {
        val n = pairs.size
        pairs.sortWith(compareBy { it[0] })
        val memo = IntArray(n)

        var ans = 0
        for (i in 0 until n) {
            ans = ans.coerceAtLeast(longestPairChain(i, pairs, n, memo))
        }
        return ans
    }

    private fun longestPairChain(i: Int, pairs: Array<IntArray>, n: Int, memo: IntArray): Int {
        if (memo[i] != 0) {
            return memo[i]
        }
        memo[i] = 1
        for (j in i + 1 until n) {
            if (pairs[i][1] < pairs[j][0]) {
                memo[i] = memo[i].coerceAtLeast(1 + longestPairChain(j, pairs, n, memo))
            }
        }
        return memo[i]
    }
}

class FindLongestChainIterative : FindLongestChain {
    override operator fun invoke(pairs: Array<IntArray>): Int {
        val n = pairs.size
        pairs.sortWith(compareBy { it[0] })
        val dp = IntArray(n) { 1 }
        var ans = 1

        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                if (pairs[i][1] < pairs[j][0]) {
                    dp[i] = maxOf(dp[i], 1 + dp[j])
                }
            }
            ans = maxOf(ans, dp[i])
        }
        return ans
    }
}
