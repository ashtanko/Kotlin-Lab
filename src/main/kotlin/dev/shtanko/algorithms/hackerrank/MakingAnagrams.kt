/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.hackerrank

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-making-anagrams/problem">Making Anagrams</a>
 *
 * The idea is to count the frequency of each character in the strings and then calculate the absolute
 * difference of the frequencies.
 *
 * The algorithm uses an array of integers to store the frequency of each character in the first string.
 *
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
data object MakingAnagrams {

    /**
     * @param a the first string
     * @param b the second string
     * @return the minimum number of characters to delete to make the strings anagrams
     */
    operator fun invoke(a: String, b: String): Int {
        val table = IntArray(ALPHABET_LETTERS_COUNT)
        a.lowercase().forEach {
            table[it - 'a']++
        }
        b.lowercase().forEach {
            table[it - 'a']--
        }
        return table.sumOf { kotlin.math.abs(it) }
    }
}
