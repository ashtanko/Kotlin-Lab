/*
 * Copyright 2020 Oleksii Shtanko
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
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 */
fun interface SpiralOrder {
    operator fun invoke(matrix: Array<IntArray>): List<Int>
}

/**
 * 54. Spiral Matrix
 * @see <a href="https://leetcode.com/problems/spiral-matrix/">Source</a>
 */
class SpiralOrderSolution : SpiralOrder {

    /**
     * This function returns the elements of the matrix in spiral order.
     * @param matrix The 2D array (matrix) to traverse.
     * @return The elements of the matrix in spiral order.
     */
    override fun invoke(matrix: Array<IntArray>): List<Int> {
        val res: MutableList<Int> = ArrayList()
        if (matrix.isEmpty()) {
            return res
        }

        var rowBegin = 0
        var rowEnd = matrix.size - 1
        var colBegin = 0
        var colEnd: Int = matrix[0].size - 1

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            traverseRight(matrix, rowBegin, colBegin, colEnd, res)
            rowBegin++

            traverseDown(matrix, colEnd, rowBegin, rowEnd, res)
            colEnd--

            if (rowBegin <= rowEnd) {
                traverseLeft(matrix, rowEnd, colBegin, colEnd, res)
            }
            rowEnd--

            if (colBegin <= colEnd) {
                traverseUp(matrix, colBegin, rowBegin, rowEnd, res)
            }
            colBegin++
        }

        return res
    }

    /**
     * This function traverses the matrix from left to right.
     * @param matrix The 2D array (matrix) to traverse.
     * @param row The row to traverse.
     * @param startCol The starting column.
     * @param endCol The ending column.
     * @param res The list to add the traversed elements to.
     */
    private fun traverseRight(matrix: Array<IntArray>, row: Int, startCol: Int, endCol: Int, res: MutableList<Int>) {
        for (j in startCol..endCol) {
            res.add(matrix[row][j])
        }
    }

    /**
     * This function traverses the matrix from top to bottom.
     * @param matrix The 2D array (matrix) to traverse.
     * @param col The column to traverse.
     * @param startRow The starting row.
     * @param endRow The ending row.
     * @param res The list to add the traversed elements to.
     */
    private fun traverseDown(matrix: Array<IntArray>, col: Int, startRow: Int, endRow: Int, res: MutableList<Int>) {
        for (j in startRow..endRow) {
            res.add(matrix[j][col])
        }
    }

    /**
     * This function traverses the matrix from right to left.
     * @param matrix The 2D array (matrix) to traverse.
     * @param row The row to traverse.
     * @param startCol The starting column.
     * @param endCol The ending column.
     * @param res The list to add the traversed elements to.
     */
    private fun traverseLeft(matrix: Array<IntArray>, row: Int, startCol: Int, endCol: Int, res: MutableList<Int>) {
        for (j in endCol downTo startCol) {
            res.add(matrix[row][j])
        }
    }

    /**
     * This function traverses the matrix from bottom to top.
     * @param matrix The 2D array (matrix) to traverse.
     * @param col The column to traverse.
     * @param startRow The starting row.
     * @param endRow The ending row.
     * @param res The list to add the traversed elements to.
     */
    private fun traverseUp(matrix: Array<IntArray>, col: Int, startRow: Int, endRow: Int, res: MutableList<Int>) {
        for (j in endRow downTo startRow) {
            res.add(matrix[j][col])
        }
    }
}
