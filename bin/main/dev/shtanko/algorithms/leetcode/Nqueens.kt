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
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens
 * attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space respectively.
 */
fun Int.solveNQueens(): List<List<String>> {
    val chess = Array(this) { CharArray(this) }
    for (i in 0 until this) {
        for (j in 0 until this) {
            chess[i][j] = '.'
        }
    }
    val res: MutableList<List<String>> = ArrayList()
    solve(res, chess, 0)
    return res
}

@Throws(IllegalStateException::class)
fun Array<out Pair<Int, Char>>.assertLocations(size: Int, abc: CharArray) {
    if (this.isEmpty()) return
    if (!this.map { it.first }.none { it > size }) {
        throw IllegalStateException("Column cannot be more that $size x $size board")
    }
    if (this.map { it.second }.none { abc.contains(it) }) {
        throw IllegalStateException(
            "Wrong row - should be one of this: ${abc.toList().joinToString()}",
        )
    }
}

private const val BOARD_MAX_SIZE = 8

/**
 * Max 8x8.
 */
fun Int.genBoard(vararg locations: Pair<Int, Char>): String {
    if (this > BOARD_MAX_SIZE) throw IllegalStateException("Board size cannot be more than 8x8")
    val abc = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
    val empty = "|_|"
    val filled = "|#|"
    val unique = locations.toSet().toTypedArray()
    unique.assertLocations(this, abc)

    val trueLocations = unique.map {
        it.first - 1 to abc.indexOf(it.second.lowercaseChar())
    }

    val board = Array(this) { IntArray(this) { 0 } }
    for (loc in trueLocations) {
        board.reversedArray()[loc.first][loc.second] = 1
    }
    val sb = StringBuilder()
    for (i in board.reversedArray().indices) {
        sb.append("${this - i} ")
        for (b in board[i]) {
            if (b == 0) sb.append(empty) else sb.append(filled)
        }
        sb.append("\n")
    }
    for (j in 0 until this) {
        sb.append("  ${abc[j]}")
    }
    sb.append("\n")
    return sb.toString()
}

private fun solve(
    res: MutableList<List<String>>,
    chess: Array<CharArray>,
    row: Int,
) {
    if (row == chess.size) {
        res.add(construct(chess))
        return
    }
    for (col in chess.indices) {
        if (valid(chess, row, col)) {
            chess[row][col] = 'Q'
            solve(res, chess, row + 1)
            chess[row][col] = '.'
        }
    }
}

private fun valid(chess: Array<CharArray>, row: Int, col: Int): Boolean {
    for (i in 0 until row) {
        if (chess[i][col] == 'Q') {
            return false
        }
    }

    run {
        var i = row - 1
        var j = col + 1
        while (i >= 0 && j < chess.size) {
            if (chess[i][j] == 'Q') {
                return false
            }
            i--
            j++
        }
    }

    var i = row - 1
    var j = col - 1
    while (i >= 0 && j >= 0) {
        if (chess[i][j] == 'Q') {
            return false
        }
        i--
        j--
    }
    return true
}

private fun construct(chess: Array<CharArray>): List<String> {
    val path: MutableList<String> = ArrayList()
    for (chars in chess) {
        path.add(String(chars))
    }
    return path
}
