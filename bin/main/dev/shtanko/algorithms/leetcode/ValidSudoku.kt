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

/**
 * 36. Valid Sudoku
 * @see <a href="https://leetcode.com/problems/valid-sudoku/">Source</a>
 */
fun interface ValidSudoku {
    operator fun invoke(board: Array<CharArray>): Boolean
}

class ValidSudokuSimple : ValidSudoku {
    override fun invoke(board: Array<CharArray>): Boolean {
        val seen: MutableSet<String> = HashSet()
        for (i in 0..8) {
            for (j in 0..8) {
                if (board[i][j] != '.') {
                    val b = "(" + board[i][j] + ")"
                    if (!seen.add(b + i) || !seen.add(j.toString() + b) || !seen.add((i / 3).toString() + b + j / 3)) {
                        return false
                    }
                }
            }
        }
        return true
    }
}
