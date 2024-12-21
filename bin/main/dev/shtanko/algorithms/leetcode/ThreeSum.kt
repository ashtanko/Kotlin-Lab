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
 * 3 Sum.
 * @see <a href="https://leetcode.com/problems/3sum/">Source</a>
 */
fun interface ThreeSum {
    operator fun invoke(nums: IntArray): List<List<Int>>
}

/**
 * Approach 1: Two Pointers.
 * Time Complexity: O(n^2).
 * Space Complexity: O(n^2).
 */
class ThreeSumTwoPointers : ThreeSum {
    override operator fun invoke(nums: IntArray): List<List<Int>> {
        nums.sort()
        val res: MutableList<List<Int>> = ArrayList<List<Int>>()
        var i = 0
        while (i < nums.size && nums[i] <= 0) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res)
            }
            ++i
        }
        return res
    }

    private fun twoSumII(nums: IntArray, i: Int, res: MutableList<List<Int>>) {
        var lo = i + 1
        var hi = nums.size - 1
        while (lo < hi) {
            val sum = nums[i] + nums[lo] + nums[hi]
            when {
                sum < 0 -> {
                    ++lo
                }

                sum > 0 -> {
                    --hi
                }

                else -> {
                    res.add(listOf(nums[i], nums[lo++], nums[hi--]))
                    while (lo < hi && nums[lo] == nums[lo - 1]) ++lo
                }
            }
        }
    }
}

/**
 * Approach 2: Hashset.
 * Time Complexity: O(n^2).
 * Space Complexity: O(n^2).
 */
class ThreeSumHashset : ThreeSum {
    override operator fun invoke(nums: IntArray): List<List<Int>> {
        nums.sort()
        val res: MutableList<List<Int>> = ArrayList<List<Int>>()
        var i = 0
        while (i < nums.size && nums[i] <= 0) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, res)
            }
            ++i
        }
        return res
    }

    private fun twoSum(nums: IntArray, i: Int, res: MutableList<List<Int>>) {
        val seen = HashSet<Int>()
        var j = i + 1
        while (j < nums.size) {
            val complement = -nums[i] - nums[j]
            if (seen.contains(complement)) {
                res.add(listOf(nums[i], nums[j], complement))
                while (j + 1 < nums.size && nums[j] == nums[j + 1]) ++j
            }
            seen.add(nums[j])
            ++j
        }
    }
}

/**
 * Approach 3: "No-Sort".
 * Time Complexity: O(n^2).
 * Space Complexity: O(n).
 */
class ThreeSumNoSort : ThreeSum {
    override operator fun invoke(nums: IntArray): List<List<Int>> {
        val res: MutableSet<List<Int>> = HashSet()
        val dups: MutableSet<Int> = HashSet()
        val seen: MutableMap<Int, Int> = HashMap()
        for (i in nums.indices) if (dups.add(nums[i])) {
            for (j in i + 1 until nums.size) {
                val complement = -nums[i] - nums[j]
                if (seen.containsKey(complement) && seen[complement] == i) {
                    val triplet: MutableList<Int> = mutableListOf(nums[i], nums[j], complement)
                    triplet.sort()
                    res.add(triplet)
                }
                seen[nums[j]] = i
            }
        }
        return ArrayList(res)
    }
}
