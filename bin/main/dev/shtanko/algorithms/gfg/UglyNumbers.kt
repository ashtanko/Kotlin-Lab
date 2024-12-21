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

package dev.shtanko.algorithms.gfg

import kotlin.math.min

/**
 * Ugly Numbers
 * https://www.geeksforgeeks.org/ugly-numbers/
 * Ugly numbers are numbers whose only prime factors are 2, 3 or 5.
 * The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers. By convention, 1 is included.
 * Given a number n, the task is to find n’th Ugly number.
 */
fun interface UglyNumbers {
    operator fun invoke(n: Int): Int
}

class UglyNumbersBruteForce : UglyNumbers {
    override fun invoke(n: Int): Int {
        val ugly = IntArray(n)
        var i2 = 0
        var i3 = 0
        var i5 = 0
        var nextMultipleOf2 = 2
        var nextMultipleOf3 = 3
        var nextMultipleOf5 = 5
        var nextUglyNo = 1

        ugly[0] = 1

        for (i in 1 until n) {
            nextUglyNo = min(
                nextMultipleOf2,
                min(
                    nextMultipleOf3,
                    nextMultipleOf5,
                ),
            )
            ugly[i] = nextUglyNo
            if (nextUglyNo == nextMultipleOf2) {
                i2 += 1
                nextMultipleOf2 = ugly[i2] * 2
            }
            if (nextUglyNo == nextMultipleOf3) {
                i3 += 1
                nextMultipleOf3 = ugly[i3] * 3
            }
            if (nextUglyNo == nextMultipleOf5) {
                i5 += 1
                nextMultipleOf5 = ugly[i5] * 5
            }
        }

        return nextUglyNo
    }
}
