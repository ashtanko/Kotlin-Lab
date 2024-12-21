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

import java.util.LinkedList
import java.util.Queue

/**
 * 566. Reshape the Matrix
 * @see <a href="https://leetcode.com/problems/reshape-the-matrix/">Source</a>
 */
fun interface MatrixReshape {
    operator fun invoke(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray>
}

sealed class MatrixReshapeStrategy {

    /**
     * Approach 1: Using Queue
     * Time complexity : O(m * n).
     * Space complexity : O(m * n).
     */
    class UsingQueue : MatrixReshape {
        override operator fun invoke(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
            val res = Array(r) { IntArray(c) }
            if (mat.isEmpty() || r * c != mat.size * mat[0].size
            ) {
                return mat
            }
            val queue: Queue<Int> = LinkedList()
            for (i in mat.indices) {
                for (j in 0 until mat[0].size) {
                    queue.add(mat[i][j])
                }
            }
            for (i in 0 until r) {
                for (j in 0 until c) {
                    res[i][j] = queue.remove()
                }
            }
            return res
        }
    }

    /**
     * Approach 2: Without Using Extra Space
     * Time complexity : O(m * n).
     * Space complexity : O(m * n).
     */
    class WithoutUsingExtraSpace : MatrixReshape {
        override operator fun invoke(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
            val res = Array(r) { IntArray(c) }
            if (mat.isEmpty() || r * c != mat.size * mat[0].size) {
                return mat
            }
            var rows = 0
            var cols = 0
            for (i in mat.indices) {
                for (j in 0 until mat[0].size) {
                    res[rows][cols] = mat[i][j]
                    cols++
                    if (cols == c) {
                        rows++
                        cols = 0
                    }
                }
            }
            return res
        }
    }

    /**
     * Approach 3: Using division and modulus
     * Time complexity : O(m * n).
     * Space complexity : O(m * n).
     */
    class UsingDivision : MatrixReshape {
        override operator fun invoke(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
            val res = Array(r) { IntArray(c) }
            if (mat.isEmpty() || r * c != mat.size * mat[0].size) {
                return mat
            }
            var count = 0
            for (i in mat.indices) {
                for (j in 0 until mat[0].size) {
                    res[count / c][count % c] = mat[i][j]
                    count++
                }
            }
            return res
        }
    }
}
