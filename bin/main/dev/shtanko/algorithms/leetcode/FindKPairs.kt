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

import kotlin.math.abs

/**
 * 532. K-diff Pairs in an Array
 */
fun interface FindKPairs {
    operator fun invoke(nums: IntArray, k: Int): Int
}

sealed class FindKStrategy {

    /**
     * Approach 1: Brute Force
     */
    object BruteForce : FindKStrategy(), FindKPairs {
        override operator fun invoke(nums: IntArray, k: Int): Int {
            nums.sort()
            var result = 0

            for (i in nums.indices) {
                if (i > 0 && nums[i] == nums[i - 1]) continue
                for (j in i + 1 until nums.size) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue
                    if (abs(nums[j] - nums[i]) == k) result++
                }
            }

            return result
        }
    }

    /**
     * Approach 2: Two Pointers
     */
    object TwoPointers : FindKStrategy(), FindKPairs {
        override operator fun invoke(nums: IntArray, k: Int): Int {
            nums.sort()
            var left = 0
            var right = 1
            var result = 0

            while (left < nums.size && right < nums.size) {
                if (left == right || nums[right] - nums[left] < k) {
                    // List item 1 in the text
                    right++
                } else if (nums[right] - nums[left] > k) {
                    // List item 2 in the text
                    left++
                } else {
                    // List item 3 in the text
                    left++
                    result++
                    while (left < nums.size && nums[left] == nums[left - 1]) left++
                }
            }
            return result
        }
    }

    /**
     * Approach 3: Hashmap
     */
    object Hashmap : FindKStrategy(), FindKPairs {
        override operator fun invoke(nums: IntArray, k: Int): Int {
            var result = 0

            val counter = HashMap<Int, Int>()
            for (n: Int in nums) {
                counter[n] = counter.getOrDefault(n, 0) + 1
            }

            for (entry: Map.Entry<Int, Int> in counter.entries) {
                if (k > 0 && counter.containsKey(entry.key + k)) {
                    result++
                } else if (k == 0 && entry.value > 1) {
                    result++
                }
            }
            return result
        }
    }
}
