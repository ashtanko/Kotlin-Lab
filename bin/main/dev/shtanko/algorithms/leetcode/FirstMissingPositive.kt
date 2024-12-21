/*
 * Copyright 2020 Oleksii Shtanko
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
 * 41. First Missing Positive
 * @see <a href="https://leetcode.com/problems/first-missing-positive">Source</a>
 */
fun interface FirstMissingPositive {
    operator fun invoke(nums: IntArray): Int
}

class FirstMissingPositiveBoolArray : FirstMissingPositive {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        val seen = BooleanArray(n + 1) // Array for lookup

        // Mark the elements from nums in the lookup array
        for (num in nums) {
            if (num in 1..n) {
                seen[num] = true
            }
        }

        // Iterate through integers 1 to n
        // return smallest missing positive integer
        for (i in 1..n) {
            if (!seen[i]) {
                return i
            }
        }

        // If seen contains all elements 1 to n
        // the smallest missing positive number is n + 1
        return n + 1
    }
}

/**
 * Approach 2: Index as a Hash Key
 */
class FirstMissingPositiveHashKey : FirstMissingPositive {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var contains1 = false

        // Replace negative numbers, zeros,
        // and numbers larger than n with 1s.
        // After this nums contains only positive numbers.
        for (i in 0 until n) {
            // Check whether 1 is in the original array
            if (nums[i] == 1) {
                contains1 = true
            }
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1
            }
        }

        if (!contains1) return 1

        // Mark whether integers 1 to n are in nums
        // Use index as a hash key and negative sign as a presence detector.
        for (i in 0 until n) {
            val value = Math.abs(nums[i])
            if (value == n) {
                nums[0] = -Math.abs(nums[0])
            } else {
                nums[value] = -Math.abs(nums[value])
            }
        }

        // First positive in nums is smallest missing positive integer
        for (i in 1 until n) {
            if (nums[i] > 0) return i
        }

        // nums[0] stores whether n is in nums
        if (nums[0] > 0) {
            return n
        }

        // If nums contains all elements 1 to n
        // the smallest missing positive number is n + 1
        return n + 1
    }
}

/**
 * Approach 3: Cycle Sort
 */
class FirstMissingPositiveCycleSort : FirstMissingPositive {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size

        // Use cycle sort to place positive elements smaller than n
        // at the correct index
        var i = 0
        while (i < n) {
            val correctIdx = nums[i] - 1
            if (nums[i] in 1..n && nums[i] != nums[correctIdx]) {
                swap(nums, i, correctIdx)
            } else {
                i++
            }
        }
        i = 0
        while (i < n) {
            if (nums[i] != i + 1) {
                return i + 1
            }
            i++
        }

        // If all elements are at the correct index
        // the smallest missing positive number is n + 1
        return n + 1
    }

    private fun swap(nums: IntArray, index1: Int, index2: Int) {
        val temp = nums[index1]
        nums[index1] = nums[index2]
        nums[index2] = temp
    }
}
