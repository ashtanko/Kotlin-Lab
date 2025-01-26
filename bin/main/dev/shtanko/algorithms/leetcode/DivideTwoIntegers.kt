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

import kotlin.math.abs

private const val BITS = 31

/**
 * 29. Divide Two Integers
 * @see <a href="https://leetcode.com/problems/divide-two-integers/">Source</a>
 */
fun interface DivideTwoIntegers {
    fun divide(dividend: Int, divisor: Int): Int
}

/**
 * This solution has O(logN^2) time complexity.
 */
@Suppress("INTEGER_OVERFLOW")
class DivideIntegersBitShifting : DivideTwoIntegers {
    override fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == 1 shl BITS && divisor == -1) return 1.shl(BITS).minus(1)
        var a: Int = abs(dividend)
        val b: Int = abs(divisor)
        var res = 0
        var x: Int
        while (a - b >= 0) {
            x = 0
            while (a - (b shl x shl 1) >= 0) {
                x++
            }
            res += 1 shl x
            a -= b shl x
        }
        return if (dividend > 0 == divisor > 0) res else -res
    }
}

/**
 * Solution 2
 * Another solution is also O(32)
 */
@Suppress("INTEGER_OVERFLOW")
class DivideIntegersBitShifting2 : DivideTwoIntegers {
    override fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == 1 shl BITS && divisor == -1) return (1 shl BITS) - 1
        var a: Int = abs(dividend)
        val b: Int = abs(divisor)
        var res = 0
        for (x in BITS downTo 0) if ((a ushr x) - b >= 0) {
            res += 1 shl x
            a -= b shl x
        }
        return if (dividend > 0 == divisor > 0) res else -res
    }
}
