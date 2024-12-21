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

private const val ROOK = 'R'
private const val PAWN = 'p'
private const val BISHOP = 'B'
private const val BOARD_MAX_SIZE = 8

/**
 * 999. Available Captures for Rook
 * @see <a href="https://leetcode.com/problems/available-captures-for-rook/">Source</a>
 */
fun interface NumRookCaptures {
    operator fun invoke(board: Array<CharArray>): Int
}

/**
 * Straight Forward Solution
 * Time O(64)
 * Space O(1)
 */
class NumRookCapturesSF : NumRookCaptures {
    override operator fun invoke(board: Array<CharArray>): Int {
        var x0 = 0
        var y0 = 0
        var res = 0
        for (i in 0 until BOARD_MAX_SIZE) {
            for (j in 0 until BOARD_MAX_SIZE) {
                if (board[i][j] == ROOK) {
                    x0 = i
                    y0 = j
                }
            }
        }

        for (d in arrayOf(intArrayOf(1, 0), intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(0, -1))) {
            var x = x0 + d[0]
            var y = y0 + d[1]
            while (x in 0 until BOARD_MAX_SIZE && 0 <= y && y < BOARD_MAX_SIZE) {
                if (board[x][y] == PAWN) res++
                if (board[x][y] != '.') break
                x += d[0]
                y += d[1]
            }
        }
        return res
    }
}

/**
 * Runtime: O(n)
 * Memory: O(1)
 */
class NumRookCapturesSearch : NumRookCaptures {
    override operator fun invoke(board: Array<CharArray>): Int {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == ROOK) {
                    val twoSum = cap(board, i, j, 0, 1).plus(cap(board, i, j, 0, -1))
                    return twoSum.plus(cap(board, i, j, 1, 0).plus(cap(board, i, j, -1, 0)))
                }
            }
        }
        return 0
    }

    private fun cap(b: Array<CharArray>, x: Int, y: Int, dx: Int, dy: Int): Int {
        var m = x
        var n = y
        while (b.isXRange(m) && b.isYRange(m, n) && b.isNotBishop(m, n)) {
            if (b[m][n] == PAWN) return 1
            m += dx
            n += dy
        }
        return 0
    }

    private fun Array<CharArray>.isXRange(x: Int): Boolean {
        return x >= 0 && x < this.size
    }

    private fun Array<CharArray>.isYRange(x: Int, y: Int): Boolean {
        return y >= 0 && y < this[x].size
    }

    private fun Array<CharArray>.isNotBishop(x: Int, y: Int): Boolean {
        return this[x][y] != BISHOP
    }
}
