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

import kotlin.math.max
import kotlin.math.min

/**
 * Maximum Product Subarray
 * @see <a href="https://leetcode.com/problems/maximum-product-subarray/">Source</a>
 */
fun interface MaximumProductSubArray {
    fun maxProduct(nums: IntArray): Int
}

class MaxProductBruteForce : MaximumProductSubArray {
    override fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var result = nums.first()

        for (i in nums.indices) {
            var accu = 1
            for (j in i until nums.size) {
                accu *= nums[j]
                result = max(result, accu)
            }
        }
        return result
    }
}

class MaxProductDP : MaximumProductSubArray {
    override fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var maxSoFar = nums.first()
        var minSoFar = nums.first()
        var result = maxSoFar

        for (i in 1 until nums.size) {
            val curr = nums[i]
            val tempMax = max(curr, max(maxSoFar * curr, minSoFar * curr))
            minSoFar = min(curr, min(maxSoFar * curr, minSoFar * curr))
            maxSoFar = tempMax
            result = max(maxSoFar, result)
        }
        return result
    }
}
