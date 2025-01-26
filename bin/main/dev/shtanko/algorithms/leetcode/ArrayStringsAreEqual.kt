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

import dev.shtanko.algorithms.annotations.TwoPointers
import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1662. Check If Two String Arrays are Equivalent
 * @see <a href="https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent">Source</a>
 */
@Easy(link = "https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent")
fun interface ArrayStringsAreEqual {
    operator fun invoke(word1: Array<String>, word2: Array<String>): Boolean
}

class ArrayStringsAreEqualCompare : ArrayStringsAreEqual {
    override fun invoke(word1: Array<String>, word2: Array<String>): Boolean {
        // Creates a new string by combining all the strings in word1.
        val word1Combined = StringBuilder()
        for (s in word1) {
            word1Combined.append(s)
        }

        // Creates a new string by combining all the strings in word2.
        val word2Combined = StringBuilder()
        for (s in word2) {
            word2Combined.append(s)
        }

        // Returns true if both string are the same.
        return word1Combined.compareTo(word2Combined) == 0
    }
}

@TwoPointers
class ArrayStringsAreEqualTwoPointers : ArrayStringsAreEqual {
    override fun invoke(word1: Array<String>, word2: Array<String>): Boolean {
        var word1Pointer = 0
        var word2Pointer = 0
        var string1Pointer = 0
        var string2Pointer = 0

        while (word1Pointer < word1.size && word2Pointer < word2.size) {
            if (word1[word1Pointer][string1Pointer++] !=
                word2[word2Pointer][string2Pointer++]
            ) {
                return false
            }

            if (string1Pointer == word1[word1Pointer].length) {
                word1Pointer++
                string1Pointer = 0
            }

            if (string2Pointer == word2[word2Pointer].length) {
                word2Pointer++
                string2Pointer = 0
            }
        }

        return word1Pointer == word1.size && word2Pointer == word2.size
    }
}
