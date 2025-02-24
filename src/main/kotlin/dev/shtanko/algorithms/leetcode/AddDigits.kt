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
import dev.shtanko.algorithms.annotations.Math
import dev.shtanko.algorithms.annotations.StraightForward
import dev.shtanko.algorithms.annotations.level.Easy

private const val MAX = 9

/**
 * 258. Add Digits
 * @see <a href="https://leetcode.com/problems/add-digits/">Source</a>
 */
@Easy(link = "https://leetcode.com/problems/add-digits")
fun interface AddDigits {
    operator fun invoke(num: Int): Int
}

/**
 * This class represents a straightforward implementation of the digital root calculation algorithm.
 * It implements the [AddDigits] interface.
 *
 * @property DECIMAL The base number system to be used for the calculations.
 * @property MAX The maximum value that can be present in the result of the digital root calculation.
 */
@StraightForward
class AddDigitsStraightForward : AddDigits {
    /**
     * Calculates the digital root of a given number.
     *
     * @param num The number for which the digital root needs to be calculated.
     * @return The digital root of the given number.
     */
    override operator fun invoke(num: Int): Int {
        var digitalRoot = 0
        var digits = num
        while (digits > 0) {
            digitalRoot += digits % DECIMAL
            digits /= DECIMAL
            if (digits == 0 && digitalRoot > MAX) {
                digits = digitalRoot
                digitalRoot = 0
            }
        }
        return digitalRoot
    }
}

/**
 * Class representing a mathematical operation to add the digits of a given number.
 * Implements the AddDigits interface.
 *
 * @param num The number to be operated on.
 */
@Math
class AddDigitsMath : AddDigits {
    /**
     * Calculates the remainder of dividing the given number by the maximum value.
     *
     * @param num The number to be operated on.
     * @return The remainder of dividing the number by the maximum value.
     */
    override operator fun invoke(num: Int): Int {
        if (num == 0) return 0
        if (num % MAX == 0) return MAX
        return num % MAX
    }
}

/**
 * Class representing the AddDigitsDigitalRoot strategy for adding digits.
 */
@Math
class AddDigitsDigitalRoot : AddDigits {
    /**
     * Invokes the `AddDigitsDigitalRoot` function with the given input number.
     *
     * @param num The input number.
     * @return The result of the invocation.
     */
    override operator fun invoke(num: Int): Int {
        return if (num == 0) 0 else 1 + (num - 1) % MAX
    }
}
