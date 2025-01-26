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

package dev.shtanko.algorithms.leetcode

/**
 * 2482. Difference Between Ones and Zeros in Row and Column
 * @see <a href="<link>">Source</a>
 */
fun interface OnesMinusZeros {
    operator fun invoke(grid: Array<IntArray>): Array<IntArray>
}

class OnesMinusZerosArrayCounter : OnesMinusZeros {
    override fun invoke(grid: Array<IntArray>): Array<IntArray> {
        val m: Int = grid.size
        val n: Int = grid[0].size

        val onesRow = IntArray(m) { 0 }
        val onesCol = IntArray(n) { 0 }

        for (i in 0 until m) {
            for (j in 0 until n) {
                onesRow[i] += grid[i][j]
                onesCol[j] += grid[i][j]
            }
        }

        val diff = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                diff[i][j] = 2 * onesRow[i] + 2 * onesCol[j] - n - m
            }
        }

        return diff
    }
}
