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
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * @see <a href="https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique">Source</a>
 */
fun interface MinDeletions {
    operator fun invoke(str: String): Int
}

class MinDeletionsGreedy : MinDeletions {
    override fun invoke(str: String): Int {
        val cnt = IntArray(ALPHABET_LETTERS_COUNT)
        var res = 0
        val used: MutableSet<Int> = HashSet()
        for (element in str) {
            ++cnt[element - 'a']
        }
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            while (cnt[i] > 0 && !used.add(cnt[i])) {
                --cnt[i]
                ++res
            }
        }
        return res
    }
}
