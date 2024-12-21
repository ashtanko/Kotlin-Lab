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
 * 1611. Minimum One Bit Operations to Make Integers Zero
 * https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero
 */
fun interface MinOneBitOperations {
    operator fun invoke(n: Int): Int
}

class MinOneBitOperationsRecursion : MinOneBitOperations {
    /**
     * Calculates the minimum number of one-bit operations required to make an integer zero.
     *
     * @param n The input integer.
     * @return The minimum number of one-bit operations.
     */
    override fun invoke(n: Int): Int {
        if (n == 0) {
            return 0
        }

        var k = 0
        var curr = 1
        while (curr * 2 <= n) {
            curr *= 2
            k++
        }

        return (1 shl k + 1) - 1 - invoke(n xor curr)
    }
}

class MinOneBitOperationsIteration : MinOneBitOperations {
    /**
     * Computes the minimum number of one-bit operations required to make an integer zero.
     *
     * @param n The integer to perform the operations on.
     * @return The minimum number of one-bit operations required.
     */
    override fun invoke(n: Int): Int {
        var ans = 0
        var k = 0
        var mask = 1

        while (mask <= n) {
            if (n and mask != 0) {
                ans = (1 shl k + 1) - 1 - ans
            }

            mask = mask shl 1
            k++
        }

        return ans
    }
}

class MinOneBitOperationsGrayCode : MinOneBitOperations {
    private companion object {
        const val SHIFT_16 = 16
        const val SHIFT_8 = 8
        const val SHIFT_4 = 4
        const val SHIFT_2 = 2
        const val SHIFT_1 = 1
    }

    override fun invoke(n: Int): Int {
        return calculateXOR(n, SHIFT_16, SHIFT_8, SHIFT_4, SHIFT_2, SHIFT_1)
    }

    /**
     * Calculates the XOR of a given number with a series of shifts.
     *
     * @param n The input number for which the XOR is to be calculated.
     * @param shifts The array of shift values.
     * @return The result of the XOR operation.
     */
    private fun calculateXOR(n: Int, vararg shifts: Int): Int {
        var result = n
        for (shift in shifts) {
            result = result xor (result shr shift)
        }
        return result
    }
}
