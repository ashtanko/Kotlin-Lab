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

import dev.shtanko.algorithms.annotations.level.Medium
import kotlin.math.min

/**
 * Count Square Submatrices with All Ones.
 * @see <a href="https://leetcode.com/problems/count-square-submatrices-with-all-ones/">Source</a>
 */
@Medium("https://leetcode.com/problems/count-square-submatrices-with-all-ones")
object CountSquares {
    operator fun invoke(matrix: Array<IntArray>): Int {
        var res = 0
        for (i in matrix.indices) {
            for (j in matrix.first().indices) {
                if (matrix[i][j] > 0 && i > 0 && j > 0) {
                    matrix[i][j] = min(matrix[i - 1][j - 1], min(matrix[i - 1][j], matrix[i][j - 1])) + 1
                }
                res += matrix[i][j]
            }
        }
        return res
    }
}
