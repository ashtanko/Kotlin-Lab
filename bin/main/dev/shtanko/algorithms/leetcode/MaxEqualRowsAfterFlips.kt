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
 * 1072. Flip Columns For Maximum Number of Equal Rows
 * @see <a href="https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/">Source</a>
 */
@Medium("https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/")
fun interface MaxEqualRowsAfterFlips {
    operator fun invoke(matrix: Array<IntArray>): Int
}

class MaxEqualRowsAfterFlipsHashMap : MaxEqualRowsAfterFlips {
    override fun invoke(matrix: Array<IntArray>): Int {
        val patternFrequency = mutableMapOf<String, Int>()

        for (currentRow in matrix) {
            // Convert row to pattern using map and joinToString
            // 'T' if element matches first element, 'F' otherwise
            val rowPattern = currentRow.joinToString("") {
                if (it == currentRow[0]) "T" else "F"
            }

            // Update pattern frequency using getOrDefault and put
            patternFrequency[rowPattern] = patternFrequency.getOrDefault(rowPattern, 0) + 1
        }

        // Return maximum frequency using values.maxOrNull() with fallback to 0
        return patternFrequency.values.maxOrNull() ?: 0
    }
}
