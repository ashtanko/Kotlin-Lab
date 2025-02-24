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

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1822. Sign of the Product of an Array
 * @see <a href="https://leetcode.com/problems/sign-of-the-product-of-an-array/">Source</a>
 */
@Easy("https://leetcode.com/problems/sign-of-the-product-of-an-array")
fun interface ArraySign {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Counting Negative Numbers
 */
@Iterative
class ArraySignCountingNegativeNumbers : ArraySign {
    override operator fun invoke(nums: IntArray): Int {
        var countNegativeNumbers = 0
        for (num in nums) {
            if (num == 0) {
                return 0
            }
            if (num < 0) {
                countNegativeNumbers++
            }
        }

        return if (countNegativeNumbers % 2 == 0) 1 else -1
    }
}

/**
 * Approach 2: Tracking the Sign of the Product
 */
@Iterative
class ArraySignTracking : ArraySign {
    override operator fun invoke(nums: IntArray): Int {
        var sign = 1
        for (num in nums) {
            if (num == 0) {
                return 0
            }
            if (num < 0) {
                sign *= -1
            }
        }
        return sign
    }
}

/**
 * Approach 3: Tracking the Sign of the Product
 */
@Iterative
class ArraySignSimple : ArraySign {
    override operator fun invoke(nums: IntArray): Int {
        var sign = 1
        for (n in nums) {
            if (n == 0) {
                return 0
            }
            if (n < 0) {
                sign = -sign
            }
        }
        return sign
    }
}
