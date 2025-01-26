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

import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 632. Smallest Range Covering Elements from K Lists
 * @see <a href="https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/">Source</a>
 */
@Hard("https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists")
fun interface SmallestRangeFinder {
    operator fun invoke(nums: List<List<Int>>): IntArray
}

class SmallestRangeFinderTwoPointer : SmallestRangeFinder {
    override fun invoke(nums: List<List<Int>>): IntArray {
        val merged = mutableListOf<IntArray>()

        // Merge all lists with their list index
        nums.forEachIndexed { i, list ->
            list.forEach { num ->
                merged.add(intArrayOf(num, i))
            }
        }

        // Sort the merged list
        merged.sortBy { it[0] }

        // Two pointers to track the smallest range
        val freq = mutableMapOf<Int, Int>()
        var left = 0
        var count = 0
        var rangeStart = 0
        var rangeEnd = Int.MAX_VALUE

        for (right in merged.indices) {
            freq[merged[right][1]] = freq.getOrDefault(merged[right][1], 0) + 1
            if (freq[merged[right][1]] == 1) count++

            // When all lists are represented, try to shrink the window
            while (count == nums.size) {
                val curRange = merged[right][0] - merged[left][0]
                if (curRange < rangeEnd - rangeStart) {
                    rangeStart = merged[left][0]
                    rangeEnd = merged[right][0]
                }

                freq[merged[left][1]] = freq[merged[left][1]]!! - 1
                if (freq[merged[left][1]] == 0) count--
                left++
            }
        }

        return intArrayOf(rangeStart, rangeEnd)
    }
}
