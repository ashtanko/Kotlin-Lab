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

/**
 * 59. Spiral Matrix II
 * @see <a href="https://leetcode.com/problems/spiral-matrix-ii">Source</a>
 */
fun interface SpiralMatrix2 {
    fun generateMatrix(num: Int): Array<IntArray>
}

/**
 * Approach 1: Traverse Layer by Layer in Spiral Form
 */
class SpiralMatrix2Traverse : SpiralMatrix2 {
    override fun generateMatrix(num: Int): Array<IntArray> {
        val result = Array(num) { IntArray(num) }
        var cellValue = 1
        for (layer in 0 until (num + 1) / 2) {
            // direction 1 - traverse from left to right
            for (pointer in layer until num - layer) {
                result[layer][pointer] = cellValue++
            }
            // direction 2 - traverse from top to bottom
            for (pointer in layer + 1 until num - layer) {
                result[pointer][num - layer - 1] = cellValue++
            }
            // direction 3 - traverse from right to left
            for (pointer in layer + 1 until num - layer) {
                result[num - layer - 1][num - pointer - 1] = cellValue++
            }
            // direction 4 - traverse from bottom to top
            for (pointer in layer + 1 until num - layer - 1) {
                result[num - pointer - 1][layer] = cellValue++
            }
        }
        return result
    }
}

class SpiralMatrix2Optimized : SpiralMatrix2 {
    override fun generateMatrix(num: Int): Array<IntArray> {
        val result = Array(num) { IntArray(num) }
        var cellValue = 1
        val directions = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0),
        )
        var directionIndex = 0
        var row = 0
        var col = 0
        while (cellValue <= num * num) {
            result[row][col] = cellValue++
            val nextRow = Math.floorMod(row + directions[directionIndex][0], num)
            val nextColumn = Math.floorMod(col + directions[directionIndex][1], num)

            // change direction if next cell is non-zero
            if (result[nextRow][nextColumn] != 0) directionIndex = (directionIndex + 1) % 4
            row += directions[directionIndex][0]
            col += directions[directionIndex][1]
        }
        return result
    }
}
