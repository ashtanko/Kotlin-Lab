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
 * 52. N-Queens II
 * @see <a href="https://leetcode.com/problems/n-queens-ii/">Source</a>
 */
fun interface TotalNQueensStrategy {
    operator fun invoke(n: Int): Int
}

/**
 * Approach: Backtracking
 * Time complexity: O(N!)
 * Space complexity: O(N)
 */
class TotalNQueensStraightForward : TotalNQueensStrategy {
    private val occupiedCols = hashSetOf<Int>()
    private val occupiedDiag1s = hashSetOf<Int>()
    private val occupiedDiag2s = hashSetOf<Int>()

    override operator fun invoke(n: Int): Int {
        return n.totalNQueensHelper(0, 0)
    }

    private fun Int.totalNQueensHelper(row: Int, c: Int): Int {
        var count = c
        for (col in 0 until this) {
            if (occupiedCols.contains(col)) {
                continue
            }
            val diag1 = row - col
            if (occupiedDiag1s.contains(diag1)) {
                continue
            }
            val diag2 = row + col
            if (occupiedDiag2s.contains(diag2)) {
                continue
            }
            // we can now place a queen here
            if (row == this - 1) {
                count++
            } else {
                occupiedCols.add(col)
                occupiedDiag1s.add(diag1)
                occupiedDiag2s.add(diag2)
                count = this.totalNQueensHelper(row + 1, count)
                // recover
                occupiedCols.remove(col)
                occupiedDiag1s.remove(diag1)
                occupiedDiag2s.remove(diag2)
            }
        }

        return count
    }
}

class TotalNQueensRecursive : TotalNQueensStrategy {
    private var count = 0

    override operator fun invoke(n: Int): Int {
        val cols = BooleanArray(n)
        val d1 = BooleanArray(2 * n)
        val d2 = BooleanArray(2 * n)
        n.backtracking(0, cols, d1, d2)
        return count
    }

    private fun Int.backtracking(row: Int, cols: BooleanArray, d1: BooleanArray, d2: BooleanArray) {
        if (row == this) count++
        for (col in 0 until this) {
            val id1 = col - row + this
            val id2 = col + row
            if (cols[col] || d1[id1] || d2[id2]) {
                continue
            }
            cols[col] = true
            d1[id1] = true
            d2[id2] = true
            this.backtracking(row + 1, cols, d1, d2)
            cols[col] = false
            d1[id1] = false
            d2[id2] = false
        }
    }
}
