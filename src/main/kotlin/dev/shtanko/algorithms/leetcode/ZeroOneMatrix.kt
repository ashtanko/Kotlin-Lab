/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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
import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

/**
 * 542. 01 Matrix
 * @see <a href="https://leetcode.com/problems/01-matrix/">Source</a>
 */
@Medium("https://leetcode.com/problems/01-matrix")
fun interface ZeroOneMatrix {
    operator fun invoke(matrix: Array<IntArray>): Array<IntArray>
}

/**
 * Approach 2: Using BFS
 */
class ZeroOneMatrixBFS : ZeroOneMatrix {
    private val directions = intArrayOf(0, 1, 0, -1, 0)

    override fun invoke(matrix: Array<IntArray>): Array<IntArray> {
        val queue: Queue<IntArray> = LinkedList()

        initializeQueue(matrix, queue)

        while (queue.isNotEmpty()) {
            processQueue(matrix, queue)
        }

        return matrix
    }

    private fun initializeQueue(matrix: Array<IntArray>, queue: Queue<IntArray>) {
        for (row in matrix.indices) {
            for (col in matrix[0].indices) {
                if (matrix[row][col] == 0) {
                    queue.offer(intArrayOf(row, col))
                } else {
                    matrix[row][col] = -1 // Marked as not processed yet!
                }
            }
        }
    }

    private fun processQueue(matrix: Array<IntArray>, queue: Queue<IntArray>) {
        val current = queue.poll()
        val row = current[0]
        val col = current[1]

        for (i in 0..3) {
            val newRow = row + directions[i]
            val newCol = col + directions[i + 1]

            val outOfBounds = newRow < 0 || newRow == matrix.size || newCol < 0 || newCol == matrix[0].size

            if (outOfBounds || matrix[newRow][newCol] != -1) {
                continue
            }

            matrix[newRow][newCol] = matrix[row][col] + 1
            queue.offer(intArrayOf(newRow, newCol))
        }
    }
}

/**
 * Approach 3: Dynamic Programming
 */
class ZeroOneMatrixDP : ZeroOneMatrix {

    override fun invoke(matrix: Array<IntArray>): Array<IntArray> {
        val rows: Int = matrix.size
        val cols: Int = matrix.firstOrNull()?.size ?: 0

        fillTopLeft(matrix, rows, cols)
        fillBottomRight(matrix, rows, cols)

        return matrix
    }

    private fun fillTopLeft(matrix: Array<IntArray>, rows: Int, cols: Int) {
        val inf = rows + cols

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (matrix[row][col] == 0) continue

                val top = if (row - 1 >= 0) matrix[row - 1][col] else inf
                val left = if (col - 1 >= 0) matrix[row][col - 1] else inf

                matrix[row][col] = min(top, left) + 1
            }
        }
    }

    private fun fillBottomRight(matrix: Array<IntArray>, rows: Int, cols: Int) {
        val inf = rows + cols

        for (row in rows - 1 downTo 0) {
            for (col in cols - 1 downTo 0) {
                if (matrix[row][col] == 0) continue

                val bottom = if (row + 1 < rows) matrix[row + 1][col] else inf
                val right = if (col + 1 < cols) matrix[row][col + 1] else inf

                matrix[row][col] = min(matrix[row][col], min(bottom, right) + 1)
            }
        }
    }
}
