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

/**
 * 885. Spiral Matrix III
 * @see <a href="https://leetcode.com/problems/spiral-matrix-iii">Source</a>
 */
fun interface SpiralMatrix3 {
    operator fun invoke(rows: Int, cols: Int, startRow: Int, startCol: Int): Array<IntArray>
}

class SpiralMatrix3Simulation : SpiralMatrix3 {
    override fun invoke(
        rows: Int,
        cols: Int,
        startRow: Int,
        startCol: Int,
    ): Array<IntArray> {
        var rStart = startRow
        var cStart = startCol
        val directions = arrayOf(
            intArrayOf(0, 1), // East
            intArrayOf(1, 0), // South
            intArrayOf(0, -1), // West
            intArrayOf(-1, 0), // North
        )
        val traversed = Array(rows * cols) { IntArray(2) }
        var idx = 0

        // Initial step size is 1, value of direction represents the current direction.
        var step = 1
        var direction = 0

        while (idx < rows * cols) {
            repeat(2) {
                repeat(step) {
                    // Validate the current position
                    if (rStart in 0 until rows && cStart in 0 until cols) {
                        traversed[idx] = intArrayOf(rStart, cStart)
                        idx++
                    }
                    // Make changes to the current position.
                    rStart += directions[direction][0]
                    cStart += directions[direction][1]
                }
                direction = (direction + 1) % 4
            }
            step++
        }

        return traversed
    }
}
