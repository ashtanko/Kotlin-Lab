/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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
 * 6. Zigzag Conversion
 * @see <a href="https://leetcode.com/problems/zigzag-conversion/">Source</a>
 */
@Medium("https://leetcode.com/problems/zigzag-conversion")
fun interface ZigzagConversion {
    operator fun invoke(input: String, numRows: Int): String
}

class ZigzagConversionSB : ZigzagConversion {
    override fun invoke(input: String, numberOfRows: Int): String {
        if (numberOfRows <= 1) return input

        val rowBuilders = Array(numberOfRows) { StringBuilder() }
        var currentRow = 0
        var direction = 1

        for (char in input) {
            rowBuilders[currentRow].append(char)
            if (currentRow == 0) {
                direction = 1
            }
            if (currentRow == numberOfRows - 1) {
                direction = -1
            }
            currentRow += direction
        }

        return rowBuilders.joinToString("") { it.toString() }
    }
}
