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

import kotlin.math.abs
import kotlin.math.max

/**
 * 658. Find K Closest Elements
 * @see <a href="https://leetcode.com/problems/find-k-closest-elements/">Source</a>
 */
object FindKClosestElements {

    /**
     * Approach 1: Sort With Custom Comparator
     */
    fun sortWithCustomComparator(arr: IntArray, k: Int, x: Int): List<Int> {
        // Convert from array to list first to make use of Collections.sort()
        var sortedArr: MutableList<Int> = ArrayList()
        for (num in arr) {
            sortedArr.add(num)
        }

        // Sort using custom comparator
        sortedArr.sortWith { num1: Int, num2: Int ->
            abs(num1 - x) - abs(num2 - x)
        }

        // Only take k elements
        sortedArr = sortedArr.subList(0, k)

        // Sort again to have output in ascending order
        sortedArr.sort()
        return sortedArr
    }

    /**
     * Approach 2: Binary Search + Sliding Window
     */
    fun bsSlidingWindow(arr: IntArray, k: Int, x: Int): List<Int> {
        val result: MutableList<Int> = ArrayList()

        // Base case
        if (arr.size == k) {
            for (i in 0 until k) {
                result.add(arr[i])
            }
            return result
        }

        // Binary search to find the closest element
        var left = 0
        var right: Int = arr.size - 1
        var mid = 0
        while (left <= right) {
            mid = (left + right) / 2
            if (arr[mid] > x) {
                right = mid - 1
            } else if (arr[mid] < x) {
                left = mid + 1
            } else {
                break
            }
        }

        // Initialize our sliding window's bounds
        left = max(0, mid - 1)
        right = left + 1

        // While the window size is less than k
        while (right - left - 1 < k) {
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1
                continue
            }

            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.size || abs(arr[left] - x) <= abs(arr[right] - x)) {
                left -= 1
            } else {
                right += 1
            }
        }

        // Build and return the window
        for (i in left + 1 until right) {
            result.add(arr[i])
        }

        return result
    }

    /**
     * Approach 3: Binary Search To Find The Left Bound
     * Time complexity: O(log(N−k)+k).
     * Space complexity: O(1).
     */
    fun bsFindTheLeftBound(arr: IntArray, k: Int, x: Int): List<Int> {
        // Initialize binary search bounds
        var left = 0
        var right: Int = arr.size - k

        // Binary search against the criteria described
        while (left < right) {
            val mid = (left + right) / 2
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        // Create output in correct format
        val result: MutableList<Int> = ArrayList()
        for (i in left until left + k) {
            result.add(arr[i])
        }

        return result
    }
}
