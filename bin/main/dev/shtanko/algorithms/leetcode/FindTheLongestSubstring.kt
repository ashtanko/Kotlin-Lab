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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 1371. Find the Longest Substring Containing Vowels in Even Counts
 * @see <a href="https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/">Source</a>
 */
fun interface FindTheLongestSubstring {
    operator fun invoke(str: String): Int
}

data object FindTheLongestSubstringBitmask : FindTheLongestSubstring {
    override fun invoke(str: String): Int {
        var currentXOR = 0
        val vowelBitmask = IntArray(ALPHABET_LETTERS_COUNT)
        vowelBitmask['a' - 'a'] = 1
        vowelBitmask['e' - 'a'] = 2
        vowelBitmask['i' - 'a'] = 4
        vowelBitmask['o' - 'a'] = 8
        vowelBitmask['u' - 'a'] = 16
        val xorPositions = IntArray(32) { -1 }
        var longestSubstringLength = 0

        for (i in str.indices) {
            currentXOR = currentXOR xor vowelBitmask[str[i] - 'a']
            if (xorPositions[currentXOR] == -1 && currentXOR != 0) {
                xorPositions[currentXOR] = i
            }
            longestSubstringLength = maxOf(longestSubstringLength, i - xorPositions[currentXOR])
        }
        return longestSubstringLength
    }
}
