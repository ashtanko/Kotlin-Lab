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
 * 33. Search in Rotated Sorted Array
 * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">Source</a>
 */
fun interface SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int
}

class SearchInRotatedSortedArraySearch : SearchInRotatedSortedArray {
    override fun search(nums: IntArray, target: Int): Int {
        val minIdx = findMinIdx(nums)
        if (target == nums[minIdx]) return minIdx
        val m: Int = nums.size
        var start = if (target <= nums[m - 1]) minIdx else 0
        var end = if (target > nums[m - 1]) minIdx else m - 1

        while (start <= end) {
            val mid = start + (end - start) / 2
            if (nums[mid] == target) {
                return mid
            } else if (target > nums[mid]) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        return -1
    }

    private fun findMinIdx(nums: IntArray): Int {
        var start = 0
        var end = nums.size - 1
        while (start < end) {
            val mid = start + (end - start) / 2
            if (nums[mid] > nums[end]) start = mid + 1 else end = mid
        }
        return start
    }
}
