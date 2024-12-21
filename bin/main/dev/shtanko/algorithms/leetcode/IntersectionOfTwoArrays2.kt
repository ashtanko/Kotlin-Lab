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

import dev.shtanko.algorithms.annotations.Sort

/**
 * 350. Intersection of Two Arrays II
 * @see <a href="https://leetcode.com/problems/intersection-of-two-arrays-ii">Source</a>
 */
fun interface IntersectionOfTwoArrays2 {
    operator fun invoke(nums1: IntArray, nums2: IntArray): IntArray
}

@Sort
class IntersectionOfTwoArrays2Sort : IntersectionOfTwoArrays2 {
    override fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        val nums1Length: Int = nums1.size
        val nums2Length: Int = nums2.size
        var firstIndex = 0
        var secondIndex = 0
        var intersectionIndex = 0
        nums1.sort()
        nums2.sort()
        while (firstIndex < nums1Length && secondIndex < nums2Length) {
            when {
                nums1[firstIndex] < nums2[secondIndex] -> firstIndex++
                nums1[firstIndex] > nums2[secondIndex] -> secondIndex++
                else -> {
                    nums1[intersectionIndex++] = nums1[firstIndex++]
                    secondIndex++
                }
            }
        }
        return nums1.copyOfRange(0, intersectionIndex)
    }
}

class IntersectionOfTwoArrays2Map : IntersectionOfTwoArrays2 {
    override fun invoke(nums1: IntArray, nums2: IntArray): IntArray {
        if (nums1.size > nums2.size) {
            return invoke(nums2, nums1)
        }

        val numberFrequency = mutableMapOf<Int, Int>()
        for (number in nums1) {
            numberFrequency[number] = numberFrequency.getOrDefault(number, 0) + 1
        }

        val intersectionResult = mutableListOf<Int>()
        for (number in nums2) {
            if (numberFrequency.getOrDefault(number, 0) > 0) {
                intersectionResult.add(number)
                numberFrequency[number] = numberFrequency.getOrDefault(number, 0) - 1
            }
        }

        return intersectionResult.toIntArray()
    }
}
