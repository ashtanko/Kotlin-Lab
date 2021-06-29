/*
 * Copyright 2021 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import kotlin.math.max

/**
 * 3. Longest Substring Without Repeating Characters
 * @link https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
interface LengthOfLongestSubstring {
    fun perform(s: String): Int
}

/**
 * Approach 1: Brute Force
 * Time complexity : O(n^3).
 * Space complexity : O(min(n,m)).
 */
class LengthOfLongestSubstringBF : LengthOfLongestSubstring {
    override fun perform(s: String): Int {
        val n: Int = s.length
        var res = 0
        for (i in 0 until n) {
            for (j in i until n) {
                if (checkRepetition(s, i, j)) {
                    res = max(res, j - i + 1)
                }
            }
        }

        return res
    }

    private fun checkRepetition(s: String, start: Int, end: Int): Boolean {
        val chars = IntArray(MAXIMUM_VALUE)
        for (i in start..end) {
            val c = s[i]
            chars[c.toInt()]++
            if (chars[c.toInt()] > 1) {
                return false
            }
        }
        return true
    }

    companion object {
        private const val MAXIMUM_VALUE = 128
    }
}

/**
 * Approach 2: Sliding Window
 * Time complexity : O(2n) = O(n).
 * Space complexity : O(min(m,n)).
 */
class LLSSlidingWindow : LengthOfLongestSubstring {
    override fun perform(s: String): Int {
        val chars = IntArray(MAXIMUM_VALUE)

        var left = 0
        var right = 0

        var res = 0
        while (right < s.length) {
            val r: Char = s[right]
            chars[r.toInt()]++
            while (chars[r.toInt()] > 1) {
                val l: Char = s[left]
                chars[l.toInt()]--
                left++
            }
            res = Math.max(res, right - left + 1)
            right++
        }
        return res
    }

    companion object {
        private const val MAXIMUM_VALUE = 128
    }
}

/**
 * Approach 3: Sliding Window Optimized
 * Time complexity : O(n).
 * Space complexity : O(min(m,n)).
 */
class LLSSlidingWindowOpt : LengthOfLongestSubstring {
    override fun perform(s: String): Int {
        val chars = arrayOfNulls<Int>(MAXIMUM_VALUE)

        var left = 0
        var right = 0

        var res = 0
        while (right < s.length) {
            val r: Char = s[right]
            val index = chars[r.toInt()]
            if (index != null && index >= left && index < right) {
                left = index + 1
            }
            res = Math.max(res, right - left + 1)
            chars[r.toInt()] = right
            right++
        }

        return res
    }

    companion object {
        private const val MAXIMUM_VALUE = 128
    }
}