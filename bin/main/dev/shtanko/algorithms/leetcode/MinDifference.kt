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

import kotlin.math.min

/**
 * 1906. Minimum Absolute Difference Queries
 * @see <a href="https://leetcode.com/problems/minimum-absolute-difference-queries/">Source</a>
 */
fun interface MinDifference {
    operator fun invoke(nums: IntArray, queries: Array<IntArray>): IntArray
}

class MinDifferencePrefixSum : MinDifference {
    override operator fun invoke(nums: IntArray, queries: Array<IntArray>): IntArray {
        val n: Int = nums.size
        val count = Array(n + 1) { IntArray(LIMIT) }
        val q: Int = queries.size
        val ans = IntArray(q)

        for (i in 0 until n) {
            for (j in 0 until LIMIT) count[i + 1][j] = count[i][j]
            ++count[i + 1][nums[i] - 1]
        }

        for (i in 0 until q) {
            val low = queries[i][0]
            val high = queries[i][1] + 1
            val present: MutableList<Int> = ArrayList()
            var min = LIMIT
            for (j in 0 until LIMIT) if (count[high][j] - count[low][j] != 0) present.add(j)
            for (j in 1 until present.size) min = min(min, present[j] - present[j - 1])
            if (present.size == 1) min = -1
            ans[i] = min
        }

        return ans
    }

    companion object {
        private const val LIMIT = 100
    }
}
