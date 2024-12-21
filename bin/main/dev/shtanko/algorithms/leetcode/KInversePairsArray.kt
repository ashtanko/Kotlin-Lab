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
import dev.shtanko.algorithms.leetcode.KInversePairsArray.Companion.DEFAULT_ARRAY_SIZE
import kotlin.math.min

/**
 * 629. K Inverse Pairs Array
 * @see <a href="https://leetcode.com/problems/k-inverse-pairs-array">Source</a>
 */
fun interface KInversePairsArray {
    operator fun invoke(length: Int, numberOfPairs: Int): Int

    companion object {
        const val DEFAULT_ARRAY_SIZE = 1001
    }
}

/**
 * Approach 2: Using Recursion with Memoization
 */
class KInversePairsArrayRecursion : KInversePairsArray {
    private var memo = Array(DEFAULT_ARRAY_SIZE) { arrayOfNulls<Int>(DEFAULT_ARRAY_SIZE) }

    override fun invoke(length: Int, numberOfPairs: Int): Int {
        if (length == 0) return 0
        if (numberOfPairs == 0) return 1
        if (memo[length][numberOfPairs] != null) return memo[length][numberOfPairs] ?: -1
        var inversePairsCount = 0
        for (i in 0..min(numberOfPairs, length - 1)) {
            inversePairsCount = (inversePairsCount + invoke(length - 1, numberOfPairs - i)) % MOD
        }
        memo[length][numberOfPairs] = inversePairsCount
        return inversePairsCount
    }
}

/**
 * Approach 3: Dynamic Programming
 */
class KInversePairsArrayDP : KInversePairsArray {
    override fun invoke(length: Int, numberOfPairs: Int): Int {
        val dpTable = Array(length + 1) { IntArray(numberOfPairs + 1) }
        for (i in 1..length) {
            for (j in 0..numberOfPairs) {
                if (j == 0) {
                    dpTable[i][j] = 1
                } else {
                    for (p in 0..min(j, i - 1)) {
                        dpTable[i][j] = (dpTable[i][j] + dpTable[i - 1][j - p]) % MOD
                    }
                }
            }
        }
        return dpTable[length][numberOfPairs]
    }
}

/**
 * Approach 4: Dynamic Programming with Cumulative Sum
 */
class CumulativeSum : KInversePairsArray {
    override fun invoke(length: Int, numberOfPairs: Int): Int {
        val dp = Array(length + 1) { IntArray(numberOfPairs + 1) }
        for (i in 1..length) {
            for (j in 0..numberOfPairs) {
                if (j == 0) {
                    dp[i][j] = 1
                } else {
                    val value = (
                        dp[i - 1][j] + MOD - if (j - i >= 0) {
                            dp[i - 1][j - i]
                        } else {
                            0
                        }
                        ) % MOD
                    dp[i][j] = (dp[i][j - 1] + value) % MOD
                }
            }
        }
        return (dp[length][numberOfPairs] + MOD - if (numberOfPairs > 0) dp[length][numberOfPairs - 1] else 0) % MOD
    }
}

/**
 * Approach 5: Another Optimized Dynamic Programming Approach
 */
class KInversePairsArrayOptimizedDP : KInversePairsArray {
    override operator fun invoke(length: Int, numberOfPairs: Int): Int {
        val dp = Array(length + 1) { IntArray(numberOfPairs + 1) }

        for (i in 1..length) {
            var counter = 0
            while (counter <= numberOfPairs && counter <= i * (i - 1) / 2) {
                if (i == 1 && counter == 0) {
                    dp[i][counter] = 1
                    break
                } else if (counter == 0) {
                    dp[i][counter] = 1
                } else {
                    val value =
                        (dp[i - 1][counter] + MOD - if (counter - i >= 0) dp[i - 1][counter - i] else 0) % MOD
                    dp[i][counter] = (dp[i][counter - 1] + value) % MOD
                }
                counter++
            }
        }

        return dp[length][numberOfPairs]
    }
}

/**
 * Approach 6: Once Again Memoization
 */
class KInversePairsArrayMemoization : KInversePairsArray {
    private var memoizationTable = Array(DEFAULT_ARRAY_SIZE) { arrayOfNulls<Int>(DEFAULT_ARRAY_SIZE) }

    override fun invoke(length: Int, numberOfPairs: Int): Int {
        val res = computeInversePairs(length, numberOfPairs) + MOD - if (numberOfPairs > 0) {
            computeInversePairs(length, numberOfPairs - 1)
        } else {
            0
        }
        return res % MOD
    }

    private fun computeInversePairs(n: Int, k: Int): Int {
        if (n == 0) return 0
        if (k == 0) return 1
        if (memoizationTable[n][k] != null) return memoizationTable[n][k] ?: -1
        val value: Int =
            (computeInversePairs(n - 1, k) + MOD - if (k - n >= 0) computeInversePairs(n - 1, k - n) else 0) % MOD
        memoizationTable[n][k] = (computeInversePairs(n, k - 1) + value) % MOD
        return memoizationTable[n][k] ?: -1
    }
}

/**
 * Approach 7: 1-D Dynamic Programming
 */
class KInversePairsArrayDP1D : KInversePairsArray {
    override fun invoke(length: Int, numberOfPairs: Int): Int {
        var dp = IntArray(numberOfPairs + 1)
        for (i in 1..length) {
            val tempDp = IntArray(numberOfPairs + 1)
            tempDp[0] = 1
            for (j in 1..numberOfPairs) {
                val value = (dp[j] + MOD - if (j - i >= 0) dp[j - i] else 0) % MOD
                tempDp[j] = (tempDp[j - 1] + value) % MOD
            }
            dp = tempDp
        }
        return (dp[numberOfPairs] + MOD - if (numberOfPairs > 0) dp[numberOfPairs - 1] else 0) % MOD
    }
}
