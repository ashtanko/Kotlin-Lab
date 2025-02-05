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
 * 2540. Minimum Common Value
 * @see <a href="https://leetcode.com/problems/minimum-common-value">Source</a>
 */
fun interface MinCommonVal {
    operator fun invoke(nums1: IntArray, nums2: IntArray): Int
}

class MinCommonValHashSet : MinCommonVal {
    override fun invoke(nums1: IntArray, nums2: IntArray): Int {
        val set1: MutableSet<Int> = HashSet()

        // Add the elements from nums1 to set1
        for (num in nums1) {
            set1.add(num)
        }

        // Search for each element of nums2 in set1
        // Return the first common element found
        for (num in nums2) {
            if (set1.contains(num)) {
                return num
            }
        }

        // Return -1 if there are no common elements
        return -1
    }
}

class MinCommonValTwoPointers : MinCommonVal {
    override fun invoke(nums1: IntArray, nums2: IntArray): Int {
        var first = 0
        var second = 0
        // Traverse through both arrays with two pointers
        // Increment the pointer with a smaller value at that index
        // Return the first common element found
        while (first < nums1.size && second < nums2.size) {
            if (nums1[first] < nums2[second]) {
                first++
            } else if (nums1[first] > nums2[second]) {
                second++
            } else {
                return nums1[first]
            }
        }

        // Return -1 if there are no common elements
        return -1
    }
}

class MinCommonValBinarySearch : MinCommonVal {
    override fun invoke(nums1: IntArray, nums2: IntArray): Int {
        // Binary search should be done on the larger array
        // If nums1 is longer, call getCommon with the arrays swapped
        if (nums1.size > nums2.size) {
            return invoke(nums2, nums1)
        }

        // Search for each element of nums1 in nums2
        // Return the first common element found
        for (num in nums1) {
            if (binarySearch(num, nums2)) {
                return num
            }
        }

        // Return -1 if there are no common elements
        return -1
    }

    private fun binarySearch(target: Int, nums: IntArray): Boolean {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] > target) {
                right = mid - 1
            } else if (nums[mid] < target) {
                left = mid + 1
            } else {
                return true
            }
        }
        return false
    }
}
