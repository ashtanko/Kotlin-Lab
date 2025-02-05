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

/**
 * 1608. Special Array With X Elements Greater Than or Equal X
 * @see <a href="https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/">Source</a>
 */
fun interface SpecialArray {
    operator fun invoke(nums: IntArray): Int
}

class SpecialArraySort : SpecialArray {
    override fun invoke(nums: IntArray): Int {
        nums.sort()
        val n = nums.size
        for (i in 1..n) {
            val k = getFirstGreaterOrEqual(nums, i)

            if (n - k == i) {
                return i
            }
        }

        return -1
    }

    private fun getFirstGreaterOrEqual(nums: IntArray, value: Int): Int {
        var start = 0
        var end = nums.size - 1

        var index = nums.size
        while (start <= end) {
            val mid = (start + end) / 2

            if (nums[mid] >= value) {
                index = mid
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        return index
    }
}

class SpecialArrayPrefixSum : SpecialArray {
    override fun invoke(nums: IntArray): Int {
        val n = nums.size
        val prefixSum = IntArray(n + 1)

        for (num in nums) {
            prefixSum[num.coerceAtMost(n)]++
        }

        for (i in n - 1 downTo 0) {
            prefixSum[i] += prefixSum[i + 1]
        }

        for (i in 1..n) {
            if (prefixSum[i] == i) {
                return i
            }
        }

        return -1
    }
}
