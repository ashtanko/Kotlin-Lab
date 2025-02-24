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
import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 2262. Total Appeal of A String
 * @see <a href="https://leetcode.com/problems/total-appeal-of-a-string/">Source</a>
 */
@Hard(link = "https://leetcode.com/problems/total-appeal-of-a-string")
fun interface AppealSum {
    /**
     * This function calculates the total appeal of a string.
     *
     * @param str The string to calculate the appeal of.
     * @return The total appeal of the string.
     */
    operator fun invoke(str: String): Long
}

/**
 * This class provides an implementation of the AppealSum interface using dynamic programming.
 * It keeps track of the current appeal and the previous occurrence of each character.
 * For each character in the string, it updates the current appeal and the previous occurrence of the character,
 * and adds the current appeal to the total appeal.
 */
@DP
class AppealSumDP : AppealSum {
    override operator fun invoke(str: String): Long {
        var res: Long = 0
        var cur: Long = 0
        val prev = LongArray(ALPHABET_LETTERS_COUNT)
        for (i in str.indices) {
            cur += i + 1 - prev[str[i] - 'a']
            prev[str[i] - 'a'] = (i + 1).toLong()
            res += cur
        }
        return res
    }
}

/**
 * This class provides an implementation of the AppealSum interface using Kotlin's mapIndexed function.
 * It calculates the appeal of each character in the string and sums them up to get the total appeal.
 */
@DP
class AppealSumDPKt : AppealSum {
    override operator fun invoke(str: String): Long {
        var cur: Long = 0
        val prev = LongArray(ALPHABET_LETTERS_COUNT)
        return str.mapIndexed { index, c ->
            cur += index + 1 - prev[c - 'a']
            prev[c - 'a'] = index.toLong() + 1
            cur
        }.sum()
    }
}
