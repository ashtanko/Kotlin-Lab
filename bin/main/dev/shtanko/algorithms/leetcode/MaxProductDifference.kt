/*
 * Copyright 2023 Oleksii Shtanko
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
import kotlin.math.min

/**
 * 1913. Maximum Product Difference Between Two Pairs
 * @see <a href="<link>">Source</a>
 */
fun interface MaxProductDifference {
    operator fun invoke(nums: IntArray): Int
}

class MaxProductDifferenceSort : MaxProductDifference {
    override fun invoke(nums: IntArray): Int {
        nums.sort()
        return nums[nums.size - 1] * nums[nums.size - 2] - nums[0] * nums[1]
    }
}

class MaxProductDifferenceTrack : MaxProductDifference {
    override fun invoke(nums: IntArray): Int {
        var biggest = 0
        var secondBiggest = 0
        var smallest = Int.MAX_VALUE
        var secondSmallest = Int.MAX_VALUE

        for (num in nums) {
            if (num > biggest) {
                secondBiggest = biggest
                biggest = num
            } else {
                secondBiggest = max(secondBiggest.toDouble(), num.toDouble()).toInt()
            }

            if (num < smallest) {
                secondSmallest = smallest
                smallest = num
            } else {
                secondSmallest = min(secondSmallest.toDouble(), num.toDouble()).toInt()
            }
        }

        return biggest * secondBiggest - smallest * secondSmallest
    }
}
