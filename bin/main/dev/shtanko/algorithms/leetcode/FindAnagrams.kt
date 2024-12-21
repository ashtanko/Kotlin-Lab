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
 * 438. Find All Anagrams in a String
 * @see <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">Source</a>
 */
fun interface FindAnagrams {
    operator fun invoke(s: String, p: String): List<Int>
}

class FindAnagramsHashTable : FindAnagrams {
    override operator fun invoke(s: String, p: String): List<Int> {
        val list: MutableList<Int> = ArrayList()
        if (p.length > s.length) return list // Base Condition
        val n: Int = s.length // Array1 of s
        val m: Int = p.length // Array2 of p
        val count = freq(p) // initialize only 1 time
        val currentCount = freq(s.substring(0, m)) // freq function, update every-time according to sliding window
        // areSame function
        if (areSame(count, currentCount)) {
            list.add(0)
        }

        var i: Int = m
        while (i < n) {
            // going from 3 to 9 in above example
            currentCount[s[i - m] - 'a']-- // blue pointer, decrement frequency
            currentCount[s[i] - 'a']++ // red pointer, increment frequency
            if (areSame(count, currentCount)) { // now check, both array are same
                list.add(i - m + 1) // if we find similar add their index in our list
            }
            i++
        }
        return list
    }

    private fun areSame(x: IntArray, y: IntArray): Boolean {
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            // compare all the frequency & doesn't find any di-similar frequency return true otherwise false
            if (x[i] != y[i]) {
                return false
            }
        }
        return true
    }

    private fun freq(s: String): IntArray {
        val count = IntArray(ALPHABET_LETTERS_COUNT) // create array of size 26
        for (element in s) {
            count[element.code - 'a'.code]++ // update acc. to it's frequency
        }
        return count // and return count
    }
}
