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

import dev.shtanko.extensions.isVowel

/**
 * 2785. Sort Vowels in a String
 * @see <a href="https://leetcode.com/problems/sort-vowels-in-a-string">Source</a>
 */
fun interface SortVowels {
    operator fun invoke(s: String): String
}

sealed interface SortVowelsStrategy {

    /**
     * Approach 1: Sorting
     */
    data object Sorting : SortVowels, SortVowelsStrategy {
        override fun invoke(s: String): String {
            val temp = ArrayList<Char>()

            // Store the vowels in the temporary string.
            for (c in s.toCharArray()) {
                if (c.isVowel()) {
                    temp.add(c)
                }
            }

            // Sort the temporary string characters in ascending order.
            temp.sort()

            val ans = StringBuilder()
            var j = 0
            for (i in s.indices) {
                // If the character is a vowel, replace it with the character in the string temp.
                if (s[i].isVowel()) {
                    ans.append(temp[j])
                    j++
                } else {
                    ans.append(s[i])
                }
            }

            return ans.toString()
        }
    }

    /**
     * Approach 2: Counting Sort
     */
    data object CountingSort : SortVowels, SortVowelsStrategy {
        override fun invoke(s: String): String {
            val count = IntArray(LIMIT)

            // Store the frequencies for each character.
            for (c in s.toCharArray()) {
                if (c.isVowel()) {
                    count[c.code - 'A'.code]++
                }
            }

            // Sorted string having all the vowels.
            val sortedVowel = "AEIOUaeiou"
            val ans = java.lang.StringBuilder()
            var j = 0
            for (i in s.indices) {
                if (!s[i].isVowel()) {
                    ans.append(s[i])
                } else {
                    // Skip to the character which is having remaining count.
                    while (count[sortedVowel[j].code - 'A'.code] == 0) {
                        j++
                    }
                    ans.append(sortedVowel[j])
                    count[sortedVowel[j].code - 'A'.code]--
                }
            }
            return ans.toString()
        }
    }

    companion object {
        private const val LIMIT = 1000
    }
}
