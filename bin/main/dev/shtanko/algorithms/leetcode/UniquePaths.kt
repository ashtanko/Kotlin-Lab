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

import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.DFS

/**
 * 62. Unique Paths
 * @see <a href="https://leetcode.com/problems/unique-paths">Source</a>
 */
fun interface UniquePaths {
    operator fun invoke(m: Int, n: Int): Int
}

/**
 * Approach 1: Brute-Force
 */
@BruteForce
class UniquePathsBruteForce : UniquePaths {
    override fun invoke(m: Int, n: Int): Int {
        return perform(m, n, 0, 0)
    }

    /**
     * Calculate the number of unique paths from the top-left corner (0,0) to the
     * bottom-right corner (m-1, n-1) in a grid of size m x n.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @param i The current row index.
     * @param j The current column index.
     * @return The number of unique paths from (0,0) to (m-1, n-1).
     */
    private fun perform(m: Int, n: Int, i: Int = 0, j: Int = 0): Int {
        // base case: If the current position is out of bounds, there is no path.
        if (i >= m || j >= n) return 0
        // base case: If we have reached the destination, there is one unique path.
        if (i == m - 1 && j == n - 1) return 1
        // recursive case: Sum the paths going right and down from the current position.
        return perform(m, n, i + 1, j) + perform(m, n, i, j + 1)
    }
}

/**
 * Approach 2: Dynamic Programming - Memoization
 */
@DFS
class UniquePathsDpMemo : UniquePaths {
    /**
     * Calculate the number of unique paths from the top-left corner (0,0) to the
     * bottom-right corner (m-1, n-1) in a grid of size m x n using memoization.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths from (0,0) to (m-1, n-1).
     */
    override fun invoke(m: Int, n: Int): Int {
        // Create a HashMap to memoize results.
        val memo = HashMap<Pair<Int, Int>, Int>()

        /**
         * Recursive function to find unique paths.
         *
         * @param i The current row index.
         * @param j The current column index.
         * @return The number of unique paths from (i, j) to (m-1, n-1).
         */
        fun dfs(i: Int, j: Int): Int {
            if (i >= m || j >= n) return 0
            if (i == m - 1 && j == n - 1) return 1

            // Check if the result for this cell is already memoized.
            if (memo.containsKey(Pair(i, j))) {
                return memo[Pair(i, j)]!!
            }

            // Calculate the number of paths recursively.
            val paths = dfs(i + 1, j) + dfs(i, j + 1)

            // Memoize the result before returning.
            memo[Pair(i, j)] = paths

            return paths
        }

        // Start the calculation from the top-left corner (0,0).
        return dfs(0, 0)
    }
}

/**
 * Approach 3: Dynamic Programming - Tabulation
 */
class UniquePathsDpTabulation : UniquePaths {
    override fun invoke(m: Int, n: Int): Int {
        if (m == 0 && n == 0) return 0
        // Create a 2D array to store the number of unique paths for each cell.
        val dp = Array(m) { IntArray(n) { 1 } }

        // Calculate the number of unique paths using dynamic programming.
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }

        // The result is stored in the bottom-right corner of the grid.
        return dp[m - 1][n - 1]
    }
}

/**
 * Approach 4: Space Optimized Dynamic Programming
 */
class UniquePathsDpOpt : UniquePaths {
    override fun invoke(m: Int, n: Int): Int {
        if (m == 0 && n == 0) return 0
        // Create an array to store the number of unique paths for each column.
        val dp = IntArray(n) { 1 }

        // Calculate the number of unique paths using dynamic programming.
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[j] += dp[j - 1]
            }
        }

        // The result is stored in the last column of the array.
        return dp[n - 1]
    }
}

/**
 * Approach 5: Math
 */
class UniquePathsMath : UniquePaths {

    override fun invoke(m: Int, n: Int): Int {
        if (m == 0 && n == 0) return 0
        var ans = 1
        var i = m + n - 2
        var j = 1
        while (i >= maxOf(m, n)) {
            ans = ans * i / j
            i--
            j++
        }
        return ans
    }
}
