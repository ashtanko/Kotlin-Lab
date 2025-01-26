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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 880. Decoded String at Index
 * @see <a href="https://leetcode.com/problems/decoded-string-at-index">Source</a>
 */
@Medium("https://leetcode.com/problems/decoded-string-at-index")
fun interface DecodedStringAtIndex {
    operator fun invoke(str: String, k: Int): String
}

class DecodedStringAtIndexSolution : DecodedStringAtIndex {
    override fun invoke(str: String, k: Int): String {
        var currentLength = 0
        var targetIndex = k

        var position = 0
        while (currentLength < targetIndex) {
            currentLength = if (Character.isDigit(str[position])) {
                currentLength * (str[position] - '0')
            } else {
                currentLength + 1
            }
            position++
        }
        position--
        while (position > 0) {
            if (Character.isDigit(str[position])) {
                currentLength /= str[position] - '0'
                targetIndex %= currentLength
            } else {
                if (targetIndex % currentLength == 0) {
                    break
                }
                currentLength--
            }
            position--
        }
        return str[position].toString()
    }
}
