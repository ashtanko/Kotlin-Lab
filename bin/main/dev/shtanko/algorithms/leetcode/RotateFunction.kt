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
 * 396. Rotate Function
 * @see <a href="https://leetcode.com/problems/rotate-function/">Source</a>
 */
fun interface RotateFunction {
    fun maxRotateFunction(nums: IntArray): Int
}

class RotateFunctionDP : RotateFunction {
    override fun maxRotateFunction(nums: IntArray): Int {
        val len: Int = nums.size
        if (len == 0 || len == 1) return 0
        var sum = 0
        var dp0 = 0
        var max = Int.MIN_VALUE
        val dp = IntArray(len + 1)
        for (i in 0 until len) {
            sum += nums[i]
            dp0 += i * nums[i]
        }
        dp[0] = dp0
        for (i in 1..len) {
            dp[i] = dp[i - 1] + sum - len * nums[len - i]
            max = max(dp[i], max)
        }
        return max
    }
}
