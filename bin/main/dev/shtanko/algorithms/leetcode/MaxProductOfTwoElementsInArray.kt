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

/**
 * 1464. Maximum Product of Two Elements in an Array
 * @see <a href="https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array">Source</a>
 */
fun interface MaxProductOfTwoElementsInArray {
    operator fun invoke(nums: IntArray): Int
}

class MaxProductOfTwoElementsInArrayBF : MaxProductOfTwoElementsInArray {
    override fun invoke(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                ans = max(ans, (nums[i] - 1) * (nums[j] - 1))
            }
        }

        return ans
    }
}

class MaxProductOfTwoElementsInArraySort : MaxProductOfTwoElementsInArray {
    override fun invoke(nums: IntArray): Int {
        nums.sort()
        val x = nums[nums.size - 1]
        val y = nums[nums.size - 2]
        return (x - 1) * (y - 1)
    }
}

class TrackSecondBiggest : MaxProductOfTwoElementsInArray {
    override fun invoke(nums: IntArray): Int {
        var biggest = 0
        var secondBiggest = 0
        for (num in nums) {
            if (num > biggest) {
                secondBiggest = biggest
                biggest = num
            } else {
                secondBiggest = max(secondBiggest.toDouble(), num.toDouble()).toInt()
            }
        }

        return (biggest - 1) * (secondBiggest - 1)
    }
}
