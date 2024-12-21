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

import dev.shtanko.algorithms.DECIMAL
import kotlin.math.pow

private const val FIRST_SIX = 6
private const val CD = 3

fun interface Maximum69Number {
    operator fun invoke(num: Int): Int
}

/**
 * Approach 1: Convert the integer to an iterable object
 */
class Max69NumberIterable : Maximum69Number {
    override operator fun invoke(num: Int): Int {
        // Convert the input 'num' to a string builder 'numSB'.
        val numSB = StringBuilder()
        numSB.append(num)

        // Iterate over the string builder (from high to low).
        for (i in numSB.indices) {
            // If we find the first '6', replace it with '9' and break the loop.
            if (numSB[i] == '6') {
                numSB.setCharAt(i, '9')
                break
            }
        }

        // Convert the modified string builder to integer and return it.
        return numSB.toString().toInt()
    }
}

/**
 * Approach 2: Use built-in function
 */
class Max69NumberBuildIn : Maximum69Number {
    override operator fun invoke(num: Int): Int {
        // Use the built-in function to replace the first '6' with '9'.
        // Return the integer converted from the modified 'numString'.
        return "$num".replaceFirst("6".toRegex(), "9").toInt()
    }
}

/**
 * Approach 3: Check the remainder
 */
class Max69NumberRem : Maximum69Number {
    override operator fun invoke(num: Int): Int {
        var firstSix = -1
        var number: Int = num
        var i = 0
        while (number > 0) {
            if (number % DECIMAL == FIRST_SIX) {
                firstSix = i
            }
            number /= DECIMAL
            i++
        }
        return num + CD * DECIMAL.toDouble().pow(firstSix.toDouble()).toInt()
    }
}
