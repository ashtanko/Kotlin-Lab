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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 1930. Unique Length-3 Palindromic Subsequences
 * @see <a href="https://leetcode.com/problems/unique-length-3-palindromic-subsequences">Source</a>
 */
fun interface CountPalindromicSubsequence {
    operator fun invoke(str: String): Int
}

sealed interface CountPalindromicStrategy {

    /**
     * Approach 1: Count Letters In-Between
     */
    data object CountLetters : CountPalindromicSubsequence, CountPalindromicStrategy {
        override fun invoke(str: String): Int {
            val letters: MutableSet<Char> = HashSet()
            for (c in str.toCharArray()) {
                letters.add(c)
            }

            var ans = 0
            for (letter in letters) {
                var i = -1
                var j = 0
                for (k in str.indices) {
                    if (str[k] == letter) {
                        if (i == -1) {
                            i = k
                        }
                        j = k
                    }
                }
                val between: MutableSet<Char> = HashSet()
                for (k in i + 1 until j) {
                    between.add(str[k])
                }
                ans += between.size
            }

            return ans
        }
    }

    /**
     * Approach 2: Pre-Compute First and Last Indices
     */
    data object PreComputeFirstAndLastIndices : CountPalindromicSubsequence, CountPalindromicStrategy {
        override fun invoke(str: String): Int {
            val first = IntArray(ALPHABET_LETTERS_COUNT) { -1 }
            val last = IntArray(ALPHABET_LETTERS_COUNT)

            for (i in str.indices) {
                val curr: Int = str[i] - 'a'
                if (first[curr] == -1) {
                    first[curr] = i
                }
                last[curr] = i
            }

            var ans = 0
            for (i in 0..<ALPHABET_LETTERS_COUNT) {
                if (first[i] == -1) {
                    continue
                }
                val between: MutableSet<Char> = HashSet()
                for (j in first[i] + 1 until last[i]) {
                    between.add(str[j])
                }
                ans += between.size
            }

            return ans
        }
    }
}
