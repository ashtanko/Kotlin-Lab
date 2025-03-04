/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Bitwise

package dev.shtanko.algorithms.bitwise

import dev.shtanko.algorithms.annotations.Bitwise

/**
 * Toggles the bit at the specified position in an integer.
 *
 * @param position The position of the bit to toggle (0-based index).
 * @return The integer with the bit at the specified position toggled.
 *
 * Example:
 * ```
 * val num = 5 // 0101 in binary
 * val result = num.toggleBit(1) // result = 7 (0111 in binary)
 * ```
 */
fun Int.toggleBit(position: Int): Int {
    return this xor (1 shl position)
}

/**
 * Counts the number of '1' bits in the binary representation of an integer.
 *
 * @return The number of '1' bits in the binary representation of the integer.
 *
 * Example:
 * ```
 * val num = 7 // 0111 in binary
 * val count = num.countOnes() // count = 3
 * ```
 */
fun Int.countOnes(): Int {
    var count = 0
    var num = this
    while (num != 0) {
        count += num and 1
        num = num shr 1
    }
    return count
}

/**
 * Checks if an integer is a power of two.
 *
 * A number is a power of two if it has exactly one '1' bit in its binary representation.
 *
 * @return `true` if the integer is a power of two, otherwise `false`.
 *
 * Example:
 * ```
 * val num = 4
 * val isPower = num.isPowerOfTwo() // isPower = true
 * ```
 */
fun Int.isPowerOfTwo(): Boolean {
    return this > 0 && (this and (this - 1)) == 0
}

/**
 * Swaps the values of the two integers in the pair.
 *
 * @return A new pair with the values swapped.
 *
 * Example:
 * ```
 * val pair = 1 to 2
 * val swapped = pair.swap() // swapped = 2 to 1
 * ```
 */
fun Pair<Int, Int>.swap(): Pair<Int, Int> {
    var a = first
    var b = second
    a = a xor b
    b = a xor b
    a = a xor b
    return a to b
}

/**
 * Returns the value of the rightmost '1' bit in the integer.
 *
 * This is achieved by performing a bitwise AND with the negative value of the integer,
 * which isolates the rightmost set bit.
 *
 * @return The value of the rightmost '1' bit.
 *
 * Example:
 * ```
 * val num = 18 // 10010 in binary
 * val rightmostBit = num.rightmostOneBit() // rightmostBit = 2 (10 in binary)
 * ```
 */
fun Int.rightmostOneBit(): Int {
    return this and -this
}

/**
 * Shifts the bits of the integer to the left by a specified number of positions.
 *
 * @param positions The number of positions to shift the bits to the left.
 * @return The integer after performing the left shift operation.
 *
 * Example:
 * ```
 * val num = 3 // 0011 in binary
 * val shifted = num.leftShift(2) // shifted = 12 (1100 in binary)
 * ```
 */
fun Int.leftShift(positions: Int): Int {
    return this shl positions
}

/**
 * Shifts the bits of the integer to the right by a specified number of positions.
 *
 * @param positions The number of positions to shift the bits to the right.
 * @return The integer after performing the right shift operation.
 *
 * Example:
 * ```
 * val num = 12 // 1100 in binary
 * val shifted = num.rightShift(2) // shifted = 3 (0011 in binary)
 * ```
 */
fun Int.rightShift(positions: Int): Int {
    return this shr positions
}

/**
 * Clears (sets to 0) the bit at the specified position in the integer.
 *
 * This is done by using the bitwise AND operation with the inverted mask of the specified bit position.
 *
 * @param position The position of the bit to clear (0-based index).
 * @return The integer with the specified bit cleared.
 *
 * Example:
 * ```
 * val num = 14 // 1110 in binary
 * val cleared = num.clearBit(1) // cleared = 12 (1100 in binary)
 * ```
 */
fun Int.clearBit(position: Int): Int {
    return this and (1 shl position).inv()
}

/**
 * Inverts all the bits of the integer.
 *
 * This is done by performing the bitwise NOT operation (`inv()`), which flips all the bits.
 *
 * @return The integer with all bits inverted.
 *
 * Example:
 * ```
 * val num = 5 // 0101 in binary
 * val inverted = num.invertBits() // inverted = -6 (111...1010 in binary)
 * ```
 */
fun Int.invertBits(): Int {
    return inv()
}
