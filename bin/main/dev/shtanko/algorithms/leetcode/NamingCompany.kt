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
 * 2306. Naming a Company
 * @see <a href="https://leetcode.com/problems/naming-a-company/">Source</a>
 */
fun interface NamingCompany {
    fun distinctNames(ideas: Array<String>): Long
}

class NamingCompanyCountPairs : NamingCompany {
    override fun distinctNames(ideas: Array<String>): Long {
        var res: Long = 0
        val cnt = Array(ALPHABET_LETTERS_COUNT) { LongArray(ALPHABET_LETTERS_COUNT) }
        val s: Array<MutableSet<String>> = Array(ALPHABET_LETTERS_COUNT) { mutableSetOf() }
        for (idea in ideas) {
            s[idea[0] - 'a'].add(idea.substring(1))
        }
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            for (suff in s[i]) {
                for (j in 0 until ALPHABET_LETTERS_COUNT) {
                    cnt[i][j] += (if (s[j].contains(suff)) 0 else 1).toLong()
                }
            }
        }
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            for (j in 0 until ALPHABET_LETTERS_COUNT) {
                res += cnt[i][j] * cnt[j][i]
            }
        }
        return res
    }
}
