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
 * 2215. Find the Difference of Two Arrays
 * @see <a href="https://leetcode.com/problems/find-the-difference-of-two-arrays/">Source</a>
 */
fun interface FindArrayDifference {
    fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>>
}

/**
 * Approach 1: Brute Force
 */
class FindArrayDifferenceBF : FindArrayDifference {
    override fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
        return listOf(
            getElementsOnlyInFirstList(nums1, nums2),
            getElementsOnlyInFirstList(nums2, nums1),
        )
    }

    // Returns the elements in the first arg nums1 that don't exist in the second arg nums2.
    private fun getElementsOnlyInFirstList(nums1: IntArray, nums2: IntArray): List<Int> {
        val onlyInNums1: MutableSet<Int> = HashSet()

        // Iterate over each element in the list nums1.
        for (num in nums1) {
            var existInNums2 = false
            // Check if num is present in the second arg nums2.
            for (x in nums2) {
                if (x == num) {
                    existInNums2 = true
                    break
                }
            }
            if (!existInNums2) {
                onlyInNums1.add(num)
            }
        }

        // Convert to vector.
        return ArrayList(onlyInNums1)
    }
}

class FindArrayDifferenceHashSet : FindArrayDifference {
    override fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
        return listOf(
            getElementsOnlyInFirstList(nums1, nums2),
            getElementsOnlyInFirstList(nums2, nums1),
        )
    }

    // Returns the elements in the first arg nums1 that don't exist in the second arg nums2.
    private fun getElementsOnlyInFirstList(nums1: IntArray, nums2: IntArray): List<Int> {
        val onlyInNums1: MutableSet<Int> = HashSet()

        // Store nums2 elements in an unordered set.
        val existsInNums2: MutableSet<Int> = HashSet()
        for (num in nums2) {
            existsInNums2.add(num)
        }

        // Iterate over each element in the list nums1.
        for (num in nums1) {
            if (!existsInNums2.contains(num)) {
                onlyInNums1.add(num)
            }
        }

        // Convert to vector.
        return ArrayList(onlyInNums1)
    }
}
