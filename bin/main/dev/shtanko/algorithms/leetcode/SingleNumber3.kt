/*
 * Copyright 2024 Oleksii Shtanko
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
 * 260. Single Number III
 * @see <a href="https://leetcode.com/problems/single-number-iii/">Source</a>
 */
fun interface SingleNumber3 {
    operator fun invoke(nums: IntArray): IntArray
}

class SingleNumber3Bitwise : SingleNumber3 {
    override fun invoke(nums: IntArray): IntArray {
        var bitmask = 0
        for (num in nums) {
            bitmask = bitmask xor num
        }
        val diff = bitmask and -bitmask
        var x = 0
        for (num in nums) {
            if (num and diff != 0) {
                x = x xor num
            }
        }
        return intArrayOf(x, bitmask xor x)
    }
}
