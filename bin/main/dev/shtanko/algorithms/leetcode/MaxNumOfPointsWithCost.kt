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

import dev.shtanko.algorithms.annotations.DP

/**
 * 1937. Maximum Number of Points with Cost
 * @see <a href="https://leetcode.com/problems/maximum-number-of-points-with-cost/">Source</a>
 */
fun interface MaxNumOfPointsWithCost {
    operator fun invoke(points: Array<IntArray>): Long
}

/**
 * Approach 1: Dynamic Programming
 */
@DP
class MaxNumOfPointsWithCostDP : MaxNumOfPointsWithCost {
    override fun invoke(points: Array<IntArray>): Long {
        val rows = points.size
        val cols = points[0].size
        var previousRow = LongArray(cols)

        // Initialize the first row
        for (col in 0 until cols) {
            previousRow[col] = points[0][col].toLong()
        }

        // Process each row
        for (row in 0 until rows - 1) {
            val leftMax = LongArray(cols)
            val rightMax = LongArray(cols)
            val currentRow = LongArray(cols)

            // Calculate left-to-right maximum
            leftMax[0] = previousRow[0]
            for (col in 1 until cols) {
                leftMax[col] = maxOf(leftMax[col - 1] - 1, previousRow[col])
            }

            // Calculate right-to-left maximum
            rightMax[cols - 1] = previousRow[cols - 1]
            for (col in cols - 2 downTo 0) {
                rightMax[col] = maxOf(rightMax[col + 1] - 1, previousRow[col])
            }

            // Calculate the current row's maximum points
            for (col in 0 until cols) {
                currentRow[col] = points[row + 1][col].toLong() +
                    maxOf(leftMax[col], rightMax[col])
            }

            // Update previousRow for the next iteration
            previousRow = currentRow
        }

        // Find the maximum value in the last processed row
        return previousRow.maxOrNull() ?: 0L
    }
}

/**
 * Approach 2: Dynamic Programming (Optimized)
 */
@DP
class MaxNumOfPointsWithCostDPBetter : MaxNumOfPointsWithCost {
    override fun invoke(points: Array<IntArray>): Long {
        val cols = points[0].size
        var currentRow = LongArray(cols)
        var previousRow = LongArray(cols)

        for (row in points) {
            // Left to right pass
            var runningMax = 0L
            for (col in 0 until cols) {
                runningMax = maxOf(runningMax - 1, previousRow[col])
                currentRow[col] = runningMax
            }

            // Right to left pass
            runningMax = 0L
            for (col in cols - 1 downTo 0) {
                runningMax = maxOf(runningMax - 1, previousRow[col])
                currentRow[col] = maxOf(currentRow[col], runningMax) + row[col]
            }

            // Update previousRow for next iteration
            previousRow = currentRow.copyOf()
        }

        // Find maximum points in the last row
        return previousRow.maxOrNull() ?: 0L
    }
}
