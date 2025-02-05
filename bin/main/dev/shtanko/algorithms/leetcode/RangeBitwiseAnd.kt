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
 * 201. Bitwise AND of Numbers Range
 * @see <a href="https://leetcode.com/problems/bitwise-and-of-numbers-range">Source</a>
 */
fun interface RangeBitwiseAnd {
    operator fun invoke(left: Int, right: Int): Int
}

class RangeBitwiseAndBitwise : RangeBitwiseAnd {
    override fun invoke(left: Int, right: Int): Int {
        var left0 = left
        var mask = 1
        while (left0 > 0) {
            while (mask and left0 == 0) mask = mask shl 1

            val sum = left0 + mask
            if (sum < 0 || right < sum) return left0
            left0 = left0 and sum
        }
        return 0
    }
}
