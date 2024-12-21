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
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 */
fun interface AbstractMaximumProductStrategy {
    operator fun invoke(products: IntArray): Int
}

/**
 * Time complexity : O(n^3).
 * We need to consider every triplet from nums array of length n.
 * Space complexity : O(1). Constant extra space is used.
 */
class MaximumProductBrutForce : AbstractMaximumProductStrategy {
    override operator fun invoke(products: IntArray): Int {
        val n = products.size
        var ans = Int.MIN_VALUE
        for (i in products.indices) {
            for (j in i + 1 until n) {
                for (k in j + 1 until n) {
                    ans = ans.coerceAtLeast(products[i] * products[j] * products[k])
                }
            }
        }
        return ans
    }
}

/**
 * Time complexity : O(n log n). Sorting the nums array takes n\log n n log n time.
 * Space complexity : O(log n). Sorting takes O(\log n)O(log n) space.
 */
class MaximumProductSorting : AbstractMaximumProductStrategy {
    override operator fun invoke(products: IntArray): Int {
        products.sort()
        val n = products.size
        return (products.first() * products[1] * products.last())
            .coerceAtLeast(products.last() * products[n - 2] * products[n - 3])
    }
}

/**
 * Time complexity : O(n). Only one iteration over the nums array of length nn is required.
 * Space complexity : O(1). Constant extra space is used.
 */
class MaximumProductSingleScan : AbstractMaximumProductStrategy {
    override operator fun invoke(products: IntArray): Int {
        var max1 = Int.MIN_VALUE
        var max2 = Int.MIN_VALUE
        var max3 = Int.MIN_VALUE
        var min1 = Int.MAX_VALUE
        var min2 = Int.MAX_VALUE
        for (product in products) {
            when {
                product > max1 -> {
                    max3 = max2
                    max2 = max1
                    max1 = product
                }

                product > max2 -> {
                    max3 = max2
                    max2 = product
                }

                product > max3 -> {
                    max3 = product
                }
            }
            if (product < min1) {
                min2 = min1
                min1 = product
            } else if (product < min2) {
                min2 = product
            }
        }
        return (max1 * max2 * max3).coerceAtLeast(max1 * min1 * min2)
    }
}
