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

import kotlin.math.min

/**
 * 2088. Count Fertile Pyramids in a Land
 * @see <a href="https://leetcode.com/problems/count-fertile-pyramids-in-a-land/">Source</a>
 */
fun interface CountPyramids {
    operator fun invoke(grid: Array<IntArray>): Int
}

class CountPyramidsDP : CountPyramids {
    override operator fun invoke(grid: Array<IntArray>): Int {
        val m: Int = grid.size
        val n: Int = grid.first().size
        val rev = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) rev[m - i - 1][j] = grid[i][j]
        }
        return cal(grid) + cal(rev)
    }

    private fun cal(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var res = 0
        for (i in 1 until m) {
            var j = 0
            var cnt = 0
            while (j < n) {
                if (0 != grid[i][j]) cnt++ else cnt = 0
                if (0 == cnt || 0 == j) {
                    ++j
                    continue
                }
                grid[i][j] = min(grid[i - 1][j - 1] + 1, cnt + 1 shr 1)
                res += grid[i][j] - 1
                ++j
            }
        }
        return res
    }
}

class CountPyramidsDP2 : CountPyramids {
    override operator fun invoke(grid: Array<IntArray>): Int {
        val inverseGrid = inverse(grid)
        return helper(grid) + helper(inverseGrid)
    }

    private fun helper(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var ans = 0
        for (i in 1 until m) {
            for (j in 1 until n - 1) {
                if (grid[i][j] > 0) {
                    grid[i][j] = min(
                        min(grid[i - 1][j], grid[i - 1][j - 1]),
                        grid[i - 1][j + 1],
                    ) + 1
                    ans += grid[i][j] - 1
                }
            }
        }
        return ans
    }

    private fun inverse(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val g = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                g[i][j] = grid[m - i - 1][j]
            }
        }
        return g
    }
}
