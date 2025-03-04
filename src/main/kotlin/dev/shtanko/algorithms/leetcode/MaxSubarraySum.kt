/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.SlidingWindow
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2461. Maximum Sum of Distinct Subarrays With Length K
 * @see <a href="https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/")
fun interface MaxSubarraySum {
    operator fun invoke(nums: IntArray, k: Int): Long
}

@SlidingWindow
data object MaxSubarraySumSlidingWindow : MaxSubarraySum {
    override fun invoke(nums: IntArray, k: Int): Long {
        var ans = 0L
        var currentSum = 0L
        var begin = 0
        var end = 0
        val numToIndex = mutableMapOf<Int, Int>()

        while (end < nums.size) {
            val currNum = nums[end]
            val lastOccurrence = numToIndex[currNum] ?: -1

            // If current window already has number or if window is too big, adjust window
            while (begin <= lastOccurrence || end - begin + 1 > k) {
                currentSum -= nums[begin]
                begin++
            }

            numToIndex[currNum] = end
            currentSum += nums[end]
            if (end - begin + 1 == k) {
                ans = maxOf(ans, currentSum)
            }
            end++
        }

        return ans
    }
}
