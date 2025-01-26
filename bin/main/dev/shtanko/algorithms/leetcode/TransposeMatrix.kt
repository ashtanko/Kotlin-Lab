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
 * 867. Transpose Matrix
 * @see <a href="https://leetcode.com/problems/transpose-matrix">Source</a>
 */
fun interface TransposeMatrix {
    operator fun invoke(matrix: Array<IntArray>): Array<IntArray>
}

class TransposeMatrixCopyDirectly : TransposeMatrix {
    override fun invoke(matrix: Array<IntArray>): Array<IntArray> {
        val columns = matrix.size
        val rows = matrix[0].size

        val transMatrix = Array(rows) { i ->
            IntArray(columns) { j -> matrix[j][i] }
        }

        return transMatrix
    }
}
