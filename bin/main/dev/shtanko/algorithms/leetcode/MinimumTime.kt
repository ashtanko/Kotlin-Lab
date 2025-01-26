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
 * 2167. Minimum Time to Remove All Cars Containing Illegal Goods
 * @see <a href="https://leetcode.com/problems/minimum-time-to-remove-all-cars-containing-illegal-goods">
 *     Source</a>
 */
fun interface MinimumTime {
    operator fun invoke(s: String): Int
}

class MinimumTimeOnePass : MinimumTime {
    override operator fun invoke(s: String): Int {
        val n: Int = s.length
        var left = 0
        var res = n
        for (i in 0 until n) {
            left = min(left + (s[i] - '0') * 2, i + 1)
            res = min(res, left + n - 1 - i)
        }
        return res
    }
}

class MinimumTimePrefixSuffix : MinimumTime {
    override operator fun invoke(s: String): Int {
        if (s.isEmpty()) {
            return 0
        }
        return calculateRes(s, calculateLeft(s), calculateRight(s))
    }

    private fun calculateRes(s: String, left: IntArray, right: IntArray, res: Int = Int.MAX_VALUE): Int {
        var r = res
        for (i in s.indices) {
            if (i == 0) {
                if (right[i] < r) {
                    r = right[i]
                }
            } else if (i == s.length - 1) {
                if (left[i] < r) {
                    r = left[i]
                }
            } else {
                if (left[i] + right[i + 1] < r) {
                    r = left[i] + right[i + 1]
                }
            }
        }
        return r
    }

    private fun calculateRight(s: String): IntArray {
        val right = IntArray(s.length)
        for (i in s.length - 1 downTo 0) {
            if (s[i] == '1') {
                right[i] = if (i == s.length - 1) 1 else min(right[i + 1] + 2, s.length - i)
            } else {
                right[i] = if (i == s.length - 1) 0 else right[i + 1]
            }
        }
        return right
    }

    private fun calculateLeft(s: String): IntArray {
        val left = IntArray(s.length)
        for (i in s.indices) {
            if (s[i] == '1') {
                left[i] = if (i == 0) 1 else min(left[i - 1] + 2, i + 1)
            } else {
                left[i] = if (i == 0) 0 else left[i - 1]
            }
        }
        return left
    }
}
