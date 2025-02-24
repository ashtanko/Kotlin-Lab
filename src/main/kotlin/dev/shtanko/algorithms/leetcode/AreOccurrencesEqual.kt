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
import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1941. Check if All Characters Have Equal Number of Occurrences
 * @see <a href="https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/">Source</a>
 */
@Easy("https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences")
fun interface AreOccurrencesEqual {
    operator fun invoke(str: String): Boolean
}

@Iterative
class AreOccurrencesEqualKotlin : AreOccurrencesEqual {
    override operator fun invoke(str: String): Boolean {
        val num = str.count { it == str[0] }
        for (i in 1 until str.length) {
            if (num != str.count { it == str[i] }) return false
        }
        return true
    }
}

class AreOccurrencesEqualBF : AreOccurrencesEqual {
    override operator fun invoke(str: String): Boolean {
        val fr = IntArray(ALPHABET_LETTERS_COUNT)
        str.chars().forEach { c -> fr[c - 'a'.code]++ }
        return fr.filter { f ->
            f > 0
        }.all { f ->
            f == fr.max()
        }
    }
}
