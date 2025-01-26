/*
 * Copyright 2021 Oleksii Shtanko
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

import kotlin.math.abs

/**
 * Design Tic-Tac-Toe
 * @see <a href="https://leetcode.com/problems/design-tic-tac-toe/">Source</a>
 */
fun interface DesignTicTacToe {
    fun move(row: Int, col: Int, player: Int): Int
}

/**
 * Time Complexity: O(n)
 * Space Complexity: O(n^2)
 */
class TTTOptimizedBruteForce(val n: Int) : DesignTicTacToe {

    private val board: Array<IntArray> = Array(n) { IntArray(n) }

    override fun move(row: Int, col: Int, player: Int): Int {
        board[row][col] = player
        // check if the player wins
        val rowOrColumn = checkRow(row, player) || checkColumn(col, player) || row == col && checkDiagonal(player)
        if (rowOrColumn || col == n - row - 1 && checkAntiDiagonal(player)) {
            return player
        }
        // No one wins
        return 0
    }

    private fun checkDiagonal(player: Int): Boolean {
        for (row in 0 until n) {
            if (board[row][row] != player) {
                return false
            }
        }
        return true
    }

    private fun checkAntiDiagonal(player: Int): Boolean {
        for (row in 0 until n) {
            if (board[row][n - row - 1] != player) {
                return false
            }
        }
        return true
    }

    private fun checkColumn(col: Int, player: Int): Boolean {
        for (row in 0 until n) {
            if (board[row][col] != player) {
                return false
            }
        }
        return true
    }

    private fun checkRow(row: Int, player: Int): Boolean {
        for (col in 0 until n) {
            if (board[row][col] != player) {
                return false
            }
        }
        return true
    }
}

/**
 * Approach 2: Optimised Approach
 * Time Complexity: O(1)
 * Space Complexity: O(n)
 */
class TTTOptimised(val n: Int) : DesignTicTacToe {

    private var rows: IntArray = IntArray(n)
    private var cols: IntArray = IntArray(n)
    private var diagonal = 0
    private var antiDiagonal = 0

    override fun move(row: Int, col: Int, player: Int): Int {
        val currentPlayer = if (player == 1) 1 else -1
        // update currentPlayer in rows and cols arrays
        rows[row] += currentPlayer
        cols[col] += currentPlayer
        // update diagonal
        if (row == col) {
            diagonal += currentPlayer
        }
        // update anti diagonal
        if (col == cols.size - row - 1) {
            antiDiagonal += currentPlayer
        }
        val n: Int = rows.size
        // check if the current player wins
        val rowsOrCols = abs(rows[row]) == n || abs(cols[col]) == n
        return if (rowsOrCols || abs(diagonal) == n || abs(antiDiagonal) == n) {
            player
        } else {
            0
        }
        // No one wins
    }
}
