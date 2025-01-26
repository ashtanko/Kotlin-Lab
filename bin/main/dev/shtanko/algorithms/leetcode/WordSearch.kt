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

import dev.shtanko.algorithms.HALF_OF_BYTE

/**
 * 79. Word Search
 * @see <a href="https://leetcode.com/problems/word-search/">Source</a>
 */
fun interface WordSearch {
    operator fun invoke(board: Array<CharArray>, word: String): Boolean
}

class WordSearchShort : WordSearch {
    override fun invoke(board: Array<CharArray>, word: String): Boolean {
        val chars = word.toCharArray()
        for (y in board.indices) {
            for (x in 0 until board[y].size) {
                if (exist(board, y, x, chars, 0)) {
                    return true
                }
            }
        }
        return false
    }

    private fun exist(board: Array<CharArray>, y: Int, x: Int, word: CharArray, i: Int): Boolean {
        if (i == word.size) {
            return true
        }
        if (y < 0 || x < 0 || y == board.size || x == board[y].size) {
            return false
        }
        if (board[y][x] != word[i]) {
            return false
        }
        board[y][x] = (board[y][x].code xor HALF_OF_BYTE).toChar()
        val exist = exist(board, y, x + 1, word, i + 1) || exist(board, y, x - 1, word, i + 1) || exist(
            board,
            y + 1,
            x,
            word,
            i + 1,
        ) || exist(board, y - 1, x, word, i + 1)
        board[y][x] = (board[y][x].code xor HALF_OF_BYTE).toChar()
        return exist
    }
}
