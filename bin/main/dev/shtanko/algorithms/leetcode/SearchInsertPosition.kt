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
 * 35. Search Insert Position
 * @see <a href="https://leetcode.com/problems/search-insert-position">Source</a>
 */
fun interface SearchInsertPosition {
    fun searchInsert(nums: IntArray, target: Int): Int
}

class SearchInsertPositionIterative : SearchInsertPosition {
    override fun searchInsert(nums: IntArray, target: Int): Int {
        var low = 0
        var high = nums.size - 1
        while (low <= high) {
            val mid = (low + high) / 2
            when {
                nums[mid] == target -> {
                    return mid
                }

                nums[mid] > target -> {
                    high = mid - 1
                }

                else -> {
                    low = mid + 1
                }
            }
        }
        return low
    }
}

class SearchInsertPositionFast : SearchInsertPosition {
    override fun searchInsert(nums: IntArray, target: Int): Int {
        var start = 0
        var end: Int = nums.size - 1

        while (start <= end) {
            val mid = start + (end - start) / 2
            if (nums[mid] == target) return mid else if (nums[mid] > target) end = mid - 1 else start = mid + 1
        }

        return start
    }
}
