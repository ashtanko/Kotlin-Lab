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
 * 1685. Sum of Absolute Differences in a Sorted Array
 * @see <a href="https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/">Source</a>
 */
fun interface GetSumAbsoluteDifferences {
    operator fun invoke(nums: IntArray): IntArray
}

class GetSumAbsoluteDifferencesPrefixSum : GetSumAbsoluteDifferences {
    override fun invoke(nums: IntArray): IntArray {
        val n: Int = nums.size
        val prefix = IntArray(n)
        prefix[0] = nums[0]
        for (i in 1 until n) {
            prefix[i] = prefix[i - 1] + nums[i]
        }

        val ans = IntArray(n)
        for (i in 0 until n) {
            val leftSum = prefix[i] - nums[i]
            val rightSum = prefix[n - 1] - prefix[i]
            val rightCount = n - 1 - i
            val leftTotal = i * nums[i] - leftSum
            val rightTotal = rightSum - rightCount * nums[i]
            ans[i] = leftTotal + rightTotal
        }

        return ans
    }
}

class GetSumAbsoluteDifferencesPrefixSum2 : GetSumAbsoluteDifferences {
    override fun invoke(nums: IntArray): IntArray {
        val n: Int = nums.size
        var totalSum = 0
        for (num in nums) {
            totalSum += num
        }

        var leftSum = 0
        val ans = IntArray(n)
        for (i in 0 until n) {
            val rightSum = totalSum - leftSum - nums[i]
            val rightCount = n - 1 - i
            val leftTotal = i * nums[i] - leftSum
            val rightTotal = rightSum - rightCount * nums[i]
            ans[i] = leftTotal + rightTotal
            leftSum += nums[i]
        }

        return ans
    }
}
