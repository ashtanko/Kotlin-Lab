/*
 * Copyright 2021 Oleksii Shtanko
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
 * Verifying an Alien Dictionary
 * @see <a href="https://leetcode.com/problems/verifying-an-alien-dictionary/">Source</a>
 */
fun interface VerifyingAlienDictionary {
    operator fun invoke(words: Array<String>, order: String): Boolean
}

class VerifyingAlienDictionaryCompare : VerifyingAlienDictionary {
    override fun invoke(words: Array<String>, order: String): Boolean {
        val orderMap = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in order.indices) {
            orderMap[order[i] - 'a'] = i
        }

        for (i in 0 until words.size - 1) {
            for (j in 0 until words[i].length) {
                // If we do not find a mismatch letter between words[i] and words[i + 1],
                // we need to examine the case when words are like ("apple", "app").
                if (j >= words[i + 1].length) return false
                if (words[i][j] != words[i + 1][j]) {
                    val currentWordChar: Int = words[i][j] - 'a'
                    val nextWordChar: Int = words[i + 1][j] - 'a'
                    return if (orderMap[currentWordChar] > orderMap[nextWordChar]) {
                        false
                    } else {
                        break
                    }
                }
            }
        }
        return true
    }
}
