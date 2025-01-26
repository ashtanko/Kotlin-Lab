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

import kotlin.math.min

/**
 * 5. Longest Palindromic Substring
 * @see <a href="https://leetcode.com/problems/longest-palindromic-substring">Source</a>
 */
fun interface LongestPalindromicSubstring {
    operator fun invoke(s: String): String
}

/**
 * Approach 1: Check All Substrings
 */
class LongestPalindromicSubstringBF : LongestPalindromicSubstring {
    override fun invoke(s: String): String {
        for (length in s.length downTo 1) {
            for (start in 0..s.length - length) {
                if (check(start, start + length, s)) {
                    return s.substring(start, start + length)
                }
            }
        }

        return ""
    }

    private fun check(i: Int, j: Int, s: String): Boolean {
        var left = i
        var right = j - 1
        while (left < right) {
            if (s[left] != s[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }
}

/**
 * Approach 2: Dynamic Programming
 */
class LongestPalindromicSubstringDP : LongestPalindromicSubstring {
    override fun invoke(s: String): String {
        if (s.isEmpty()) {
            return ""
        }
        val n: Int = s.length
        val dp = Array(n) { BooleanArray(n) }
        val ans = intArrayOf(0, 0)

        for (i in 0 until n) {
            dp[i][i] = true
        }

        for (i in 0 until n - 1) {
            if (s[i] == s[i + 1]) {
                dp[i][i + 1] = true
                ans[0] = i
                ans[1] = i + 1
            }
        }

        for (diff in 2 until n) {
            for (i in 0 until n - diff) {
                val j = i + diff
                if (s[i] == s[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true
                    ans[0] = i
                    ans[1] = j
                }
            }
        }

        val i = ans[0]
        val j = ans[1]
        return s.substring(i, j + 1)
    }
}

/**
 * Approach 3: Expand From Centers
 */
class LongestPalindromicSubstringExpand : LongestPalindromicSubstring {
    override fun invoke(s: String): String {
        if (s.isEmpty()) {
            return ""
        }
        val ans = intArrayOf(0, 0)

        for (i in s.indices) {
            val oddLength = expand(i, i, s)
            if (oddLength > ans[1] - ans[0] + 1) {
                val dist = oddLength / 2
                ans[0] = i - dist
                ans[1] = i + dist
            }
            val evenLength = expand(i, i + 1, s)
            if (evenLength > ans[1] - ans[0] + 1) {
                val dist = evenLength / 2 - 1
                ans[0] = i - dist
                ans[1] = i + 1 + dist
            }
        }

        val i = ans[0]
        val j = ans[1]
        return s.substring(i, j + 1)
    }

    private fun expand(i: Int, j: Int, s: String): Int {
        var left = i
        var right = j
        while (left >= 0 && right < s.length && s[left] == s[right]) {
            left--
            right++
        }
        return right - left - 1
    }
}

/**
 * Approach 4: Manacher's Algorithm
 */
class LongestPalindromicSubstringManacher : LongestPalindromicSubstring {
    override fun invoke(s: String): String {
        val sPrime = StringBuilder("#")
        for (c in s.toCharArray()) {
            sPrime.append(c).append("#")
        }

        val n = sPrime.length
        val palindromeRadii = IntArray(n)
        var center = 0
        var radius = 0

        for (i in 0 until n) {
            val mirror = 2 * center - i
            if (i < radius) {
                palindromeRadii[i] =
                    min((radius - i).toDouble(), palindromeRadii[mirror].toDouble()).toInt()
            }
            while (i + 1 + palindromeRadii[i] < n &&
                i - 1 - palindromeRadii[i] >= 0 &&
                sPrime[i + 1 + palindromeRadii[i]] == sPrime[
                    i - 1 - palindromeRadii[i],
                ]
            ) {
                palindromeRadii[i]++
            }
            if (i + palindromeRadii[i] > radius) {
                center = i
                radius = i + palindromeRadii[i]
            }
        }

        var maxLength = 0
        var centerIndex = 0
        for (i in 0 until n) {
            if (palindromeRadii[i] > maxLength) {
                maxLength = palindromeRadii[i]
                centerIndex = i
            }
        }

        val startIndex = (centerIndex - maxLength) / 2

        return s.substring(startIndex, startIndex + maxLength)
    }
}
