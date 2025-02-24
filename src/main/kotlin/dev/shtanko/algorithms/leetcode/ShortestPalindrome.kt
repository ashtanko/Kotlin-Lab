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
 * 214. Shortest Palindrome
 * @see <a href="https://leetcode.com/problems/shortest-palindrome/">Shortest Palindrome</a>
 */
fun interface ShortestPalindromeStrategy {
    operator fun invoke(str: String): String
}

class ShortestPalindromeBruteForce : ShortestPalindromeStrategy {
    override operator fun invoke(str: String): String {
        val length = str.length
        var palindromeLength = 0
        for (k in length - 1 downTo 0) {
            var left = 0
            var right = k
            while (left < right) {
                if (str[left] != str[right]) break
                left++
                right--
            }
            if (left >= right) {
                palindromeLength = k
                break
            }
        }
        val stringBuilder = StringBuilder()
        for (i in length - 1 downTo palindromeLength + 1) {
            stringBuilder.append(str[i])
        }
        stringBuilder.append(str)
        return stringBuilder.toString()
    }
}

class ShortestPalindromeTwoPointers : ShortestPalindromeStrategy {
    override operator fun invoke(str: String): String {
        var input = str
        var left = 0
        var right = input.length - 1
        while (left < right) {
            val leftChar = input[left]
            val rightChar = input[right]
            if (leftChar == rightChar) {
                left++
                right--
            } else {
                input = input.substring(0, left) + rightChar + input.substring(left)
                left++
            }
        }
        return input
    }
}

class ShortestPalindromeMP : ShortestPalindromeStrategy {
    override operator fun invoke(input: String): String {
        val combinedString = input + "#" + StringBuilder(input).reverse().toString()
        val prefixTable = buildPrefixTable(combinedString)
        return StringBuilder(input.substring(prefixTable[prefixTable.size - 1])).reverse().toString() + input
    }

    private fun buildPrefixTable(pattern: String): IntArray {
        val prefixTable = IntArray(pattern.length)
        var prefixIndex = 0

        for (i in 1 until pattern.length) {
            if (pattern[prefixIndex] == pattern[i]) {
                prefixTable[i] = prefixTable[i - 1] + 1
                prefixIndex++
            } else {
                prefixIndex = prefixTable[i - 1]
                while (prefixIndex > 0 && pattern[prefixIndex] != pattern[i]) {
                    prefixIndex = prefixTable[prefixIndex - 1]
                }
                if (pattern[prefixIndex] == pattern[i]) {
                    prefixIndex++
                }
                prefixTable[i] = prefixIndex
            }
        }
        return prefixTable
    }
}
