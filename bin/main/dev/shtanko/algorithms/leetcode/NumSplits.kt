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
 * 1525. Number of Good Ways to Split a String
 * @see <a href="https://leetcode.com/problems/number-of-good-ways-to-split-a-string/">Source</a>
 */
fun interface NumSplits {
    operator fun invoke(str: String): Int
}

class NumSplitsSplitPointer : NumSplits {
    override operator fun invoke(str: String): Int {
        val l = IntArray(ALPHABET_LETTERS_COUNT)
        val r = IntArray(ALPHABET_LETTERS_COUNT)
        var dL = 0
        var dR = 0
        var res = 0
        val s = str.toCharArray()
        for (ch in s) dR += if (++r[ch.code - 'a'.code] == 1) 1 else 0
        for (i in s.indices) {
            dL += if (++l[s[i] - 'a'] == 1) 1 else 0
            dR -= if (--r[s[i] - 'a'] == 0) 1 else 0
            res += if (dL == dR) 1 else 0
        }
        return res
    }
}

class NumSplitsMap : NumSplits {
    override operator fun invoke(str: String): Int {
        val left: HashMap<Char, Int> = HashMap()
        val right: HashMap<Char, Int> = HashMap()

        // put all the characters in the right hashmap and keep track of the count
        for (i in str.indices) {
            right[str[i]] = right.getOrDefault(str[i], 0) + 1
        }

        // count the number of times the right is equal to the left, which is the result
        var count = 0

        // loop through all the characters and add them to the left and remove them from the right
        for (element in str) {
            val curr: Char = element
            left[curr] = left.getOrDefault(curr, 0) + 1

            // I put a getOrDefault() here instead of just get() as a "just in case"
            // but the "right" should have all the characters
            // since we added all characters at the beginning
            // adding an if statement increases runtime but getOrDefault() did not
            right[curr] = right.getOrDefault(curr, 0) - 1

            // if the count is equal to zero then remove the key/value pair
            if (right[curr]!! <= 0) {
                right.remove(curr)
            }

            // if the two hashmaps are equal in size,
            // which means they have the same amount of keys,
            // which means they have the same amount of distinct characters
            // increment the count for the result
            if (left.size == right.size) {
                count++
            }
        }

        return count
    }
}
