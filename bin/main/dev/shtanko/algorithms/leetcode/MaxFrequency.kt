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
 * 1838. Frequency of the Most Frequent Element
 * @see <a href="https://leetcode.com/problems/frequency-of-the-most-frequent-element/">Source</a>
 */
fun interface MaxFrequency {
    fun maxFrequency(nums: IntArray, k: Int): Int
}

class MaxFrequencySlidingWindow : MaxFrequency {
    override fun maxFrequency(nums: IntArray, k: Int): Int {
        val n = nums.size
        nums.sort()
        var ans = 0
        var left = 0

        var sum: Long = 0

        for (right in 0 until n) {
            sum += nums[right]
            while (nums[right] * 1L * (right - left + 1) - sum > k) {
                sum -= nums[left]
                left++
            }
            ans = max(ans, right - left + 1)
        }

        return ans
    }
}
