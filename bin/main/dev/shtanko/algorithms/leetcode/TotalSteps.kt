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

/**
 * 2289. Steps to Make Array Non-decreasing
 * @see <a href="https://leetcode.com/problems/steps-to-make-array-non-decreasing/">Source</a>
 */
fun interface TotalSteps {
    operator fun invoke(nums: IntArray): Int
}

class TotalStepsDp : TotalSteps {
    override operator fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var res = 0
        var j = -1
        val dp = IntArray(n)
        val stack = IntArray(n)
        for (i in n - 1 downTo 0) {
            while (j >= 0 && nums[i] > nums[stack[j]]) {
                dp[i] = max(++dp[i], dp[stack[j--]])
                res = max(res, dp[i])
            }
            stack[++j] = i
        }
        return res
    }
}
