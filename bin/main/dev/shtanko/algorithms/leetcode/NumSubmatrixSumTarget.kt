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

/**
 * 1074. Number of Submatrices That Sum to Target
 * @see <a href="https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/">Source</a>
 */
fun interface NumSubmatrixSumTarget {
    operator fun invoke(matrix: Array<IntArray>, target: Int): Int
}

class NumSubmatrixSumTargetSolution : NumSubmatrixSumTarget {
    override operator fun invoke(matrix: Array<IntArray>, target: Int): Int {
        var res = 0
        val m = matrix.size
        val n = matrix[0].size

        // Compute prefix sum for each row
        for (i in 0 until m) {
            for (j in 1 until n) {
                matrix[i][j] += matrix[i][j - 1]
            }
        }

        // Iterate over all pairs of columns and count submatrices with sum equal to target
        val counter = HashMap<Int, Int>()
        for (i in 0 until n) {
            for (j in i until n) {
                counter.clear()
                counter[0] = 1
                var cur = 0
                for (k in 0 until m) {
                    cur += matrix[k][j] - if (i > 0) matrix[k][i - 1] else 0
                    res += counter.getOrDefault(cur - target, 0)
                    counter[cur] = counter.getOrDefault(cur, 0) + 1
                }
            }
        }
        return res
    }
}

class NumSubmatrixSumTargetSolution2 : NumSubmatrixSumTarget {
    override fun invoke(matrix: Array<IntArray>, target: Int): Int {
        val prefixSumMatrix = calculatePrefixSum(matrix)
        return countSubmatrixSums(prefixSumMatrix, target)
    }

    private fun calculatePrefixSum(matrix: Array<IntArray>): Array<IntArray> {
        val m = matrix.size
        val n = matrix[0].size
        val prefixSumMatrix = Array(m) { IntArray(n) }

        for (row in 0 until m) {
            for (col in 0 until n) {
                prefixSumMatrix[row][col] = matrix[row][col] + if (col > 0) prefixSumMatrix[row][col - 1] else 0
            }
        }
        return prefixSumMatrix
    }

    private fun countSubmatrixSums(prefixSumMatrix: Array<IntArray>, target: Int): Int {
        val m = prefixSumMatrix.size
        val n = prefixSumMatrix[0].size
        var count = 0

        for (colStart in 0 until n) {
            for (colEnd in colStart until n) {
                for (rowStart in 0 until m) {
                    var sum = 0
                    for (rowEnd in rowStart until m) {
                        sum += prefixSumMatrix[rowEnd][colEnd] - if (colStart > 0) {
                            prefixSumMatrix[rowEnd][colStart - 1]
                        } else {
                            0
                        }
                        if (sum == target) {
                            count++
                        }
                    }
                }
            }
        }
        return count
    }
}
