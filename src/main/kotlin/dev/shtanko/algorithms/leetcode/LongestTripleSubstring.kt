/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2981. Find Longest Special Substring That Occurs Thrice I
 * @see <a href="https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/">Source</a>
 */
@Medium
sealed interface LongestTripleSubstring {
    operator fun invoke(str: String): Int

    data object OptimizedHashing : LongestTripleSubstring {
        override fun invoke(str: String): Int {
            // Create a map to store the count of all substrings
            val count = mutableMapOf<Pair<Char, Int>, Int>()
            var substringLength: Int

            for (start in str.indices) {
                val character = str[start]
                substringLength = 0

                for (end in start until str.length) {
                    // If the current character matches the initial character, increment the count
                    if (character == str[end]) {
                        substringLength++
                        val key = Pair(character, substringLength)
                        count[key] = count.getOrDefault(key, 0) + 1
                    } else {
                        break
                    }
                }
            }

            // Variable to store the longest substring length with frequency at least 3
            var ans = 0
            for ((key, value) in count) {
                val length = key.second
                if (value >= 3 && length > ans) {
                    ans = length
                }
            }

            return if (ans == 0) -1 else ans
        }
    }
}
