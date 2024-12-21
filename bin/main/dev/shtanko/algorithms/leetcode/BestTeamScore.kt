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

/**
 * 1626. Best Team With No Conflicts
 * @see <a href="https://leetcode.com/problems/best-team-with-no-conflicts/">Source</a>
 */
@Medium("https://leetcode.com/problems/best-team-with-no-conflicts")
fun interface BestTeamScore {
    operator fun invoke(scores: IntArray, ages: IntArray): Int
}

@DP
class BestTeamScoreDP : BestTeamScore {
    override operator fun invoke(scores: IntArray, ages: IntArray): Int {
        val n: Int = ages.size
        val candidate = Array(n) { IntArray(2) }

        for (i in 0 until n) {
            candidate[i][0] = ages[i]
            candidate[i][1] = scores[i]
        }
        candidate.sortWith { a, b -> if (a[0] == b[0]) a[1] - b[1] else a[0] - b[0] }
        val dp = IntArray(n)
        dp[0] = candidate[0][1]
        var max = dp[0]
        for (i in 1 until n) {
            dp[i] = candidate[i][1]
            for (j in 0 until i) {
                if (candidate[j][1] <= candidate[i][1]) {
                    dp[i] = max(dp[i], candidate[i][1] + dp[j])
                }
            }
            max = max(dp[i], max)
        }
        return max
    }
}
