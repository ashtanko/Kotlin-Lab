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
 * 1572. Matrix Diagonal Sum
 * https://leetcode.com/problems/matrix-diagonal-sum/
 */
fun interface MatrixDiagonalSum {
    fun diagonalSum(mat: Array<IntArray>): Int
}

/**
 * Approach: Iterating over Diagonal Elements
 */
class MatrixDiagonalSumIteration : MatrixDiagonalSum {
    override fun diagonalSum(mat: Array<IntArray>): Int {
        val n: Int = mat.size
        var ans = 0

        for (i in 0 until n) {
            // Add elements from primary diagonal.
            ans += mat[i][i]
            // Add elements from secondary diagonal.
            ans += mat[n - 1 - i][i]
        }
        // If n is odd, subtract the middle element as its added twice.
        if (n % 2 != 0) {
            ans -= mat[n / 2][n / 2]
        }
        return ans
    }
}
