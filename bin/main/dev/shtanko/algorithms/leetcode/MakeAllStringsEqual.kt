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
 * 1897. Redistribute Characters to Make All Strings Equal
 * @see <a href="https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal">Source</a>
 */
fun interface MakeAllStringsEqual {
    operator fun invoke(words: Array<String>): Boolean
}

class StringCharacterCountChecker : MakeAllStringsEqual {
    override fun invoke(words: Array<String>): Boolean {
        val characterCounts: MutableMap<Char, Int> = HashMap()

        // Count occurrences of each character in all words
        for (word in words) {
            for (char in word.toCharArray()) {
                characterCounts[char] = characterCounts.getOrDefault(char, 0) + 1
            }
        }

        val wordCount: Int = words.size

        // Check if the character counts are evenly divisible by the number of words
        for (count in characterCounts.values) {
            if (count % wordCount != 0) {
                return false
            }
        }

        return true
    }
}

class MakeAllStringsEqualArray : MakeAllStringsEqual {
    override fun invoke(words: Array<String>): Boolean {
        val characterCounts = IntArray(ALPHABET_LETTERS_COUNT)

        // Count occurrences of each character in all words
        for (word in words) {
            for (char in word.toCharArray()) {
                characterCounts[char.code - 'a'.code]++
            }
        }

        val wordCount: Int = words.size

        // Check if the character counts are evenly divisible by the number of words
        for (count in characterCounts) {
            if (count % wordCount != 0) {
                return false
            }
        }

        return true
    }
}
