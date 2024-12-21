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
 * 74. Search a 2D Matrix
 * @see <a href="https://leetcode.com/problems/search-a-2d-matrix/">Source</a>
 */
fun interface SearchMatrix {
    operator fun invoke(matrix: Array<IntArray>, target: Int): Boolean
}

class SearchMatrixBS : SearchMatrix {
    override operator fun invoke(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) {
            return false
        }
        var start = 0
        val rows: Int = matrix.size
        val cols: Int = matrix[0].size
        var end = rows * cols - 1
        while (start <= end) {
            val mid = (start + end) / 2
            if (matrix[mid / cols][mid % cols] == target) {
                return true
            }
            if (matrix[mid / cols][mid % cols] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        return false
    }
}
