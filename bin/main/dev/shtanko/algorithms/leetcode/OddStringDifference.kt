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

/**
 * 2451. Odd String Difference
 * @see <a href="https://leetcode.com/problems/odd-string-difference">Source</a>
 */
fun interface OddStringDifference {
    operator fun invoke(words: Array<String>): String
}

class OddStringDifferenceHashMap : OddStringDifference {
    override fun invoke(words: Array<String>): String {
        // [1] as stated in the problem, there will be two groups
        //    of words according to their difference array;
        //    we'll store them in a hashmap using the difference
        //    array (actually, its hash) as a key
        val eq: MutableMap<Int, MutableList<String>> = HashMap()

        for (w in words) {
            val diff: MutableList<Int> = ArrayList()
            for (i in 1 until w.length) {
                diff.add(w[i].code - w[i - 1].code)
            }
            // [2] in Java, we can use not the difference array itself,
            //    but rather its 'hashCode' as a hash/identifier
            //    of each group of words
            eq.computeIfAbsent(
                diff.hashCode(),
            ) { ArrayList() }.add(w)
        }

        // [3] as guaranteed in the problem, there will be 2
        //    groups of words, one of them of size 1
        for ((_, value) in eq) {
            if (value.size == 1) return value[0]
        }

        return words[0] // unreachable
    }
}
