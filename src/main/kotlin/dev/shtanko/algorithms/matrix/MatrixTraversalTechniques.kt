/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.algorithms.matrix

typealias Matrix = Array<IntArray>

sealed interface MatrixTraversalStrategy {
    operator fun invoke(matrix: Matrix): List<Int>
}

class RowWiseTraversal : MatrixTraversalStrategy {
    override fun invoke(matrix: Matrix): List<Int> {
        val res = mutableListOf<Int>()
        for (row in matrix) {
            for (value in row) {
                res.add(value)
            }
        }
        return res
    }
}
