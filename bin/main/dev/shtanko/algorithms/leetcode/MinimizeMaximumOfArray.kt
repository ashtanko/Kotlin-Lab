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
 * 2439. Minimize Maximum of Array
 * @see <a href="https://leetcode.com/problems/minimize-maximum-of-array/">Source</a>
 */
fun interface MinimizeMaximumOfArray {
    fun minimizeArrayValue(nums: IntArray): Int
}

class MinimizeMaximumOfArrayPrefixSum : MinimizeMaximumOfArray {
    override fun minimizeArrayValue(nums: IntArray): Int {
        var sum: Long = 0
        var res: Long = 0
        for (i in nums.indices) {
            sum += nums[i]
            res = max(res, (sum + i) / (i + 1))
        }
        return res.toInt()
    }
}
