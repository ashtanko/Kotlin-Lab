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
 * 3163. String Compression III
 * @see <a href="https://leetcode.com/problems/string-compression-iii/">Source</a>
 */
@Medium("https://leetcode.com/problems/string-compression-iii")
fun interface StringCompression3 {
    operator fun invoke(word: String): String
}

class StringCompression3StrManipulation : StringCompression3 {
    override fun invoke(word: String): String {
        val comp = StringBuilder()

        // pos tracks our position in the input string
        var pos = 0

        // Process until we reach end of string
        while (pos < word.length) {
            var consecutiveCount = 0

            val currentChar = word[pos]

            // Count consecutive occurrences (maximum 9)
            while (
                pos < word.length &&
                consecutiveCount < 9 &&
                word[pos] == currentChar
            ) {
                consecutiveCount++
                pos++
            }

            // Append count followed by character to result
            comp.append(consecutiveCount).append(currentChar)
        }

        return comp.toString()
    }
}
