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
 * Unique Paths II
 * @see <a href="https://leetcode.com/problems/unique-paths-ii/">Source</a>
 */
fun interface UniquePaths2 {
    /**
     * Calculates the number of unique paths from the top-left corner to the bottom-right corner
     * of a grid, considering obstacles.
     *
     * @param obstacleGrid A 2D array representing the grid with obstacles (0 for open cell, 1 for obstacle).
     * @return The number of unique paths from top-left to bottom-right.
     */
    operator fun invoke(obstacleGrid: Array<IntArray>): Int
}

/**
 * Approach 1: Dynamic Programming
 * Time Complexity: O(M times N).
 * Space Complexity: O(1).
 */
class UniquePaths2DP : UniquePaths2 {
    override operator fun invoke(obstacleGrid: Array<IntArray>): Int {
        val r: Int = obstacleGrid.size
        val c: Int = obstacleGrid[0].size

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1

        // Filling the values for the first column
        for (i in 1 until r) {
            obstacleGrid[i][0] = if (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) 1 else 0
        }

        // Filling the values for the first row
        for (i in 1 until c) {
            obstacleGrid[0][i] = if (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) 1 else 0
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (i in 1 until r) {
            for (j in 1 until c) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1]
                } else {
                    obstacleGrid[i][j] = 0
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[r - 1][c - 1]
    }
}

/**
 * A more concise approach using Kotlin features.
 */
class UniquePaths2Short : UniquePaths2 {
    override operator fun invoke(obstacleGrid: Array<IntArray>): Int {
        val width = obstacleGrid[0].size
        val dp = IntArray(width) { 0 }
        dp[0] = 1
        obstacleGrid.forEach { row ->
            for (j in 0 until width) {
                dp[j] = if (row[j] == 1) 0 else dp.getOrElse(j - 1) { 0 } + dp[j]
            }
        }
        return dp.last()
    }
}
