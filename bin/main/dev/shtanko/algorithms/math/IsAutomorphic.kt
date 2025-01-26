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

@file:JvmName("AutomorphicNumber")

package dev.shtanko.algorithms.math

import dev.shtanko.algorithms.DECIMAL
import kotlin.math.pow

/**
 * A number is said to be an Automorphic, if it is present in the last digit(s)
 * of its square. Example- Let the number be 25, its square is 625. Since,
 * 25(The input number) is present in the last two digits of its square(625), it
 * is an Automorphic Number.
 * @returns true if the number is an Automorphic number otherwise return false
 */
fun Int.isAutomorphic(): Boolean {
    val m: Int
    val r: Int
    var k: Int
    var c = 0

    /**
     * m = Temporary variable to store a copy of the number entered by the
     * user. n = The number entered by the user c = Count the digits of the
     * number entered by user. p = To calculate the square of the number. k
     * = Support variable to count the digits of the number
     */
    var num = this
    m = num
    val p: Int = m * m // calculating square of the number

    do {
        k = num / DECIMAL
        c += 1 // counting the digits of the number entered by user.
        num = k
    } while (num != 0)
    /**
     * m = Temporary variable to store a copy of the number entered by the
     * user. n = The number entered by the user c = Count the digits of the
     * number entered by user. p = To calculate the square of the number. k
     * = Support variable to count the digits of the number
     */
    val s: Double = 10.0.pow(c.toDouble())
    r = p % s.toInt()
    // checking if the original number entered is present at the end of the square
    return m == r
}
