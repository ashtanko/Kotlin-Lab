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

import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.level.Medium
import kotlin.math.max
import kotlin.math.min

/**
 * 1162. As Far from Land as Possible
 * @see <a href="https://leetcode.com/problems/as-far-from-land-as-possible/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/as-far-from-land-as-possible")
fun interface AsFarFromLandAsPossible {
    fun maxDistance(grid: Array<IntArray>): Int
}

@DP
class AsFarFromLandAsPossibleDP : AsFarFromLandAsPossible {
    override fun maxDistance(grid: Array<IntArray>): Int {
        val n: Int = grid.size
        val m: Int = grid[0].size
        var res = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == 1) continue
                grid[i][j] = LIMIT
                if (i > 0) grid[i][j] = min(grid[i][j], grid[i - 1][j] + 1)
                if (j > 0) grid[i][j] = min(grid[i][j], grid[i][j - 1] + 1)
            }
        }

        for (i in n - 1 downTo -1 + 1) {
            for (j in m - 1 downTo -1 + 1) {
                if (grid[i][j] == 1) continue
                if (i < n - 1) grid[i][j] = min(grid[i][j], grid[i + 1][j] + 1)
                if (j < m - 1) grid[i][j] = min(grid[i][j], grid[i][j + 1] + 1)
                res = max(res, grid[i][j]) // update the maximum
            }
        }

        return if (res == LIMIT) -1 else res - 1
    }

    companion object {
        // 201 here cuz as the description, the size won't exceed 100.
        private const val LIMIT = 201
    }
}
