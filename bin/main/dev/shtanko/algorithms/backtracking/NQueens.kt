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

package dev.shtanko.algorithms.backtracking

import dev.shtanko.algorithms.DOT
import dev.shtanko.algorithms.QUEEN

fun interface NQueens {
    operator fun invoke(n: Int): List<List<String>>
}

class NQueensSolution : NQueens {
    override fun invoke(n: Int): List<List<String>> {
        // The list to store the final result of N-Queens solutions
        val result = mutableListOf<List<String>>()

        // Initialize an empty chessboard filled with dots ('.')
        val board = Array(n) { CharArray(n) { DOT } }

        // Function to check if it's safe to place a queen at a given position
        fun isSafe(row: Int, col: Int): Boolean {
            // Check if there is a queen in the same column
            for (i in 0 until row) {
                if (board[i][col] == QUEEN) {
                    return false
                }
            }

            // Check if there is a queen in the left diagonal
            var i = row - 1
            var j = col - 1
            while (i >= 0 && j >= 0) {
                if (board[i][j] == QUEEN) {
                    return false
                }
                i--
                j--
            }

            // Check if there is a queen in the right diagonal
            i = row - 1
            j = col + 1
            while (i >= 0 && j < n) {
                if (board[i][j] == QUEEN) {
                    return false
                }
                i--
                j++
            }

            // If no conflicts are found, it's safe to place a queen at the given position
            return true
        }

        // Recursive backtracking function to find all solutions
        fun solve(row: Int) {
            // If all queens are placed, add the current board configuration to the result
            if (row == n) {
                result.add(board.map { it.joinToString("") })
                return
            }

            // Try placing a queen in each column of the current row
            for (col in 0 until n) {
                if (isSafe(row, col)) {
                    // Place the queen and move on to the next row
                    board[row][col] = QUEEN
                    solve(row + 1)
                    // Backtrack: remove the queen and try the next column
                    board[row][col] = DOT
                }
            }
        }

        // Start the recursive backtracking from the first row
        solve(0)

        // Return the final list of solutions
        return result
    }
}
