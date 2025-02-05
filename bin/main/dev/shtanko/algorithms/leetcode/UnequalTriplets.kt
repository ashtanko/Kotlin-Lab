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

/**
 * 2475. Number of Unequal Triplets in Array
 * @see <a href="https://leetcode.com/problems/number-of-unequal-triplets-in-array/">Source</a>
 */
fun interface UnequalTriplets {
    operator fun invoke(nums: IntArray): Int
}

class UnequalTripletsOnePass : UnequalTriplets {
    override operator fun invoke(nums: IntArray): Int {
        var trips = 0
        var pairs = 0
        val count = IntArray(LIMIT)
        for (i in nums.indices) {
            trips += pairs - count[nums[i]] * (i - count[nums[i]])
            pairs += i - count[nums[i]]
            count[nums[i]] += 1
        }
        return trips
    }

    companion object {
        private const val LIMIT = 1001
    }
}
