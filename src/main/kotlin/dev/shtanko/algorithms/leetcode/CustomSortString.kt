/*
 * Copyright 2024 Oleksii Shtanko
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
 * 791. Custom Sort String
 * @see <a href="https://leetcode.com/problems/custom-sort-string">Source</a>
 */
fun interface CustomSortString {
    operator fun invoke(order: String, str: String): String
}

/**
 * Approach 1: Custom Comparator
 */
class CustomSortStringComparator : CustomSortString {
    override fun invoke(order: String, str: String): String {
        val result = StringBuilder()
        val mp = HashMap<Char, Int>()
        for (char in str) {
            mp[char] = mp.getOrDefault(char, 0) + 1
        }
        for (char in order) {
            if (mp.containsKey(char)) {
                result.append(char.toString().repeat(mp[char] ?: 0))
                mp.remove(char)
            }
        }
        for ((char, count) in mp) {
            result.append(char.toString().repeat(count))
        }
        return result.toString()
    }
}

/**
 * Approach 2: Frequency Table and Counting
 */
class CustomSortStringFreqTable : CustomSortString {
    override fun invoke(order: String, str: String): String {
        // Create a frequency table
        val freq: MutableMap<Char, Int> = HashMap()

        // Initialize frequencies of letters
        // freq[c] = frequency of char c in s
        var size: Int = str.length
        for (i in 0 until size) {
            val letter: Char = str[i]
            freq[letter] = freq.getOrDefault(letter, 0) + 1
        }

        // Iterate order string to append to result
        size = order.length
        val result = StringBuilder()
        for (i in 0 until size) {
            val letter: Char = order[i]
            while (freq.getOrDefault(letter, 0) > 0) {
                result.append(letter)
                freq[letter] = freq.getOrDefault(letter, 0) - 1
            }
        }

        // Iterate through freq and append remaining letters
        // This is necessary because some letters may not appear in `order`
        for (letter in freq.keys) {
            var count = freq.getOrDefault(letter, 0)
            while (count > 0) {
                result.append(letter)
                count--
            }
        }

        // Return the result
        return result.toString()
    }
}
