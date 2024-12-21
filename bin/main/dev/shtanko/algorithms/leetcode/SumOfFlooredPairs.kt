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

import dev.shtanko.algorithms.MOD
import kotlin.math.min

/**
 * 1862. Sum of Floored Pairs
 * @see <a href="https://leetcode.com/problems/sum-of-floored-pairs/">Source</a>
 */
fun interface SumOfFlooredPairs {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Implementation of SumOfFlooredPairs using a brute force approach.
 */
class SumOfFlooredPairsBF : SumOfFlooredPairs {
    /**
     * Calculates the sum of floored pairs using a brute force approach.
     * @param nums The array of integers.
     * @return The sum of floored pairs.
     */
    override operator fun invoke(nums: IntArray): Int {
        val counts = IntArray(MAX + 1)
        for (num in nums) {
            ++counts[num]
        }
        for (i in 1..MAX) {
            counts[i] += counts[i - 1]
        }

        var total: Long = 0
        for (i in 1..MAX) {
            if (counts[i] > counts[i - 1]) {
                var sum: Long = 0
                var j = 1
                while (i * j <= MAX) {
                    val lower = i * j - 1
                    val upper = i * (j + 1) - 1
                    sum += (counts[min(upper, MAX)] - counts[lower]) * j.toLong()
                    ++j
                }
                total = (total + sum % MOD * (counts[i] - counts[i - 1])) % MOD
            }
        }
        return total.toInt()
    }

    companion object {
        private const val MAX = 1e5.toInt()
    }
}

/**
 * Another implementation of SumOfFlooredPairs using a different brute force approach.
 */
class SumOfFlooredPairsBF2 : SumOfFlooredPairs {
    /**
     * Calculates the sum of floored pairs using a different brute force approach.
     * @param nums The array of integers.
     * @return The sum of floored pairs.
     */
    override operator fun invoke(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val max: Int = nums.max()
        val preSum = IntArray(max + 1)
        for (num in nums) preSum[num]++
        for (i in 1..max) preSum[i] += preSum[i - 1]
        var ans = 0
        for (i in 1..max) {
            if (preSum[i] > preSum[i - 1]) {
                val count = preSum[i] - preSum[i - 1]
                var j = 1
                while (i * j <= max) {
                    ans = (ans + count * j * (preSum[min(max, i * (j + 1) - 1)] - preSum[i * j - 1])) % MOD
                    j++
                }
            }
        }
        return ans
    }
}
