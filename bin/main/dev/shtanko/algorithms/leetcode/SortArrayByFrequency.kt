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
 * 1636. Sort Array by Increasing Frequency
 * @see <a href="https://leetcode.com/problems/sort-array-by-increasing-frequency">Source</a>
 */
fun interface SortArrayByFrequency {
    operator fun invoke(nums: IntArray): IntArray
}

class SortArrayByFrequencyImpl : SortArrayByFrequency {
    override fun invoke(nums: IntArray): IntArray {
        val map = mutableMapOf<Int, Int>()
        nums.forEach { num -> map[num] = map.getOrDefault(num, 0) + 1 }
        return nums.sortedWith(compareBy({ map[it] }, { -it })).toIntArray()
    }
}
