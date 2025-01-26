/*
 * Copyright 2021 Oleksii Shtanko
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
 * 153. Find Minimum in Rotated Sorted Array
 * @see <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">Source</a>
 */
fun findMinInRotatedSortedArr(nums: IntArray): Int {
    var start = 0
    var end = nums.size - 1
    while (start < end) {
        val mid = end.plus(start).div(2)
        if (nums[mid] >= nums[start] && nums[mid] > nums[end]) {
            start = mid + 1
        } else {
            end = mid
        }
    }
    return nums[start]
}
