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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2938. Separate Black and White Balls
 * @see <a href="https://leetcode.com/problems/separate-black-and-white-balls/">Source</a>
 */
@Medium("https://leetcode.com/problems/separate-black-and-white-balls")
fun interface SeparateBlackAndWhiteBalls {
    operator fun invoke(str: String): Long
}

class SeparateBlackAndWhiteBallsTwoPointer : SeparateBlackAndWhiteBalls {
    override fun invoke(str: String): Long {
        var whitePosition = 0
        var totalSwaps = 0L

        for (currentPos in str.indices) {
            if (str[currentPos] == '0') {
                totalSwaps += (currentPos - whitePosition)
                whitePosition++
            }
        }

        return totalSwaps
    }
}

class SeparateBlackAndWhiteBallsCounter : SeparateBlackAndWhiteBalls {
    override fun invoke(str: String): Long {
        var totalSwaps = 0L
        var blackBallCount = 0

        // Iterate through each ball (character) in the string
        for (char in str) {
            if (char == '0') {
                // Swap with all black balls to its left
                totalSwaps += blackBallCount
            } else {
                // Increment the count of black balls
                blackBallCount++
            }
        }

        return totalSwaps
    }
}
