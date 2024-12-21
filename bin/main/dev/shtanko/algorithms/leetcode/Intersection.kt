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
 * 349. Intersection of Two Arrays
 * @see <a href="https://leetcode.com/problems/intersection-of-two-arrays">Source</a>
 */
fun interface IntersectionStrategy {
    operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray
}

/**
 * Use two hash sets
 * Time complexity: O(n)
 */
class IntersectionTwoSets : IntersectionStrategy {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        val set = nums1.toSet()
        val intersect: MutableSet<Int> = HashSet()

        for (num in nums2) {
            if (set.contains(num)) {
                intersect.add(num)
            }
        }
        val result = IntArray(intersect.size)
        var i = 0
        for (num in intersect) {
            result[i++] = num
        }

        return result
    }
}

/**
 * Sort both arrays, use two pointers
 * Time complexity: O(nlogn)
 */
class IntersectionTwoPointers : IntersectionStrategy {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        val set: MutableSet<Int> = HashSet()
        nums1.sort()
        nums2.sort()
        var i = 0
        var j = 0
        while (i < nums1.size && j < nums2.size) {
            when {
                nums1[i] < nums2[j] -> {
                    i++
                }

                nums1[i] > nums2[j] -> {
                    j++
                }

                else -> {
                    set.add(nums1[i])
                    i++
                    j++
                }
            }
        }
        val result = IntArray(set.size)
        var k = 0
        for (num in set) {
            result[k++] = num
        }
        return result
    }
}

/**
 *
 */
class IntersectionBinarySearch : IntersectionStrategy {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        val set: MutableSet<Int> = HashSet()
        nums2.sort()
        for (num in nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num)
            }
        }
        var i = 0
        val result = IntArray(set.size)
        for (num in set) {
            result[i++] = num
        }
        return result
    }

    private fun binarySearch(nums: IntArray, target: Int): Boolean {
        var low = 0
        var high = nums.size - 1
        while (low <= high) {
            val mid = low + (high - low) / 2
            if (nums[mid] == target) {
                return true
            }
            if (nums[mid] > target) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return false
    }
}
