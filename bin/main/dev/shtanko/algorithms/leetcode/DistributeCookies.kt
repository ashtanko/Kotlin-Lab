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
import kotlin.math.min

/**
 * 2305. Fair Distribution of Cookies
 * @see <a href="https://leetcode.com/problems/fair-distribution-of-cookies/">Source</a>
 */
fun interface DistributeCookies {
    operator fun invoke(cookies: IntArray, k: Int): Int
}

class DistributeCookiesBacktracking : DistributeCookies {
    override operator fun invoke(cookies: IntArray, k: Int): Int {
        val distribute = IntArray(k)
        return dfs(0, distribute, cookies, k, k)
    }

    private fun dfs(i: Int, distribute: IntArray, cookies: IntArray, k: Int, zeroCount: Int): Int {
        // If there are not enough cookies remaining, return Integer.MAX_VALUE
        // as it leads to an invalid distribution.
        var count = zeroCount
        if (cookies.size - i < count) {
            return Int.MAX_VALUE
        }

        // After distributing all cookies, return the unfairness of this
        // distribution.
        if (i == cookies.size) {
            var unfairness = Int.MIN_VALUE
            for (value in distribute) {
                unfairness = max(unfairness, value)
            }
            return unfairness
        }

        // Try to distribute the i-th cookie to each child, and update answer
        // as the minimum unfairness in these distributions.
        var answer = Int.MAX_VALUE
        for (j in 0 until k) {
            count -= if (distribute[j] == 0) 1 else 0
            distribute[j] += cookies[i]

            // Recursively distribute the next cookie.
            answer = min(answer, dfs(i + 1, distribute, cookies, k, count))
            distribute[j] -= cookies[i]
            count += if (distribute[j] == 0) 1 else 0
        }
        return answer
    }
}
