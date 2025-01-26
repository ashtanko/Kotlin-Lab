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

import dev.shtanko.algorithms.MOD
import java.util.Stack

/**
 * 907. Sum of Subarray Minimums
 * @see <a href="https://leetcode.com/problems/sum-of-subarray-minimums/">Source</a>
 */
fun interface SumSubarrayMins {
    operator fun invoke(arr: IntArray): Int
}

/**
 * Approach: Monotonic Stack + Dynamic Programming
 */
class SumSubarrayMinsDP : SumSubarrayMins {
    override operator fun invoke(arr: IntArray): Int {
        val stack = Stack<Int>()
        val dp = IntArray(arr.size)
        var sumOfMinimums = 0L

        for (i in arr.indices) {
            while (stack.isNotEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop()
            }

            dp[i] = if (stack.isNotEmpty()) {
                val previousSmaller = stack.peek()
                dp[previousSmaller] + (i - previousSmaller) * arr[i]
            } else {
                (i + 1) * arr[i]
            }

            stack.push(i)
        }

        dp.forEach { count ->
            sumOfMinimums += count
            sumOfMinimums %= MOD
        }

        return sumOfMinimums.toInt()
    }
}
