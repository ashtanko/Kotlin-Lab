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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * 387. First Unique Character in a String
 * @see <a href="https://leetcode.com/problems/first-unique-character-in-a-string">Source</a>
 */
fun interface FirstUniqChar {
    operator fun invoke(str: String): Int
}

class FirstUniqCharArray : FirstUniqChar {
    override fun invoke(str: String): Int {
        val count = IntArray(ALPHABET_LETTERS_COUNT)
        val n: Int = str.length

        // build char count bucket : <charIndex, count>
        for (i in 0 until n) {
            val index: Int = str[i] - 'a'
            count[index]++
        }

        // find the index
        for (i in 0 until n) {
            val index: Int = str[i] - 'a'
            if (count[index] == 1) {
                return i
            }
        }
        return -1
    }
}
