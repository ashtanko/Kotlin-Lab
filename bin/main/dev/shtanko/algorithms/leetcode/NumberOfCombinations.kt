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

import dev.shtanko.algorithms.MOD
import java.util.PriorityQueue

/**
 * 1977. Number of Ways to Separate Numbers
 * @see <a href="https://leetcode.com/problems/number-of-ways-to-separate-numbers/">Source</a>
 */
fun interface NumberOfCombinations {
    operator fun invoke(num: String): Int
}

class NumberOfCombinationsBottomUp : NumberOfCombinations {
    override operator fun invoke(num: String): Int {
        val cs = num.toCharArray()
        val n = cs.size
        val rank = Array(n) { IntArray(n + 1) }
        val pq: PriorityQueue<IntArray> = PriorityQueue<IntArray>(1) { a, b -> a[1] - b[1] }
        for (i in 1..n) {
            var c = 0
            var prev = 0
            var j = 0
            while (j + i <= n) {
                pq.add(intArrayOf(j, rank[j][i - 1] * 10 + cs[i + j - 1].code - '0'.code))
                ++j
            }
            while (pq.isNotEmpty()) {
                val cur: IntArray = pq.poll()
                if (cur[1] != prev) c++
                rank[cur[0]][i] = c
                prev = cur[1]
            }
        }
        val dp = Array(n) { IntArray(n + 1) }
        var j = n - 1
        while (0 <= j) {
            if ('0' == cs[j]) {
                --j
                continue
            }
            val len = n - j
            dp[j][len] = 1
            var i = len - 1
            while (1 <= i) {
                // dp[j][i] means the valid number that can start from j and the length of the first number
                // is at least i thus here I aggregate dp[j][i + 1] into dp[j][i]
                dp[j][i] = dp[j][i + 1]
                val next = i + j
                if (next >= n || next + i > n) {
                    --i
                    continue
                }
                // if the rank of the next part is greater than the current one
                if (rank[j][i] <= rank[next][i]) {
                    dp[j][i] = (dp[j][i] + dp[next][i]) % MOD
                } else if (next + i < n) {
                    dp[j][i] = (dp[j][i] + dp[next][i + 1]) % MOD
                }
                --i
            }
            dp[j][0] = dp[j][1]
            --j
        }
        return dp[0][0]
    }
}
