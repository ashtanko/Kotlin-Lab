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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1590. Make Sum Divisible by P
 * @see <a href="https://leetcode.com/problems/make-sum-divisible-by-p">Make Sum Divisible by P</a>
 */
@Medium("https://leetcode.com/problems/make-sum-divisible-by-p")
fun interface MinSubarray {
    operator fun invoke(nums: IntArray, p: Int): Int
}

class MinSubarrayPrefixSum : MinSubarray {
    override fun invoke(nums: IntArray, p: Int): Int {
        val n = nums.size
        var totalSum = 0

        // Step 1: Calculate total sum and target remainder
        for (num in nums) {
            totalSum = (totalSum + num) % p
        }

        val target = totalSum % p
        if (target == 0) {
            return 0 // The array is already divisible by p
        }

        // Step 2: Use a hash map to track prefix sum mod p
        val modMap = mutableMapOf(0 to -1) // To handle the case where the whole prefix is the answer
        var currentSum = 0
        var minLen = n

        // Step 3: Iterate over the array
        for (i in nums.indices) {
            currentSum = (currentSum + nums[i]) % p

            // Calculate what we need to remove
            val needed = (currentSum - target + p) % p

            // If we have seen the needed remainder, we can consider this subarray
            modMap[needed]?.let {
                minLen = minOf(minLen, i - it)
            }

            // Store the current remainder and index
            modMap[currentSum] = i
        }

        // Step 4: Return result
        return if (minLen == n) -1 else minLen
    }
}
