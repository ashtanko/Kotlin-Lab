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
 * 1915. Number of Wonderful Substrings
 * @see <a href="https://leetcode.com/problems/number-of-wonderful-substrings">Source</a>
 */
fun interface WonderfulSubstrings {
    /**
     * This function calculates the number of wonderful substrings in a string.
     * @param word The input string.
     * @return The number of wonderful substrings.
     */
    operator fun invoke(word: String): Long
}

class WonderfulSubstringsSlidingWindow : WonderfulSubstrings {
    override fun invoke(word: String): Long {
        val characters = word.toCharArray()
        val wordLength = characters.size

        var wonderfulSubstringCount = 0L
        val bitCountArray = LongArray(FREQUENCY_BIT_MASK)
        var bitRepresentation = 0

        for (i in 0 until wordLength) {
            val characterCode = characters[i].code - 'a'.code
            bitRepresentation = bitRepresentation xor (1 shl characterCode)

            var currentCount = bitCountArray[bitRepresentation] + if (bitRepresentation.countOneBits() <= 1) 1L else 0L

            for (j in 0 until 10) {
                currentCount += bitCountArray[bitRepresentation xor (1 shl j)]
            }

            wonderfulSubstringCount += currentCount
            bitCountArray[bitRepresentation] += 1L
        }

        return wonderfulSubstringCount
    }

    companion object {
        private const val FREQUENCY_BIT_MASK = 1 shl 11
    }
}
