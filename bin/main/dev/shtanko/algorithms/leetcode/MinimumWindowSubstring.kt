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

/**
 * 76. Minimum Window Substring
 * @see <a href="https://leetcode.com/problems/minimum-window-substring/">Source</a>
 */
fun interface MinimumWindowSubstring {
    operator fun invoke(str: String, target: String): String
}

/**
 * Approach 1: Sliding Window
 */
class MWSSlidingWindow : MinimumWindowSubstring {
    override fun invoke(str: String, target: String): String {
        if (str.isEmpty() || target.isEmpty()) {
            return ""
        }

        val charFrequencyMap = IntArray(LIMIT)
        for (c in target.toCharArray()) {
            charFrequencyMap[c.code]++
        }
        var start = 0
        var end = 0
        var minStart = 0
        var minLen = Int.MAX_VALUE
        var counter: Int = target.length
        while (end < str.length) {
            val currentChar: Char = str[end]
            if (charFrequencyMap[currentChar.code] > 0) counter--
            charFrequencyMap[currentChar.code]--
            end++
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start
                    minStart = start
                }
                val startChar: Char = str[start]
                charFrequencyMap[startChar.code]++
                if (charFrequencyMap[startChar.code] > 0) counter++
                start++
            }
        }

        return if (minLen == Int.MAX_VALUE) {
            ""
        } else {
            str.substring(minStart, minStart + minLen)
        }
    }

    companion object {
        private const val LIMIT = 128
    }
}

/**
 * Approach 2: Optimized Sliding Window
 */
class MWSSlidingLongestSubstring : MinimumWindowSubstring {
    override fun invoke(str: String, target: String): String {
        if (str.isEmpty() || target.isEmpty()) {
            return ""
        }

        val charFrequencyMap: HashMap<Char, Int> = HashMap()
        for (character in str.toCharArray()) charFrequencyMap[character] = 0
        for (character in target.toCharArray()) {
            if (charFrequencyMap.containsKey(character)) {
                charFrequencyMap[character] = charFrequencyMap.getOrDefault(character, 0) + 1
            } else {
                return ""
            }
        }

        var start = 0
        var end = 0
        var minStart = 0
        var minLen = Int.MAX_VALUE
        var counter: Int = target.length
        while (end < str.length) {
            val currentChar: Char = str[end]
            if (charFrequencyMap.getOrDefault(currentChar, 0) > 0) {
                counter--
            }
            charFrequencyMap[currentChar] = charFrequencyMap.getOrDefault(currentChar, 0) - 1
            end++
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start
                    minStart = start
                }
                val startChar: Char = str[start]
                charFrequencyMap[startChar] = charFrequencyMap.getOrDefault(startChar, 0) + 1
                if (charFrequencyMap.getOrDefault(startChar, 0) > 0) counter++
                start++
            }
        }
        return if (minLen == Int.MAX_VALUE) {
            ""
        } else {
            str.substring(minStart, minStart + minLen)
        }
    }
}
