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
import java.util.Arrays

/**
 * 1420. Build Array Where You Can Find The Maximum Exactly K Comparisons
 * @see <a href="https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons">
 *     Source</a>
 */
fun interface NumOfArrays {
    operator fun invoke(n: Int, m: Int, k: Int): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class NumOfArraysTopDown : NumOfArrays {

    private lateinit var memo: Array<Array<IntArray>>
    private var m0 = 0
    private var n0 = 0

    override fun invoke(n: Int, m: Int, k: Int): Int {
        memo = Array(n) { Array(m + 1) { IntArray(k + 1) } }
        for (i in 0 until n) {
            for (j in 0..m) {
                Arrays.fill(memo[i][j], -1)
            }
        }
        n0 = n
        m0 = m
        return dp(0, 0, k)
    }

    private fun dp(i: Int, maxSoFar: Int, remain: Int): Int {
        if (i == n0) {
            return if (remain == 0) {
                1
            } else {
                0
            }
        }
        if (remain < 0) {
            return 0
        }
        if (memo[i][maxSoFar][remain] != -1) {
            return memo[i][maxSoFar][remain]
        }
        var ans = 0
        for (num in 1..maxSoFar) {
            ans = (ans + dp(i + 1, maxSoFar, remain)) % MOD
        }
        for (num in maxSoFar + 1..m0) {
            ans = (ans + dp(i + 1, num, remain - 1)) % MOD
        }
        memo[i][maxSoFar][remain] = ans
        return ans
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 */
class NumOfArraysBottomUp : NumOfArrays {
    override fun invoke(n: Int, m: Int, k: Int): Int {
        val dp = Array(n + 1) {
            Array(m + 1) {
                IntArray(
                    k + 1,
                )
            }
        }

        for (num in 0 until dp[0].size) {
            dp[n][num][0] = 1
        }

        for (i in n - 1 downTo 0) {
            for (maxSoFar in m downTo 0) {
                for (remain in 0..k) {
                    var ans = 0
                    for (num in 1..maxSoFar) {
                        ans = (ans + dp[i + 1][maxSoFar][remain]) % MOD
                    }
                    if (remain > 0) {
                        for (num in maxSoFar + 1..m) {
                            ans = (ans + dp[i + 1][num][remain - 1]) % MOD
                        }
                    }
                    dp[i][maxSoFar][remain] = ans
                }
            }
        }

        return dp[0][0][k]
    }
}

/**
 * Approach 3: Space-Optimized Dynamic Programming
 */
class NumOfArraysSpaceOptimizedDp : NumOfArrays {
    override fun invoke(n: Int, m: Int, k: Int): Int {
        var dp = Array(m + 1) { IntArray(k + 1) }
        var prevDp = Array(m + 1) { IntArray(k + 1) }
        for (num in dp.indices) {
            prevDp[num][0] = 1
        }

        for (i in n - 1 downTo 0) {
            dp = Array(m + 1) { IntArray(k + 1) }
            for (maxSoFar in m downTo 0) {
                for (remain in 0..k) {
                    var ans = 0
                    for (num in 1..maxSoFar) {
                        ans = (ans + prevDp[maxSoFar][remain]) % MOD
                    }
                    if (remain > 0) {
                        for (num in maxSoFar + 1..m) {
                            ans = (ans + prevDp[num][remain - 1]) % MOD
                        }
                    }
                    dp[maxSoFar][remain] = ans
                }
            }
            prevDp = dp
        }

        return dp[0][k]
    }
}

/**
 * Approach 4: A Different DP + Prefix Sums
 */
class NumOfArraysPrefixSums : NumOfArrays {
    override fun invoke(n: Int, m: Int, k: Int): Int {
        val dp = Array(n + 1) { Array(m + 1) { LongArray(k + 1) } }
        val prefix = Array(n + 1) { Array(m + 1) { LongArray(k + 1) } }

        for (num in 1..m) {
            dp[1][num][1] = 1
            prefix[1][num][1] = prefix[1][num - 1][1] + 1
        }

        for (i in 1..n) {
            for (maxNum in 1..m) {
                for (cost in 1..k) {
                    var ans = maxNum.toLong() * dp[i - 1][maxNum][cost] % MOD
                    ans = (ans + prefix[i - 1][maxNum - 1][cost - 1]) % MOD

                    dp[i][maxNum][cost] = (dp[i][maxNum][cost] + ans) % MOD

                    prefix[i][maxNum][cost] = (prefix[i][maxNum - 1][cost] + dp[i][maxNum][cost]) % MOD
                }
            }
        }

        return prefix[n][m][k].toInt()
    }
}

/**
 * Approach 5: Space-Optimized Better DP
 */
class NumOfArraysBetterDp : NumOfArrays {
    override fun invoke(n: Int, m: Int, k: Int): Int {
        var dp = Array(m + 1) { LongArray(k + 1) }
        var prefix = Array(m + 1) { LongArray(k + 1) }
        var prevDp = Array(m + 1) { LongArray(k + 1) }
        var prevPrefix = Array(m + 1) { LongArray(k + 1) }

        for (num in 1..m) {
            dp[num][1] = 1
        }

        for (i in 1..n) {
            if (i > 1) {
                dp = Array(m + 1) { LongArray(k + 1) }
            }
            prefix = Array(m + 1) { LongArray(k + 1) }
            for (maxNum in 1..m) {
                for (cost in 1..k) {
                    var ans = maxNum * prevDp[maxNum][cost] % MOD
                    ans = (ans + prevPrefix[maxNum - 1][cost - 1]) % MOD
                    dp[maxNum][cost] += ans
                    dp[maxNum][cost] %= MOD.toLong()
                    prefix[maxNum][cost] = prefix[maxNum - 1][cost] + dp[maxNum][cost]
                    prefix[maxNum][cost] %= MOD.toLong()
                }
            }
            prevDp = dp
            prevPrefix = prefix
        }

        return prefix[m][k].toInt()
    }
}
