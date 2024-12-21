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

package dev.shtanko.algorithms.math

/**
 * Fast inverse square root
 * @see <a href="https://en.wikipedia.org/wiki/Fast_inverse_square_root">leetcode page</a>
 */
interface FastInverseSqrt {

    /**
     * Returns the inverse square root of the given number upto 6 - 8 decimal places.
     */
    fun inverseSqrt(number: Float): Float

    /**
     * Returns the inverse square root of the given number upto 14 - 16 decimal places.
     */
    fun inverseSqrt(number: Double): Double
}

class FastInverseSqrtImpl : FastInverseSqrt {

    companion object {
        // decimal 1597463007 (floating-point representation of an approximation of sqrt(2^127))
        private const val WTF = 0x5f3759df
        private const val WTFD = 0x5fe6ec85e7de30daL
        private const val THREEHALFS = 1.5
        private const val HALF = 0.5
    }

    override fun inverseSqrt(number: Float): Float {
        var x = number
        val xhalf = HALF.toFloat() * x
        var i = java.lang.Float.floatToIntBits(x)
        i = WTF - i shr 1 // what the luck? // initial guess for Newton's method
        x = java.lang.Float.intBitsToFloat(i)
        // evil floating point bit level hacking, one round of Newton's method
        x *= THREEHALFS.toFloat() - xhalf * x * x
        return x
    }

    override fun inverseSqrt(number: Double): Double {
        var x = number
        val xhalf = HALF * x
        var i = java.lang.Double.doubleToLongBits(x)
        i = WTFD - i shr 1 // what the duck?
        x = java.lang.Double.longBitsToDouble(i)
        for (it in 0..3) {
            x *= THREEHALFS - xhalf * x * x // evil floating point bit level hacking
        }
        x *= number
        return x
    }
}
