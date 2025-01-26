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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 242. Valid Anagram
 * @see <a href="https://leetcode.com/problems/valid-anagram/">Source</a>
 */
fun interface ValidAnagram {
    operator fun invoke(source: String, target: String): Boolean
}

class ValidAnagramHashMap : ValidAnagram {
    override fun invoke(source: String, target: String): Boolean {
        val charFrequencyMap = HashMap<Char, Int>()
        val sourceLength: Int = source.length
        val targetLength: Int = target.length
        if (sourceLength != targetLength) {
            return false
        }
        for (index in 0 until sourceLength) {
            charFrequencyMap[source[index]] = charFrequencyMap.getOrDefault(source[index], 0) + 1
            charFrequencyMap[target[index]] = charFrequencyMap.getOrDefault(target[index], 0) - 1
        }
        for (char in charFrequencyMap.keys) {
            if (charFrequencyMap[char] != 0) {
                return false
            }
        }
        return true
    }
}

class ValidAnagramImpl : ValidAnagram {
    override fun invoke(source: String, target: String): Boolean {
        val alphabet = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in source.indices) {
            alphabet[source[i] - ALPHABET_FIRST_LETTER]++
        }
        for (i in target.indices) {
            alphabet[target[i] - ALPHABET_FIRST_LETTER]--
            if (alphabet[target[i] - ALPHABET_FIRST_LETTER] < 0) {
                return false
            }
        }
        for (i in alphabet) {
            if (i != 0) {
                return false
            }
        }
        return true
    }

    companion object {
        private const val ALPHABET_FIRST_LETTER = 'a'
    }
}
