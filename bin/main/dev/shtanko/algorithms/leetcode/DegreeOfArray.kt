/*
 * Copyright 2021 Oleksii Shtanko
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
 * Degree of an Array
 * @see <a href="https://leetcode.com/problems/degree-of-an-array/">Source</a>
 */
object DegreeOfArray {
    fun findShortestSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val left: MutableMap<Int, Int> = HashMap()
        val right: MutableMap<Int, Int> = HashMap()
        val count: MutableMap<Int, Int> = HashMap()

        for (i in nums.indices) {
            val x = nums[i]
            if (left[x] == null) left[x] = i
            right[x] = i
            count[x] = count.getOrDefault(x, 0) + 1
        }

        var ans: Int = nums.size
        val degree = count.values.max()
        for (x in count.keys) {
            if (count[x] == degree) {
                ans = min(ans, right.getOrDefault(x, 0) - left.getOrDefault(x, 0) + 1)
            }
        }
        return ans
    }
}
