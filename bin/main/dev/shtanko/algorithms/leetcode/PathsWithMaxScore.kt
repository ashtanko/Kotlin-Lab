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
 * 1301. Number of Paths with Max Score
 * @see <a href="https://leetcode.com/problems/number-of-paths-with-max-score/">Source</a>
 */
fun interface PathsWithMaxScore {
    operator fun invoke(board: List<String>): IntArray
}

class PathsWithMaxScoreDP : PathsWithMaxScore {
    private val dirs = arrayOf(intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(-1, -1))

    override operator fun invoke(board: List<String>): IntArray {
        val m: Int = board.size
        val n: Int = board[0].length
        val dpSum = Array(m) { IntArray(n) }
        val dpCnt = Array(m) { IntArray(n) }
        dpCnt[m - 1][n - 1] = 1 // start at the bottom right square

        for (r in m - 1 downTo 0) {
            for (c in n - 1 downTo 0) {
                if (dpCnt[r][c] == 0) {
                    continue
                }
                for (dir in dirs) {
                    val nr = r + dir[0]
                    val nc = c + dir[1]
                    if (nr >= 0 && nc >= 0 && board[nr][nc] != 'X') {
                        var nsum = dpSum[r][c]
                        if (board[nr][nc] != 'E') nsum += board[nr][nc] - '0'
                        if (nsum > dpSum[nr][nc]) {
                            dpCnt[nr][nc] = dpCnt[r][c]
                            dpSum[nr][nc] = nsum
                        } else if (nsum == dpSum[nr][nc]) {
                            dpCnt[nr][nc] = (dpCnt[nr][nc] + dpCnt[r][c]) % MOD
                        }
                    }
                }
            }
        }
        return intArrayOf(dpSum[0][0], dpCnt[0][0])
    }
}
