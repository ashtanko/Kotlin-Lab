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
 * 1861. Rotating the Box
 * @see <a href="https://leetcode.com/problems/rotating-the-box/">Rotating the Box</a>
 */
@Medium("https://leetcode.com/problems/rotating-the-box/")
fun interface RotatingTheBox {
    operator fun invoke(box: Array<CharArray>): Array<CharArray>
}

class RotatingTheBoxCombineRotation : RotatingTheBox {
    override fun invoke(box: Array<CharArray>): Array<CharArray> {
        val m = box.size
        val n = box[0].size
        val result = Array(n) { CharArray(m) { '.' } }

        // Apply gravity to let stones fall to the lowest possible empty cell in each column
        for (i in 0 until m) {
            var lowestRowWithEmptyCell = n - 1
            // Process each cell in row `i` in reversed order
            for (j in n - 1 downTo 0) {
                when (box[i][j]) {
                    '#' -> {
                        // Place the stone in the correct position in the rotated grid
                        result[lowestRowWithEmptyCell][m - i - 1] = '#'
                        lowestRowWithEmptyCell--
                    }

                    '*' -> {
                        // Place the obstacle in the correct position in the rotated grid
                        result[j][m - i - 1] = '*'
                        lowestRowWithEmptyCell = j - 1
                    }
                }
            }
        }

        return result
    }
}
