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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import java.util.Arrays
import kotlin.math.min

/**
 * 1002. Find Common Characters
 * @see <a href="https://leetcode.com/problems/find-common-characters/">Source</a>
 */
fun interface FindCommonCharacters {
    operator fun invoke(words: Array<String>): List<String>
}

class FrequencyIntersection : FindCommonCharacters {
    override fun invoke(words: Array<String>): List<String> {
        if (words.isEmpty()) {
            return emptyList()
        }
        val wordsSize: Int = words.size
        val commonCharacterCounts = IntArray(ALPHABET_LETTERS_COUNT)
        val currentCharacterCounts = IntArray(ALPHABET_LETTERS_COUNT)
        val result: MutableList<String> = ArrayList()

        // Initialize commonCharacterCounts with the characters from the first
        // word
        for (ch in words[0].toCharArray()) {
            commonCharacterCounts[ch.code - 'a'.code]++
        }

        for (i in 1 until wordsSize) {
            Arrays.fill(currentCharacterCounts, 0)

            // Count characters in the current word
            for (ch in words[i].toCharArray()) {
                currentCharacterCounts[ch.code - 'a'.code]++
            }

            // Update the common character counts to keep the minimum counts
            for (letter in 0..<ALPHABET_LETTERS_COUNT) {
                commonCharacterCounts[letter] = min(
                    commonCharacterCounts[letter].toDouble(),
                    currentCharacterCounts[letter].toDouble(),
                ).toInt()
            }
        }

        // Collect the common characters based on the final counts
        for (letter in 0..<ALPHABET_LETTERS_COUNT) {
            for (commonCount in 0 until commonCharacterCounts[letter]) {
                result.add((letter + 'a'.code).toChar().toString())
            }
        }

        return result
    }
}
