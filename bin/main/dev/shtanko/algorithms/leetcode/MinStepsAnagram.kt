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
import kotlin.math.max

/**
 * 1347. Minimum Number of Steps to Make Two Strings Anagram
 * @see <a href="https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram">Source</a>
 */
fun interface MinStepsAnagram {
    operator fun invoke(str: String, target: String): Int
}

class MinStepsAnagramMap : MinStepsAnagram {
    override fun invoke(str: String, target: String): Int {
        val count = IntArray(ALPHABET_LETTERS_COUNT)

        // Storing the difference of frequencies of characters in t and s.
        for (i in str.indices) {
            count[target[i] - 'a']++
            count[str[i] - 'a']--
        }

        var ans = 0

        // Adding the difference where string t has more instances than s.
        // Ignoring where t has fewer instances as they are redundant and
        // can be covered by the first case.
        for (i in 0..<ALPHABET_LETTERS_COUNT) {
            ans += max(0, count[i])
        }

        return ans
    }
}
