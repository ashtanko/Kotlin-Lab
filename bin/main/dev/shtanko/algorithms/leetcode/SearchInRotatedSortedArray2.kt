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
 * 81. Search in Rotated Sorted Array II
 * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array-ii/">Source</a>
 */
fun interface SearchInRotatedSortedArray2 {
    fun search(nums: IntArray, target: Int): Boolean
}

class SearchInRotatedSortedArray2BS : SearchInRotatedSortedArray2 {
    override fun search(nums: IntArray, target: Int): Boolean {
        val n: Int = nums.size
        if (n == 0) return false
        var end = n - 1
        var start = 0

        while (start <= end) {
            val mid = start + (end - start) / 2
            if (nums[mid] == target) {
                return true
            }
            if (!isBinarySearchHelpful(nums, start, nums[mid])) {
                start++
                continue
            }
            // which array does pivot belong to.
            val pivotArray = existsInFirst(nums, start, nums[mid])

            // which array does target belong to.
            val targetArray = existsInFirst(nums, start, target)
            // if pivot and target exist in different sorted arrays, recall that xor is true
            // when both operands are distinct
            if (pivotArray xor targetArray) {
                if (pivotArray) {
                    start = mid + 1 // pivot in the first, target in the second
                } else {
                    end = mid - 1 // target in the first, pivot in the second
                }
            } else { // If pivot and target exist in same sorted array
                if (nums[mid] < target) {
                    start = mid + 1
                } else {
                    end = mid - 1
                }
            }
        }
        return false
    }

    // returns true if we can reduce the search space in current binary search space
    private fun isBinarySearchHelpful(arr: IntArray, start: Int, element: Int): Boolean {
        return arr[start] != element
    }

    // returns true if element exists in first array, false if it exists in second
    private fun existsInFirst(arr: IntArray, start: Int, element: Int): Boolean {
        return arr[start] <= element
    }
}
