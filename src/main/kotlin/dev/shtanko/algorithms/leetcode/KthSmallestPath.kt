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

import dev.shtanko.extensions.second

/**
 * 1643. Kth The Smallest Instructions
 * @see <a href="https://leetcode.com/problems/kth-smallest-instructions/">Source</a>
 */
fun interface KthSmallestPath {
    operator fun invoke(destination: IntArray, k: Int): String
}

class KthSmallestPathImpl : KthSmallestPath {
    override operator fun invoke(destination: IntArray, k: Int): String {
        if (destination.isEmpty()) return ""
        val ti = destination.first()
        val tj = destination.second()
        val dp = Array(ti + 1) { IntArray(tj + 1) }
        for (i in ti downTo 0) {
            for (j in tj downTo 0) {
                when {
                    i == ti && j == tj -> {
                        dp[i][j] = 1
                    }

                    i == ti -> {
                        dp[i][j] = dp[i][j + 1]
                    }

                    j == tj -> {
                        dp[i][j] = dp[i + 1][j]
                    }

                    else -> {
                        dp[i][j] = dp[i + 1][j] + dp[i][j + 1]
                    }
                }
            }
        }

        // in each (i, j), we have dp[i][j] kind of instructions, which equal to dp[i][j+1] + dp[i+1][j]
        // all dp[i][j+1] kinds of instructions are lexicographically smaller than the left dp[i+1][j]
        // kinds of instructions.
        // we can just compare k with dp[i][j+1] to determine how to choose next step.
        val sb = StringBuilder()
        helper(dp, 0, 0, k, sb)
        return sb.toString()
    }

    private fun helper(dp: Array<IntArray>, i: Int, j: Int, k: Int, sb: StringBuilder) {
        var i0 = i
        var j0 = j
        if (i0 == dp.size - 1) {
            // if we came to most down position then we can only go right
            while (++j0 < dp[0].size) {
                sb.append("H")
            }
            return
        }
        if (j0 == dp[0].size - 1) {
            // if we came to most right position then we can only go down
            while (++i0 < dp.size) {
                sb.append("V")
            }
            return
        }
        if (dp[i0][j0 + 1] >= k) {
            // if k is smaller than the first dp[i][j+1] solutions, then we should go right
            sb.append("H")
            helper(dp, i0, j0 + 1, k, sb)
        } else {
            // else we go down, and we should also minus the first dp[i][j+1] solutions from k
            sb.append("V")
            helper(dp, i0 + 1, j0, k - dp[i0][j0 + 1], sb)
        }
    }
}
