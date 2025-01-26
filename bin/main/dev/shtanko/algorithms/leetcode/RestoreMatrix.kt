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
 * 1605. Find Valid Matrix Given Row and Column Sums
 * @see <a href="https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums">Source</a>
 */
fun interface RestoreMatrix {
    operator fun invoke(rowSum: IntArray, colSum: IntArray): Array<IntArray>
}

class RestoreMatrixGreedy : RestoreMatrix {
    override fun invoke(rowSum: IntArray, colSum: IntArray): Array<IntArray> {
        val result = Array(rowSum.size) { y ->
            buildLine(rowSum[y], colSum)
        }
        return result
    }

    private fun buildLine(rowSum: Int, colSums: IntArray): IntArray {
        var rowSum = rowSum
        val row = IntArray(colSums.size)
        for (x in 0 until colSums.size) {
            val value = Math.min(rowSum, colSums[x])
            colSums[x] -= value
            rowSum -= value
            row[x] = value
        }
        return row
    }
}
