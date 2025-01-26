/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.MOD

/**
 * 576. Out of Boundary Paths
 * @see <a href="https://leetcode.com/problems/out-of-boundary-paths/">Source</a>
 */
fun interface OutOfBoundaryPaths {
    operator fun invoke(rowCount: Int, columnCount: Int, maxMove: Int, startRow: Int, startColumn: Int): Int
}

/**
 * Approach 1: Brute Force
 */
class OutOfBoundaryPathsBruteForce : OutOfBoundaryPaths {
    override fun invoke(rowCount: Int, columnCount: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
        if (startRow == rowCount || startColumn == columnCount || startRow < 0 || startColumn < 0) return 1
        if (maxMove == 0) return 0
        return invoke(rowCount, columnCount, maxMove - 1, startRow - 1, startColumn) +
            invoke(rowCount, columnCount, maxMove - 1, startRow + 1, startColumn) +
            invoke(rowCount, columnCount, maxMove - 1, startRow, startColumn - 1) +
            invoke(rowCount, columnCount, maxMove - 1, startRow, startColumn + 1)
    }
}

/**
 * Approach 2: Recursion with Memoization
 */
class OutOfBoundaryPathsMemo : OutOfBoundaryPaths {
    override fun invoke(rowCount: Int, columnCount: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
        val memo = Array(rowCount) {
            Array(columnCount) {
                IntArray(maxMove + 1) { -1 }
            }
        }
        return findPaths(rowCount, columnCount, maxMove, startRow, startColumn, memo)
    }

    private fun findPaths(
        rowCount: Int,
        columnCount: Int,
        maxMove: Int,
        currentRow: Int,
        currentColumn: Int,
        memo: Array<Array<IntArray>>,
    ): Int {
        if (currentRow == rowCount || currentColumn == columnCount || currentRow < 0 || currentColumn < 0) return 1
        if (maxMove == 0) return 0
        if (memo[currentRow][currentColumn][maxMove] >= 0) {
            return memo[currentRow][currentColumn][maxMove]
        }

        val moveRight = findPaths(rowCount, columnCount, maxMove - 1, currentRow, currentColumn + 1, memo)
        val moveLeft = findPaths(rowCount, columnCount, maxMove - 1, currentRow, currentColumn - 1, memo)
        val moveDown = findPaths(rowCount, columnCount, maxMove - 1, currentRow + 1, currentColumn, memo)
        val moveUp = findPaths(rowCount, columnCount, maxMove - 1, currentRow - 1, currentColumn, memo)

        memo[currentRow][currentColumn][maxMove] = (moveRight + moveLeft + moveDown + moveUp) % MOD
        return memo[currentRow][currentColumn][maxMove]
    }
}

class OutOfBoundaryPathsDP : OutOfBoundaryPaths {
    override fun invoke(rowCount: Int, columnCount: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
        var currentDp = Array(rowCount) { IntArray(columnCount) }
        currentDp[startRow][startColumn] = 1
        var totalCount = 0

        for (moves in 1..maxMove) {
            val nextDp = Array(rowCount) { IntArray(columnCount) }

            for (rowIndex in 0 until rowCount) {
                for (columnIndex in 0 until columnCount) {
                    if (rowIndex == rowCount - 1) {
                        totalCount = (totalCount + currentDp[rowIndex][columnIndex]) % MOD
                    }
                    if (columnIndex == columnCount - 1) {
                        totalCount = (totalCount + currentDp[rowIndex][columnIndex]) % MOD
                    }
                    if (rowIndex == 0) {
                        totalCount = (totalCount + currentDp[rowIndex][columnIndex]) % MOD
                    }
                    if (columnIndex == 0) {
                        totalCount = (totalCount + currentDp[rowIndex][columnIndex]) % MOD
                    }
                    val left = if (rowIndex > 0) currentDp[rowIndex - 1][columnIndex] else 0
                    val right = if (rowIndex < rowCount - 1) currentDp[rowIndex + 1][columnIndex] else 0
                    val up = if (columnIndex > 0) currentDp[rowIndex][columnIndex - 1] else 0
                    val down = if (columnIndex < columnCount - 1) currentDp[rowIndex][columnIndex + 1] else 0
                    nextDp[rowIndex][columnIndex] = ((left + right) % MOD + (up + down) % MOD) % MOD
                }
            }
            currentDp = nextDp
        }
        return totalCount
    }
}
