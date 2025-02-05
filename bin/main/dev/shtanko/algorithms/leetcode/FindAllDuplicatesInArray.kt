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

import kotlin.math.abs

/**
 * 442. Find All Duplicates in an Array
 * @see <a href="https://leetcode.com/problems/find-all-duplicates-in-an-array">Source</a>
 */
fun interface FindAllDuplicatesInArray {
    operator fun invoke(nums: IntArray): List<Int>
}

class FindAllDuplicatesInArrayMap : FindAllDuplicatesInArray {
    override fun invoke(nums: IntArray): List<Int> {
        val map = hashMapOf<Int, Int>()
        nums.forEach { num ->
            map[num] = map.getOrDefault(num, 0) + 1
        }
        return map.filter { it.value > 1 }.keys.toList()
    }
}

class FindAllDuplicatesInArraySet : FindAllDuplicatesInArray {
    override fun invoke(nums: IntArray): List<Int> {
        val set = hashSetOf<Int>()
        val result = hashSetOf<Int>()
        nums.forEach { num ->
            if (!set.add(num)) {
                result.add(num)
            }
        }
        return result.toList()
    }
}

class FindAllDuplicatesInArrayImpl : FindAllDuplicatesInArray {
    override fun invoke(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        for (i in nums.indices) {
            val index = abs(nums[i]) - 1
            if (nums[index] < 0) {
                result.add(index + 1)
            }
            nums[index] = -nums[index]
        }
        return result
    }
}
