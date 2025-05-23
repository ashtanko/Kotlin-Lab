/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
 * 1824. Minimum Sideway Jumps
 * @see <a href="https://leetcode.com/problems/minimum-sideway-jumps/">Source</a>
 */
fun interface MinimumSidewayJumps {
    operator fun invoke(obstacles: IntArray): Int
}

class MinimumSidewayJumpsDP : MinimumSidewayJumps {
    override operator fun invoke(obstacles: IntArray): Int {
        val dp = intArrayOf(1, 0, 1)
        for (a in obstacles) {
            if (a > 0) dp[a - 1] = LIMIT
            for (i in 0..2) if (a != i + 1) {
                dp[i] = min(
                    dp[i],
                    min(
                        dp[(i + 1) % 3],
                        dp[(i + 2) % 3],
                    ) + 1,
                )
            }
        }
        return min(dp[0], min(dp[1], dp[2]))
    }

    companion object {
        private const val LIMIT = 1000000
    }
}
