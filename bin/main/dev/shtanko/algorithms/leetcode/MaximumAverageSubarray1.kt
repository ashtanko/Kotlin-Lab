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

import java.lang.Double.NaN

fun interface FindMaxAverage {
    operator fun invoke(nums: IntArray, k: Int): Double
}

class FindMaxAverage1 : FindMaxAverage {
    override operator fun invoke(nums: IntArray, k: Int): Double {
        if (nums.isEmpty()) return NaN
        val sum = IntArray(nums.size)
        sum[0] = nums[0]
        for (i in 1 until nums.size) sum[i] = sum[i - 1] + nums[i]
        var res = sum[k - 1] * 1.0 / k
        for (i in k until nums.size) {
            res = res.coerceAtLeast((sum[i] - sum[i - k]) * 1.0 / k)
        }
        return res
    }
}

class FindMaxAverage2 : FindMaxAverage {
    override operator fun invoke(nums: IntArray, k: Int): Double {
        var sum = 0.0
        for (i in 0 until k) sum += nums[i]
        var res = sum
        for (i in k until nums.size) {
            sum += nums[i] - nums[i - k].toDouble()
            res = res.coerceAtLeast(sum)
        }
        return res / k
    }
}
