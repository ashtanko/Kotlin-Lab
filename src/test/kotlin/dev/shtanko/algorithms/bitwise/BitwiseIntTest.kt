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

package dev.shtanko.algorithms.bitwise

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BitwiseIntTest {
    @Test
    fun `test toggleBit when toggling a bit`() {
        assertEquals(4, 5.toggleBit(0)) // 5 (0101) → 4 (0100)
        assertEquals(7, 5.toggleBit(1)) // 5 (0101) → 7 (0111)
        assertEquals(5, 4.toggleBit(0)) // 4 (0100) → 5 (0101)
    }

    @Test
    fun `test countOnes when counting 1 bits`() {
        assertEquals(2, 5.countOnes()) // 5 (0101) has 2 1-bits
        assertEquals(3, 7.countOnes()) // 7 (0111) has 3 1-bits
        assertEquals(1, 8.countOnes()) // 8 (1000) has 1 1-bit
        assertEquals(0, 0.countOnes()) // 0 (0000) has 0 1-bits
        assertEquals(31, Int.MAX_VALUE.countOnes()) // Maximum int has 31 1-bits
    }

    @Test
    fun `test isPowerOfTwo when checking powers of two`() {
        assertEquals(true, 1.isPowerOfTwo()) // 1 is a power of two (2^0)
        assertEquals(true, 2.isPowerOfTwo()) // 2 is a power of two (2^1)
        assertEquals(true, 4.isPowerOfTwo()) // 4 is a power of two (2^2)
        assertEquals(true, 8.isPowerOfTwo()) // 8 is a power of two (2^3)
        assertEquals(true, 16.isPowerOfTwo()) // 16 is a power of two (2^4)
        assertEquals(false, 3.isPowerOfTwo()) // 3 is not a power of two
        assertEquals(false, 6.isPowerOfTwo()) // 6 is not a power of two
        assertEquals(false, 18.isPowerOfTwo()) // 18 is not a power of two
    }

    @Test
    fun `test swap when swapping values in a pair`() {
        assertEquals(Pair(2, 3), Pair(3, 2).swap()) // Swapping (3, 2) → (2, 3)
        assertEquals(Pair(1, 5), Pair(5, 1).swap()) // Swapping (5, 1) → (1, 5)
        assertEquals(Pair(0, 0), Pair(0, 0).swap()) // Swapping (0, 0) → (0, 0)
        assertEquals(Pair(-2, -3), Pair(-3, -2).swap()) // Swapping (-3, -2) → (-2, -3)
    }

    @Test
    fun `test rightmostOneBit when getting the rightmost 1 bit`() {
        assertEquals(4, 12.rightmostOneBit()) // 12 (1100) → 4 (0100)
        assertEquals(2, 6.rightmostOneBit()) // 6 (0110) → 2 (0010)
        assertEquals(8, 24.rightmostOneBit()) // 24 (11000) → 8 (01000)
        assertEquals(1, 1.rightmostOneBit()) // 1 (0001) → 1 (0001)
        assertEquals(0, 0.rightmostOneBit()) // 0 (0000) → 0 (0000)
    }

    @Test
    fun `test leftShift when shifting bits to the left`() {
        assertEquals(16, 4.leftShift(2)) // 4 (0100) << 2 → 16 (10000)
        assertEquals(64, 8.leftShift(3)) // 8 (1000) << 3 → 64 (100000)
        assertEquals(64, 1.leftShift(6)) // 1 (0001) << 6 → 64 (1000000)
        assertEquals(1, 1.leftShift(32)) // 1 (0001) << 32 → 1 (0000)
    }

    @Test
    fun `test rightShift when shifting bits to the right`() {
        assertEquals(2, 8.rightShift(2)) // 8 (1000) >> 2 → 2 (0010)
        assertEquals(1, 8.rightShift(3)) // 8 (1000) >> 3 → 1 (0001)
        assertEquals(0, 8.rightShift(4)) // 8 (1000) >> 4 → 0 (0000)
        assertEquals(0, 1.rightShift(1)) // 1 (0001) >> 1 → 0 (0000)
    }

    @Test
    fun `test clearBit when clearing a bit at the specified position`() {
        assertEquals(14, 15.clearBit(0)) // 15 (1111) → 14 (1100)
        assertEquals(13, 15.clearBit(1)) // 15 (1111) → 13 (1101)
        assertEquals(11, 15.clearBit(2)) // 15 (1111) → 1 (1001)
        assertEquals(7, 15.clearBit(3)) // 15 (1111) → 7 (0111)
        assertEquals(0, 1.clearBit(0)) // 1 (0001) → 0 (0000)
    }

    @Test
    fun `test invertBits when inverting all bits`() {
        assertEquals(-6, 5.invertBits()) // 5 (0101) → -6 (1010)
        assertEquals(-1, 0.invertBits()) // 0 (0000) → -1 (1111)
        assertEquals(-8, 7.invertBits()) // 7 (0111) → -8 (1000)
        assertEquals(2, (-1).invertBits()) // -1 (1111) → 2 (0000)
    }
}
