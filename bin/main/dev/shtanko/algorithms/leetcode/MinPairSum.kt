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

/**
 * 1877. Minimize Maximum Pair Sum in Array
 * @see <a href="https://leetcode.com/problems/minimize-maximum-pair-sum-in-array">Source</a>
 */
fun interface MinPairSum {
    operator fun invoke(nums: IntArray): Int
}

class MinPairSumSorting : MinPairSum {
    override fun invoke(nums: IntArray): Int {
        nums.sort()

        var maxSum = 0
        val n = nums.size
        for (i in 0 until n / 2) {
            val currentSum = nums[i] + nums[n - 1 - i]
            maxSum = maxOf(maxSum, currentSum)
        }

        return maxSum
    }
}
