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
 * 2109. Adding Spaces to a String
 * @see <a href="https://leetcode.com/problems/adding-spaces-to-a-string/">Adding Spaces to a String</a>
 */
@Medium("https://leetcode.com/problems/adding-spaces-to-a-string/")
sealed interface AddingSpaces {
    operator fun invoke(s: String, spaces: IntArray): String

    data object StringBuilder : AddingSpaces {
        override fun invoke(s: String, spaces: IntArray): String {
            val result = StringBuilder()
            var spaceIndex = 0

            for (stringIndex in s.indices) {
                if (spaceIndex < spaces.size && stringIndex == spaces[spaceIndex]) {
                    result.append(' ')
                    spaceIndex++
                }
                result.append(s[stringIndex])
            }

            return result.toString()
        }
    }

    @dev.shtanko.algorithms.annotations.TwoPointers
    data object TwoPointers : AddingSpaces {
        override fun invoke(s: String, spaces: IntArray): String {
            val n = s.length
            val sb = StringBuilder()
            var i = 0
            for (space in spaces) {
                sb.append(s.substring(i, space))
                sb.append(' ')
                i = space
            }
            sb.append(s.substring(i, n))
            return sb.toString()
        }
    }
}
