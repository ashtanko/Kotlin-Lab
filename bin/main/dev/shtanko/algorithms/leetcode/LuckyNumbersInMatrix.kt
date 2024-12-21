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
 * 1380. Lucky Numbers in a Matrix
 * @see <a href="https://leetcode.com/problems/lucky-numbers-in-a-matrix">Source</a>
 */
fun interface AbstractLuckyNumbers {
    operator fun invoke(matrix: Array<IntArray>): List<Int>
}

class LuckyNumbers : AbstractLuckyNumbers {
    override operator fun invoke(matrix: Array<IntArray>): List<Int> {
        val rowCount = matrix.size
        val colCount = matrix[0].size
        val minRowValues = IntArray(rowCount) { Integer.MAX_VALUE }
        val maxColValues = IntArray(colCount)
        for (rowIndex in 0 until rowCount) {
            for (colIndex in 0 until colCount) {
                minRowValues[rowIndex] = matrix[rowIndex][colIndex].coerceAtMost(minRowValues[rowIndex])
                maxColValues[colIndex] = matrix[rowIndex][colIndex].coerceAtLeast(maxColValues[colIndex])
            }
        }
        val luckyNumbers: MutableList<Int> = ArrayList()
        for (rowIndex in 0 until rowCount) {
            for (colIndex in 0 until colCount) {
                if (minRowValues[rowIndex] == maxColValues[colIndex]) {
                    luckyNumbers.add(minRowValues[rowIndex])
                }
            }
        }
        return luckyNumbers
    }
}

class LuckyNumbersSet : AbstractLuckyNumbers {
    override operator fun invoke(matrix: Array<IntArray>): List<Int> {
        val minimumValuesInRows: MutableSet<Int> = HashSet()
        val luckyNumbers: MutableSet<Int> = HashSet()

        for (row in matrix) {
            var minimumInRow = row[0]
            for (cell in row) {
                minimumInRow = minimumInRow.coerceAtMost(cell)
            }
            minimumValuesInRows.add(minimumInRow)
        }

        for (columnIndex in matrix[0].indices) {
            var maximumInColumn = matrix[0][columnIndex]
            for (rowIndex in matrix.indices) {
                maximumInColumn = matrix[rowIndex][columnIndex].coerceAtLeast(maximumInColumn)
            }
            if (minimumValuesInRows.contains(maximumInColumn)) {
                luckyNumbers.add(maximumInColumn)
            }
        }
        return luckyNumbers.toList()
    }
}
