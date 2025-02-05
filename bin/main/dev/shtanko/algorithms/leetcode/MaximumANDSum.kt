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

import kotlin.math.max
import kotlin.math.pow

/**
 * 2172. Maximum AND Sum of Array
 * @see <a href="https://leetcode.com/problems/maximum-and-sum-of-array/">Source</a>
 */
fun interface MaximumANDSum {
    operator fun invoke(nums: IntArray, numSlots: Int): Int
}

class MaximumANDSumDP : MaximumANDSum {
    override operator fun invoke(nums: IntArray, numSlots: Int): Int {
        val mask = 3.0.pow(numSlots.toDouble()).toInt() - 1
        val memo = IntArray(mask + 1)
        return dp(nums.size - 1, mask, numSlots, memo, nums)
    }

    private fun dp(i: Int, mask: Int, ns: Int, memo: IntArray, arr: IntArray): Int {
        if (memo[mask] > 0) return memo[mask]
        if (i < 0) return 0
        var slot = 1
        var bit = 1
        while (slot <= ns) {
            if (mask / bit % 3 > 0) {
                memo[mask] = max(memo[mask], (arr[i] and slot) + dp(i - 1, mask - bit, ns, memo, arr))
            }
            ++slot
            bit *= 3
        }
        return memo[mask]
    }
}
