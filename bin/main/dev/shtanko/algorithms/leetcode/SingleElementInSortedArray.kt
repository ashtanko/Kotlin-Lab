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
 * 540. Single Element in a Sorted Array
 * @see <a href="https://leetcode.com/problems/single-element-in-a-sorted-array/">Source</a>
 */
fun interface SingleElementInSortedArray {
    /**
     * Finds the single element in a sorted array.
     * @param nums The sorted array of integers.
     * @return The single element in the array.
     */
    operator fun invoke(nums: IntArray): Int
}

/**
 * Implementation of SingleElementInSortedArray using binary search.
 * This implementation assumes that the input array is sorted in non-decreasing order.
 */
class SingleElementInSortedArrayBS : SingleElementInSortedArray {
    /**
     * Finds the single element in a sorted array using binary search.
     * @param nums The sorted array of integers.
     * @return The single element in the array.
     */
    override fun invoke(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            var mid = left.plus(right).div(2)
            if (mid % 2 == 1) {
                mid--
            }
            if (nums[mid] != nums[mid + 1]) {
                right = mid
            } else {
                left = mid + 2
            }
        }
        return nums[left]
    }
}
