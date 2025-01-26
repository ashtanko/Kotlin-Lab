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

/**
 * 97. Interleaving String
 * @see <a href="https://leetcode.com/problems/interleaving-string/">Source</a>
 */
fun interface InterleavingStringStrategy {
    operator fun invoke(s1: String, s2: String, s3: String): Boolean
}

class InterleavingStringBruteForce : InterleavingStringStrategy {
    override operator fun invoke(s1: String, s2: String, s3: String): Boolean {
        return Triple(s1, s2, s3).isInterleave(0, 0, "")
    }

    private fun Triple<String, String, String>.isInterleave(i: Int, j: Int, res: String): Boolean {
        if (res == third && i == first.length && j == second.length) return true
        var ans = false
        if (i < first.length) ans = ans or isInterleave(i + 1, j, res + first[i])
        if (j < second.length) ans = ans or isInterleave(i, j + 1, res + second[j])
        return ans
    }
}

// Recursion with memoization
class InterleavingStringRecursionWithMemo : InterleavingStringStrategy {
    override operator fun invoke(s1: String, s2: String, s3: String): Boolean {
        val memo = Array(s1.length) { IntArray(s2.length) }
        for (i in s1.indices) {
            for (j in s2.indices) {
                memo[i][j] = -1
            }
        }
        return Triple(s1, s2, s3).isInterleave(0, 0, 0, memo)
    }

    private fun Triple<String, String, String>.isInterleave(
        i: Int,
        j: Int,
        k: Int,
        memo: Array<IntArray>,
    ): Boolean {
        if (i == first.length) {
            return second.substring(j) == third.substring(k)
        }
        if (j == second.length) {
            return first.substring(i) == third.substring(k)
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1
        }
        var ans = false
        val isThirdEqualFirst = third[k] == first[i]
        val isThirdEqualSecond = third[k] == second[j]
        val thirdToFirstPredicate = isThirdEqualFirst && isInterleave(i + 1, j, k + 1, memo)
        val thirdToSecondPredicate = isThirdEqualSecond && isInterleave(i, j + 1, k + 1, memo)
        if (thirdToFirstPredicate || thirdToSecondPredicate) {
            ans = true
        }
        memo[i][j] = if (ans) 1 else 0
        return ans
    }
}

// Using 2D Dynamic Programming
class InterleavingString2D : InterleavingStringStrategy {
    override operator fun invoke(s1: String, s2: String, s3: String): Boolean {
        return isInterleave(s1, s2, s3)
    }

    private fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s3.length != s1.length + s2.length) {
            return false
        }
        val dp = Array(s1.length + 1) {
            BooleanArray(
                s2.length + 1,
            )
        }
        for (i in 0..s1.length) {
            for (j in 0..s2.length) {
                when {
                    i == 0 && j == 0 -> {
                        dp[i][j] = true
                    }

                    i == 0 -> {
                        dp[i][j] = dp[i][j - 1] && s2[j - 1] == s3[i + j - 1]
                    }

                    j == 0 -> {
                        dp[i][j] = dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]
                    }

                    else -> {
                        dp[i][j] =
                            dp[i - 1][j] && s1[i - 1] == s3[i + j - 1] || dp[i][j - 1] && s2[j - 1] == s3[i + j - 1]
                    }
                }
            }
        }
        return dp[s1.length][s2.length]
    }
}

// Using 1D Dynamic Programming
class InterleavingString1D : InterleavingStringStrategy {
    override operator fun invoke(s1: String, s2: String, s3: String): Boolean {
        return isInterleave(s1, s2, s3)
    }

    private fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s3.length != s1.length + s2.length) {
            return false
        }
        val dp = BooleanArray(s2.length + 1)
        for (i in 0..s1.length) {
            for (j in 0..s2.length) {
                when {
                    i == 0 && j == 0 -> {
                        dp[j] = true
                    }

                    i == 0 -> {
                        dp[j] = dp[j - 1] && s2[j - 1] == s3[i + j - 1]
                    }

                    j == 0 -> {
                        dp[j] = dp[j] && s1[i - 1] == s3[i + j - 1]
                    }

                    else -> {
                        dp[j] = dp[j] && s1[i - 1] == s3[i + j - 1] || dp[j - 1] && s2[j - 1] == s3[i + j - 1]
                    }
                }
            }
        }
        return dp[s2.length]
    }
}
