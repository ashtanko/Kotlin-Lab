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
 * 896. Monotonic Array
 * @see <a href="https://leetcode.com/problems/sort-array-by-parity">Source</a>
 */
fun interface MonotonicArray {
    operator fun invoke(nums: IntArray): Boolean
}

class MonotonicArrayOnePass : MonotonicArray {
    override fun invoke(nums: IntArray): Boolean {
        var inc = true
        var dec = true
        for (i in 1 until nums.size) {
            inc = inc and (nums[i - 1] <= nums[i])
            dec = dec and (nums[i - 1] >= nums[i])
        }
        return inc || dec
    }
}
