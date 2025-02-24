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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1975. Maximum Matrix Sum
 * @see <a href="https://leetcode.com/problems/maximum-matrix-sum/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-matrix-sum/")
fun interface MaximumMatrixSum {
    operator fun invoke(matrix: Array<IntArray>): Long
}

class MaximumMatrixSumSolution : MaximumMatrixSum {
    override fun invoke(matrix: Array<IntArray>): Long {
        var totalSum = 0L
        var minAbsVal = Int.MAX_VALUE
        var negativeCount = 0

        for (row in matrix) {
            for (value in row) {
                totalSum += kotlin.math.abs(value)
                if (value < 0) {
                    negativeCount++
                }
                minAbsVal = kotlin.math.min(minAbsVal, kotlin.math.abs(value))
            }
        }

        // Adjust if the count of negative numbers is odd
        if (negativeCount % 2 != 0) {
            totalSum -= 2 * minAbsVal
        }
        return totalSum
    }
}
