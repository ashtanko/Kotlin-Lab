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

import kotlin.math.max

/**
 * 343. Integer Break
 * @see <a href="https://leetcode.com/problems/integer-break/">Source</a>
 */
fun interface IntegerBreak {
    operator fun invoke(n: Int): Int
}

class IntegerBreakDP : IntegerBreak {
    override operator fun invoke(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[1] = 1
        for (i in 2..n) {
            for (j in 1 until i) {
                dp[i] = max(dp[i], max(j, dp[j]) * max(i - j, dp[i - j]))
            }
        }
        return dp[n]
    }
}
