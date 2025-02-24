/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2962. Count Subarrays Where Max Element Appears at Least K Times
 * @see <a href="https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times">Source</a>
 */
@Medium("https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times")
fun interface CountSubarraysMaxElement {
    operator fun invoke(nums: IntArray, k: Int): Long
}

class CountSubarraysMaxElementSlidingWindow : CountSubarraysMaxElement {
    override fun invoke(nums: IntArray, k: Int): Long {
        val maxElement: Int = nums.max()
        var ans: Long = 0
        var start = 0
        var maxElementsInWindow = 0

        for (element in nums) {
            if (element == maxElement) {
                maxElementsInWindow++
            }
            while (k == maxElementsInWindow) {
                if (nums[start] == maxElement) {
                    maxElementsInWindow--
                }
                start++
            }
            ans += start.toLong()
        }

        return ans
    }
}

class CountSubarraysMaxElementMaxElement : CountSubarraysMaxElement {
    override fun invoke(nums: IntArray, k: Int): Long {
        val maxElement = nums.maxOrNull() ?: 0
        val indexesOfMaxElements = mutableListOf<Int>()
        var ans: Long = 0

        for (i in nums.indices) {
            if (nums[i] == maxElement) {
                indexesOfMaxElements.add(i)
            }

            val freq = indexesOfMaxElements.size
            if (freq >= k) {
                ans += (indexesOfMaxElements[freq - k] + 1).toLong()
            }
        }

        return ans
    }
}
