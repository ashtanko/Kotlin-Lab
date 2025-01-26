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

fun interface MinimumPathSum {
    operator fun invoke(grid: Array<IntArray>): Int
}

class MinimumPathSumDP : MinimumPathSum {
    override operator fun invoke(grid: Array<IntArray>): Int {
        val m: Int = grid.size
        val n: Int = grid.first().size
        for (i in 1 until n) {
            grid.first()[i] += grid.first()[i - 1]
        }
        for (i in 1 until m) {
            grid[i][0] += grid[i - 1].first()
            for (j in 1 until n) {
                grid[i][j] += grid[i][j - 1].coerceAtMost(grid[i - 1][j])
            }
        }
        return grid[m - 1][n - 1]
    }
}
