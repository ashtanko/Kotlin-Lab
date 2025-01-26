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
 * 137. Single Number II
 * @see <a href="https://leetcode.com/problems/single-number-ii/">Source</a>
 */
fun interface SingleNumber2 {
    fun singleNumber(nums: IntArray): Int
}

class SingleNumber2Bitwise : SingleNumber2 {

    companion object {
        private const val LIMIT = 31
    }

    override fun singleNumber(nums: IntArray): Int {
        var ans = 0
        for (i in 0..LIMIT) {
            var sum = 0
            for (element in nums) {
                if (element shr i and 1 == 1) {
                    sum++
                    sum %= 3
                }
            }
            if (sum != 0) {
                ans = ans or (sum shl i)
            }
        }
        return ans
    }
}
