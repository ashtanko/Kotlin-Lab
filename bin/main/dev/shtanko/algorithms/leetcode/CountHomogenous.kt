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

import dev.shtanko.algorithms.MOD

/**
 * 1759. Count Number of Homogenous Substrings
 * @see <a href="https://leetcode.com/problems/count-number-of-homogenous-substrings">Source</a>
 */
fun interface CountHomogenous {
    operator fun invoke(str: String): Int
}

/**
 * Approach: Counting Streaks
 */
class CountHomogenousCountingStreaks : CountHomogenous {
    override fun invoke(str: String): Int {
        var ans = 0
        var currStreak = 0

        for (i in str.indices) {
            if (i == 0 || str[i] == str[i - 1]) {
                currStreak++
            } else {
                currStreak = 1
            }
            ans = (ans + currStreak) % MOD
        }

        return ans
    }
}
