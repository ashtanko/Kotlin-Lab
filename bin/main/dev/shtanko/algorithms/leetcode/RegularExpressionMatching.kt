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

fun interface RegularExpressionMatch {
    operator fun invoke(text: String, pattern: String): Boolean
}

class RegularExpressionMatchRecursion : RegularExpressionMatch {
    override operator fun invoke(text: String, pattern: String): Boolean {
        if (pattern.isEmpty()) return text.isEmpty()
        val isFirstMatch = text.isNotEmpty() &&
            (pattern[0] == text[0] || pattern[0] == '.')
        return if (pattern.length >= 2 && pattern[1] == '*') {
            invoke(text, pattern.substring(2)) ||
                isFirstMatch && invoke(text.substring(1), pattern)
        } else {
            isFirstMatch && invoke(text.substring(1), pattern.substring(1))
        }
    }
}

class RegularExpressionMatchDPTopDown : RegularExpressionMatch {

    enum class Result {
        TRUE, FALSE
    }

    private lateinit var memo: Array<Array<Result?>>

    override operator fun invoke(text: String, pattern: String): Boolean {
        memo = Array(text.length + 1) { arrayOfNulls<Result>(pattern.length + 1) }
        return dp(0, 0, text, pattern)
    }

    private fun dp(i: Int, j: Int, text: String, pattern: String): Boolean {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE
        }
        val ans = if (j == pattern.length) {
            i == text.length
        } else {
            val isFirstMatch = i < text.length && pattern[j] == text[i] || pattern[j] == '.'
            if (j + 1 < pattern.length && pattern[j + 1] == '*') {
                dp(i, j + 2, text, pattern) || isFirstMatch && dp(i + 1, j, text, pattern)
            } else {
                isFirstMatch && dp(i + 1, j + 1, text, pattern)
            }
        }
        memo[i][j] = if (ans) Result.TRUE else Result.FALSE
        return ans
    }
}

class RegularExpressionMatchDPBottomUp : RegularExpressionMatch {
    override operator fun invoke(text: String, pattern: String): Boolean {
        val dp = Array(text.length + 1) { BooleanArray(pattern.length + 1) }
        dp[text.length][pattern.length] = true
        for (i in text.length downTo 0) {
            for (j in pattern.length - 1 downTo 0) {
                val isFirstMatch = i < text.length && (pattern[j] == text[i] || pattern[j] == '.')
                if (j + 1 < pattern.length && pattern[j + 1] == '*') {
                    dp[i][j] = dp[i][j + 2] || isFirstMatch && dp[i + 1][j]
                } else {
                    dp[i][j] = isFirstMatch && dp[i + 1][j + 1]
                }
            }
        }
        return dp[0][0]
    }
}
