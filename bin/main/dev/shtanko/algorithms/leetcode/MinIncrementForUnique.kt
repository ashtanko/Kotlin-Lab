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

import kotlin.math.max

/**
 * 945. Minimum Increment to Make Array Unique
 * @see <a href="https://leetcode.com/problems/minimum-increment-to-make-array-unique/">Source</a>
 */
fun interface MinIncrementForUnique {
    operator fun invoke(nums: IntArray): Int
}

class MinIncrementForUniqueSorting : MinIncrementForUnique {
    override fun invoke(nums: IntArray): Int {
        var minIncrements = 0
        nums.sort()

        for (i in 1 until nums.size) {
            if (nums[i] <= nums[i - 1]) {
                val increment = nums[i - 1] + 1 - nums[i]
                minIncrements += increment
                nums[i] = nums[i - 1] + 1
            }
        }

        return minIncrements
    }
}

class MinIncrementForUniqueCounting : MinIncrementForUnique {
    override fun invoke(nums: IntArray): Int {
        val n: Int = nums.size
        var max = 0
        var minIncrements = 0

        for (num in nums) {
            max = max(max, num)
        }
        val frequencyCount = IntArray(n + max)
        for (num in nums) {
            frequencyCount[num]++
        }
        for (i in frequencyCount.indices) {
            if (frequencyCount[i] <= 1) continue
            val duplicates = frequencyCount[i] - 1
            frequencyCount[i + 1] += duplicates
            frequencyCount[i] = 1
            minIncrements += duplicates
        }

        return minIncrements
    }
}
