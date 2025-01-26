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
 * 1582. Special Positions in a Binary Matrix
 * @see <a href="https://leetcode.com/problems/special-positions-in-a-binary-matrix">Source</a>
 */
fun interface SpecialPositionsInBinaryMatrix {
    operator fun invoke(mat: Array<IntArray>): Int
}

class SpecialPositionsInBinaryMatrixBF : SpecialPositionsInBinaryMatrix {
    override fun invoke(mat: Array<IntArray>): Int {
        var ans = 0
        val m: Int = mat.size
        val n: Int = mat[0].size

        for (row in 0 until m) {
            for (col in 0 until n) {
                if (mat[row][col] == 1 && isSpecialPosition(mat, row, col, m, n)) {
                    ans++
                }
            }
        }

        return ans
    }

    private fun isSpecialPosition(mat: Array<IntArray>, row: Int, col: Int, m: Int, n: Int): Boolean {
        for (r in 0 until m) {
            if (r != row && mat[r][col] == 1) {
                return false
            }
        }

        for (c in 0 until n) {
            if (c != col && mat[row][c] == 1) {
                return false
            }
        }

        return true
    }
}

class SpecialPositionsInBinaryMatrixPrecompute : SpecialPositionsInBinaryMatrix {
    override fun invoke(mat: Array<IntArray>): Int {
        val rowCount = computeRowCount(mat)
        val colCount = computeColCount(mat)

        return countSpecialPositions(mat, rowCount, colCount)
    }

    private fun computeRowCount(mat: Array<IntArray>): IntArray {
        val m = mat.size
        val rowCount = IntArray(m)

        for (row in 0 until m) {
            for (col in mat[row].indices) {
                if (mat[row][col] == 1) {
                    rowCount[row]++
                }
            }
        }

        return rowCount
    }

    private fun computeColCount(mat: Array<IntArray>): IntArray {
        val n = mat[0].size
        val colCount = IntArray(n)

        for (row in mat.indices) {
            for (col in 0 until n) {
                if (mat[row][col] == 1) {
                    colCount[col]++
                }
            }
        }

        return colCount
    }

    private fun countSpecialPositions(mat: Array<IntArray>, rowCount: IntArray, colCount: IntArray): Int {
        var ans = 0

        for (row in mat.indices) {
            for (col in mat[row].indices) {
                if (mat[row][col] == 1 && rowCount[row] == 1 && colCount[col] == 1) {
                    ans++
                }
            }
        }

        return ans
    }
}
