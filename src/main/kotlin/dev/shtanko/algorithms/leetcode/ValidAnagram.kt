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
    operator fun invoke(s: String, t: String): Boolean
}

class ValidAnagramHashMap : ValidAnagram {
    override fun invoke(s: String, t: String): Boolean {
        val sMap = HashMap<Char, Int>()
        val sl: Int = s.length
        val tl: Int = t.length
        if (sl != tl) {
            return false
        }
        for (i in 0 until sl) {
            sMap[s[i]] = sMap.getOrDefault(s[i], 0) + 1
            sMap[t[i]] = sMap.getOrDefault(t[i], 0) - 1
        }
        for (c in sMap.keys) {
            if (sMap[c] != 0) {
                return false
            }
        }
        return true
    }
}

class ValidAnagramImpl : ValidAnagram {
    override fun invoke(s: String, t: String): Boolean {
        val alphabet = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in s.indices) {
            alphabet[s[i] - ALPHABET_FIRST_LETTER]++
        }
        for (i in t.indices) {
            alphabet[t[i] - ALPHABET_FIRST_LETTER]--
            if (alphabet[t[i] - ALPHABET_FIRST_LETTER] < 0) {
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
