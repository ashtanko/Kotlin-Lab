/*
 * Copyright 2024 Oleksii Shtanko
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
 * 2373. Largest Local Values in a Matrix
 * @see <a href="https://leetcode.com/problems/largest-local-values-in-a-matrix">Source</a>
 */
fun interface LargestLocalValuesInMatrix {
    operator fun invoke(grid: Array<IntArray>): Array<IntArray>
}

class LargestLocalValuesInMatrixSolution : LargestLocalValuesInMatrix {
    override fun invoke(grid: Array<IntArray>): Array<IntArray> {
        val n = grid.size
        val res = MutableList(n - 2) { MutableList(n - 2) { 0 } }
        for (i in 0 until n - 2) {
            for (j in 0 until n - 2) {
                for (ii in i until i + 3) {
                    for (jj in j until j + 3) {
                        res[i][j] = maxOf(res[i][j], grid[ii][jj])
                    }
                }
            }
        }
        return res.toTypedArray().map { it.toIntArray() }.toTypedArray()
    }
}
