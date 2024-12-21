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

import dev.shtanko.algorithms.annotations.BruteForce

/**
 * 1. Two Sum
 * @see <a href="https://leetcode.com/problems/two-sum/">Source</a>
 */
fun interface TwoSumStrategy {
    operator fun invoke(nums: IntArray, target: Int): IntArray
}

/**
 * Approach 1: Brute Force
 * Time complexity : O(n^2).
 * Space complexity : O(1).
 */
@BruteForce
class TwoSumBruteForce : TwoSumStrategy {
    override operator fun invoke(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[j] == target - nums[i]) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf()
        // or throw IllegalArgumentException("No two sum solution")
    }
}

/**
 * Approach 2: Two-pass Hash Table
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
class TwoSumTwoPassHashTable : TwoSumStrategy {
    override operator fun invoke(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = HashMap()
        for (i in nums.indices) {
            map[nums[i]] = i
        }
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement) && map[complement] != i) {
                return intArrayOf(i, map.getOrDefault(complement, 0))
            }
        }
        return intArrayOf()
        // or throw IllegalArgumentException("No two sum solution")
    }
}

/**
 * Approach 3: One-pass Hash Table
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
class TwoSumOnePassHashTable : TwoSumStrategy {
    override operator fun invoke(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = HashMap()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                map[complement]?.let {
                    return intArrayOf(it, i)
                }
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }
}

/**
 * Approach 4: Kotlin style One-pass Hash Table
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
class TwoSumOneHashMap : TwoSumStrategy {
    override operator fun invoke(nums: IntArray, target: Int): IntArray {
        val map: MutableMap<Int, Int> = HashMap()
        nums.forEachIndexed { index, i ->
            map[i]?.let { return intArrayOf(it, index) }
            map[target - i] = index
        }
        return intArrayOf()
    }
}
