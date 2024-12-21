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
import dev.shtanko.algorithms.annotations.Bitwise
import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.Sort

/**
 * 1657. Determine if Two Strings Are Close
 * @see <a href="https://leetcode.com/problems/determine-if-two-strings-are-close/">Source</a>
 */
fun interface CloseStrings {
    operator fun invoke(word1: String, word2: String): Boolean
}

@Iterative
class CloseStringsMap : CloseStrings {

    override operator fun invoke(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) {
            return false
        }
        val wf = IntArray(ALPHABET_LETTERS_COUNT)
        val wf2 = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in word1.indices) {
            wf[word1[i] - 'a'] += 1
            wf2[word2[i] - 'a'] += 1
        }
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            if (wf[i] == 0 && wf2[i] != 0) return false
        }
        val map1: HashMap<Int, Int> = HashMap()
        val map2: HashMap<Int, Int> = HashMap()
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            map1[wf[i]] = map1.getOrDefault(wf[i], 0) + 1
            map2[wf2[i]] = map2.getOrDefault(wf2[i], 0) + 1
        }
        return map1 == map2
    }
}

@Bitwise
class CloseStringsBitwise : CloseStrings {

    override operator fun invoke(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) {
            return false
        }

        val v1 = IntArray(ALPHABET_LETTERS_COUNT)
        val v2 = IntArray(ALPHABET_LETTERS_COUNT)

        var k1 = 0
        var k2 = 0

        for (b in word1.toByteArray()) {
            val i = b - 'a'.code.toByte()
            v1[i]++
            k1 = k1 or (1 shl i)
        }

        for (b in word2.toByteArray()) {
            val i = b - 'a'.code.toByte()
            v2[i]++
            k2 = k2 or (1 shl i)
        }

        // Ensure the same characters are used
        if (k1 != k2) {
            return false
        }

        // Test that occurrence counts match
        v1.sort()
        v2.sort()

        return v1.contentEquals(v2)
    }
}

@Sort
class CloseStringsSort : CloseStrings {
    override operator fun invoke(word1: String, word2: String): Boolean {
        val freq1 = IntArray(ALPHABET_LETTERS_COUNT)
        val freq2 = IntArray(ALPHABET_LETTERS_COUNT)
        for (element in word1) {
            freq1[element - 'a']++
        }
        for (element in word2) {
            freq2[element - 'a']++
        }
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            if (freq1[i] == 0 && freq2[i] != 0 || freq1[i] != 0 && freq2[i] == 0) {
                return false
            }
        }
        freq1.sort()
        freq2.sort()
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            if (freq1[i] != freq2[i]) {
                return false
            }
        }
        return true
    }
}
