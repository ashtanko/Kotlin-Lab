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

fun interface RotateImage {
    fun rotate(matrix: Array<IntArray>)
}

/**
 * Approach 1: Rotate Groups of Four Cells
 * Time complexity : O(M)
 * Space complexity : O(1)
 */
class RotateGroups : RotateImage {
    override fun rotate(matrix: Array<IntArray>) {
        val n: Int = matrix.size
        for (i in 0 until (n + 1) / 2) {
            for (j in 0 until n / 2) {
                val temp = matrix[n - 1 - j][i]
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1]
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i]
                matrix[j][n - 1 - i] = matrix[i][j]
                matrix[i][j] = temp
            }
        }
    }
}

/**
 * Approach 2: Reverse on Diagonal and then Reverse Left to Right
 * Time complexity : O(M)
 * Space complexity : O(1)
 */
class ReverseLeftToRight : RotateImage {
    override fun rotate(matrix: Array<IntArray>) {
        transpose(matrix)
        reflect(matrix)
    }

    private fun transpose(matrix: Array<IntArray>) {
        val n = matrix.size
        for (i in 0 until n) {
            for (j in i until n) {
                val tmp = matrix[j][i]
                matrix[j][i] = matrix[i][j]
                matrix[i][j] = tmp
            }
        }
    }

    private fun reflect(matrix: Array<IntArray>) {
        val n = matrix.size
        for (i in 0 until n) {
            for (j in 0 until n / 2) {
                val tmp = matrix[i][j]
                matrix[i][j] = matrix[i][n - j - 1]
                matrix[i][n - j - 1] = tmp
            }
        }
    }
}
