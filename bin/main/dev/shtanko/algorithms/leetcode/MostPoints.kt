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

import kotlin.math.max

/**
 * 2140. Solving Questions With Brainpower
 * @see <a href="https://leetcode.com/problems/solving-questions-with-brainpower/">Source</a>
 */
fun interface MostPoints {
    operator fun invoke(questions: Array<IntArray>): Long
}

class MostPointsDP : MostPoints {
    override operator fun invoke(questions: Array<IntArray>): Long {
        val n: Int = questions.size
        val dp = LongArray(n)
        dp[n - 1] = questions[n - 1][0].toLong()

        for (i in n - 2 downTo 0) {
            dp[i] = questions[i][0].toLong()
            val skip = questions[i][1]
            if (i + skip + 1 < n) {
                dp[i] += dp[i + skip + 1]
            }

            dp[i] = max(dp[i], dp[i + 1])
        }

        return dp[0]
    }
}
