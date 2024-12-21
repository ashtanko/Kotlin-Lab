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
 * 1422. Maximum Score After Splitting a String
 * @see <a href="https://leetcode.com/problems/maximum-score-after-splitting-a-string">Source</a>
 */
sealed interface MaxScoreAfterSplittingString {
    operator fun invoke(s: String): Int

    data object BruteForce : MaxScoreAfterSplittingString {
        override fun invoke(s: String): Int {
            var ans = 0

            for (i in 0 until s.length - 1) {
                var curr = 0
                for (j in 0..i) {
                    if (s[j] == '0') {
                        curr++
                    }
                }

                for (j in i + 1 until s.length) {
                    if (s[j] == '1') {
                        curr++
                    }
                }

                ans = max(ans.toDouble(), curr.toDouble()).toInt()
            }

            return ans
        }
    }

    data object CountLeftZerosAndRightOnes : MaxScoreAfterSplittingString {
        override fun invoke(s: String): Int {
            var ones = 0
            for (i in s.indices) {
                if (s[i] == '1') {
                    ones++
                }
            }

            var ans = 0
            var zeros = 0
            for (i in 0 until s.length - 1) {
                if (s[i] == '1') {
                    ones--
                } else {
                    zeros++
                }

                ans = max(ans.toDouble(), (zeros + ones).toDouble()).toInt()
            }

            return ans
        }
    }

    data object OnePass : MaxScoreAfterSplittingString {
        override fun invoke(s: String): Int {
            var ones = 0
            var zeros = 0
            var best = Int.MIN_VALUE

            for (i in 0 until s.length - 1) {
                if (s[i] == '1') {
                    ones++
                } else {
                    zeros++
                }

                best = max(best.toDouble(), (zeros - ones).toDouble()).toInt()
            }

            if (s[s.length - 1] == '1') {
                ones++
            }

            return best + ones
        }
    }
}
