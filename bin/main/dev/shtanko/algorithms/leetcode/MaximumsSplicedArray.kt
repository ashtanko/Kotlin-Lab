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
 * 2321. Maximum Score Of Spliced Array
 * @see <a href="https://leetcode.com/problems/maximum-score-of-spliced-array/">Source</a>
 */
fun interface MaximumsSplicedArray {
    operator fun invoke(nums1: IntArray, nums2: IntArray): Int
}

class MaximumsSplicedArrayKadane : MaximumsSplicedArray {
    override operator fun invoke(nums1: IntArray, nums2: IntArray): Int {
        val n: Int = nums1.size
        var sum1 = 0
        var sum2 = 0
        var maxEndHere1 = Int.MIN_VALUE
        var maxSoFar1 = 0

        var maxEndHere2 = Int.MIN_VALUE
        var maxSoFar2 = 0
        for (i in 0 until n) {
            sum1 += nums1[i]
            sum2 += nums2[i]

            // kadane algorithm
            val diff = nums2[i] - nums1[i]
            maxSoFar1 = max(diff, maxSoFar1 + diff)
            maxEndHere1 = max(maxEndHere1, maxSoFar1)
            maxSoFar2 = max(-diff, maxSoFar2 - diff)
            maxEndHere2 = max(maxEndHere2, maxSoFar2)
        }

        return max(maxEndHere1 + sum1, maxEndHere2 + sum2)
    }
}
