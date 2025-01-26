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

/**
 * 238. Product of Array Except Self
 * @see <a href="https://leetcode.com/problems/product-of-array-except-self">Source</a>
 */
fun interface ProductExceptSelf {
    operator fun invoke(nums: IntArray): IntArray
}

class ProductExceptSelfArr : ProductExceptSelf {
    override operator fun invoke(nums: IntArray): IntArray {
        val n = nums.size
        val output = IntArray(n)

        for (i in 0 until n) {
            var product = 1
            for (j in 0 until n) {
                if (i == j) continue
                product *= nums[j]
            }
            output[i] = product
        }

        return output
    }
}

class ProductExceptSelfDp : ProductExceptSelf {
    override operator fun invoke(nums: IntArray): IntArray {
        val n = nums.size
        val ans = IntArray(n)
        val leftProduct = IntArray(n)
        val rightProduct = IntArray(n)

        leftProduct[0] = 1
        for (i in 1 until n) {
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1]
        }

        rightProduct[n - 1] = 1
        for (i in n - 2 downTo 0) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1]
        }

        for (i in 0 until n) {
            ans[i] = leftProduct[i] * rightProduct[i]
        }

        return ans
    }
}

class ProductExceptSelfDpOpt : ProductExceptSelf {
    override operator fun invoke(nums: IntArray): IntArray {
        val n = nums.size
        val output = IntArray(n)
        output[0] = 1

        for (i in 1 until n) {
            output[i] = output[i - 1] * nums[i - 1]
        }

        var right = 1
        for (i in n - 1 downTo 0) {
            output[i] *= right
            right *= nums[i]
        }

        return output
    }
}
