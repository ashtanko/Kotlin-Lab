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
 * 2825. Make String a Subsequence Using Cyclic Increments
 * @see <a href="https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/">Source</a>
 */
@Medium("https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/")
sealed interface CanMakeSubsequence {
    operator fun invoke(str1: String, str2: String): Boolean

    @dev.shtanko.algorithms.annotations.TwoPointers
    data object TwoPointers : CanMakeSubsequence {
        override fun invoke(str1: String, str2: String): Boolean {
            var str2Index = 0
            val lengthStr1 = str1.length
            val lengthStr2 = str2.length

            // Traverse through both strings using a for loop
            for (str1Index in 0 until lengthStr1) {
                if (
                    str2Index < lengthStr2 &&
                    (
                        str1[str1Index] == str2[str2Index] ||
                            str1[str1Index] + 1 == str2[str2Index] ||
                            str1[str1Index] - 25 == str2[str2Index]
                        )
                ) {
                    // If a match is found, move to the next character in str2
                    str2Index++
                }
            }
            // Check if all characters in str2 were matched
            return str2Index == lengthStr2
        }
    }
}
