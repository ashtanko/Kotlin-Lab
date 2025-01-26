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

/**
 * Sum of Unique Elements
 * @see <a href="https://leetcode.com/problems/sum-of-unique-elements">Source</a>
 */
fun interface SumOfUnique {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Brute Force
 */
class SumOfUniqueBruteForce : SumOfUnique {
    override operator fun invoke(nums: IntArray): Int {
        val unique = IntArray(LIMIT) { 0 }
        var ans = 0
        for (num in nums) {
            val idx = num - 1
            unique[idx]++
            if (unique[idx] == 1) {
                ans += num
            } else if (unique[idx] == 2) {
                ans -= num
            }
        }
        return ans
    }

    companion object {
        private const val LIMIT = 101
    }
}

/**
 * Hashmap, single loop
 */
class SumOfUniqueHashMap : SumOfUnique {
    override operator fun invoke(nums: IntArray): Int {
        var sum = 0
        val map: MutableMap<Int, Int> = HashMap()
        for (num in nums) {
            if (map.containsKey(num)) {
                var repeated = map[num]!!
                map[num] = ++repeated
                if (repeated == 1) {
                    sum -= num
                }
                continue
            }
            map[num] = 0
            sum += num
        }
        return sum
    }
}

/**
 * Filter
 */
class SumOfUniqueFilter : SumOfUnique {
    override operator fun invoke(nums: IntArray): Int = nums.filter { n -> nums.count { it == n } < 2 }.sum()
}
