/*
 * Copyright 2022 Oleksii Shtanko
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
 * 2006. Count Number of Pairs With Absolute Difference K
 * @see <a href="https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/">Source</a>
 */
fun interface CountKDifference {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class CountKDifferenceBruteForce : CountKDifference {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        var count = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                val curr = nums[i]
                val next = nums[j]
                if (abs(curr - next) == k) {
                    count++
                }
            }
        }
        return count
    }
}

class CountKDifferenceMap : CountKDifference {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        val map: MutableMap<Int, Int> = HashMap()
        var res = 0
        for (i in nums.indices) {
            val el = nums[i]
            if (map.containsKey(el - k)) {
                res += map[el - k] ?: 0
            }
            if (map.containsKey(el + k)) {
                res += map[el + k] ?: 0
            }
            map[el] = (map[el] ?: 0) + 1
        }
        return res
    }
}
