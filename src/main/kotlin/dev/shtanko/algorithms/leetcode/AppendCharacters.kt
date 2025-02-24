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

import dev.shtanko.algorithms.annotations.TwoPointers
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2486. Append Characters to String to Make Subsequence
 * @see <a href="https://leetcode.com/problems/append-characters-to-string-to-make-subsequence">Source</a>
 */
@Medium("https://leetcode.com/problems/append-characters-to-string-to-make-subsequence")
fun interface AppendCharacters {
    operator fun invoke(source: String, target: String): Int
}

@TwoPointers
class AppendCharactersTwoPointers : AppendCharacters {
    override fun invoke(source: String, target: String): Int {
        val targetPrefixLength = source.fold(0) { prefixLength, char ->
            if (prefixLength < target.length && char == target[prefixLength]) {
                prefixLength + 1
            } else {
                prefixLength
            }
        }
        return target.length - targetPrefixLength
    }
}
