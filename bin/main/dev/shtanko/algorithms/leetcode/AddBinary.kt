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

import dev.shtanko.algorithms.annotations.Bitwise
import dev.shtanko.algorithms.annotations.level.Easy
import java.math.BigInteger

/**
 * Given two binary strings a and b, return their sum as a binary string.
 * @see <a href="https://leetcode.com/problems/add-binary">Source</a>
 */
@Easy(link = "https://leetcode.com/problems/add-binary")
fun interface AddBinary {
    operator fun invoke(a: String, b: String): String
}

/**
 * Time complexity: O(max(N,M)), where N and M are lengths of the input strings a and b.
 * Space complexity: O(max(N,M)) to keep the answer.
 */
@Bitwise
class AddBinaryBitByBitComputation : AddBinary {
    /**
     * Adds two binary strings and returns their sum as a binary string.
     *
     * @param a the first binary string
     * @param b the second binary string
     * @return the sum of the binary strings as a binary string
     */
    override operator fun invoke(a: String, b: String): String {
        val sb = StringBuilder()
        var i: Int = a.length - 1
        var j: Int = b.length - 1
        var carry = 0
        while (i >= 0 || j >= 0) {
            var sum = carry
            if (j >= 0) sum += b[j--] - '0'
            if (i >= 0) sum += a[i--] - '0'
            sb.append(sum % 2)
            carry = sum / 2
        }
        if (carry != 0) sb.append(carry)
        return sb.reverse().toString()
    }
}

/**
 * Time complexity : O(N+M), where N and M are lengths of the input strings a and b.
 * Space complexity: O(max(N,M)) to keep the answer.
 */
@Bitwise
class AddBinaryBitManipulation : AddBinary {
    /**
     * Performs binary addition of two strings and returns the sum as a binary string.
     *
     * @param firstBinary The first binary string.
     * @param secondBinary The second binary string.
     * @return The sum of the two binary strings as a binary string.
     */
    override operator fun invoke(firstBinary: String, secondBinary: String): String {
        if (firstBinary.isEmpty() || secondBinary.isEmpty()) return ""
        val firstOperand = BigInteger(firstBinary, 2)
        val secondOperand = BigInteger(secondBinary, 2)
        val zero = BigInteger("0", 2)

        return addBinary(firstOperand, secondOperand, zero).toString(2)
    }

    private fun addBinary(
        firstOperand: BigInteger,
        secondOperand: BigInteger,
        zero: BigInteger,
    ): BigInteger {
        var tempResult: BigInteger
        var carryResult: BigInteger
        var x = firstOperand
        var y = secondOperand
        while (y.compareTo(zero) != 0) {
            tempResult = x.xor(y)
            carryResult = x.and(y).shiftLeft(1)
            x = tempResult
            y = carryResult
        }
        return x
    }
}
