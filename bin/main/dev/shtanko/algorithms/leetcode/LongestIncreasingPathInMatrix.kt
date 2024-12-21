/*
 * Copyright 2022 Oleksii Shtanko
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

import kotlin.math.max

/**
 * 329. Longest Increasing Path in a Matrix
 * @see <a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/">Source</a>
 */
fun interface LongestIncreasingPathInMatrix {
    operator fun invoke(matrix: Array<IntArray>): Int
}

class LongestIncreasingPathInMatrixDFS : LongestIncreasingPathInMatrix {

    private val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))

    override operator fun invoke(matrix: Array<IntArray>): Int {
        if (matrix.isEmpty()) return 0
        val m: Int = matrix.size
        val n: Int = matrix[0].size
        val cache = Array(m) { IntArray(n) }
        var max = 1
        for (i in 0 until m) {
            for (j in 0 until n) {
                val len = dfs(matrix, i, j, m, n, cache)
                max = max(max, len)
            }
        }
        return max
    }

    fun dfs(matrix: Array<IntArray>, i: Int, j: Int, m: Int, n: Int, cache: Array<IntArray>): Int {
        if (cache[i][j] != 0) return cache[i][j]
        var max = 1
        for (dir in dirs) {
            val x = i + dir[0]
            val y = j + dir[1]
            val local = x < 0 || x >= m || y < 0 || y >= n
            if (local || matrix[x][y] <= matrix[i][j]) continue
            val len = 1 + dfs(matrix, x, y, m, n, cache)
            max = max(max, len)
        }
        cache[i][j] = max
        return max
    }
}
