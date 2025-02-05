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
 * 26. Remove Duplicates from Sorted Array
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array">Source</a>
 */
fun interface RemoveDuplicates {
    operator fun invoke(nums: IntArray): Int
}

class RemoveDuplicatesSolution : RemoveDuplicates {
    override fun invoke(nums: IntArray): Int {
        val n = nums.size
        // If the array has less than 2 elements, return its length
        if (n < 2) return n
        var count = 0
        // Iterate through the array starting from the second element
        for (i in 1 until n) {
            // If the current element is the same as the previous one, increment the count
            if (nums[i] == nums[i - 1]) {
                count++
            } else {
                // Move the current element to the correct position
                nums[i - count] = nums[i]
            }
        }
        // Return the length of the array without duplicates
        return n - count
    }
}
