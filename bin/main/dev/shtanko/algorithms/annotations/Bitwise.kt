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

package dev.shtanko.algorithms.annotations

/**
 * The Bitwise approach leverages bit-level operations to efficiently solve problems related to binary representations,
 * logical operations, and manipulations of integers. It is widely used in algorithms requiring low-level optimization
 * or efficient handling of binary data.
 *
 * # Common Bitwise Operators
 *
 * * `&` (AND): Performs a bitwise AND operation, where each bit is set to 1 if both corresponding bits are 1.
 * * `|` (OR): Performs a bitwise OR operation, where each bit is set to 1 if at least one of the corresponding bits
 * is 1.
 * * `^` (XOR): Performs a bitwise XOR operation, where each bit is set to 1 if the corresponding bits are different.
 * * `~` (NOT): Inverts all the bits of a number.
 * * `<<` (Left Shift): Shifts all bits of a number to the left by a specified number of positions.
 * * `>>` (Right Shift): Shifts all bits of a number to the right by a specified number of positions.
 *
 * # Applications
 *
 * 1: Checking if a Number is a Power of 2:
 * Example:
 * ```kotlin
 * fun isPowerOfTwo(n: Int): Boolean {
 *     return n > 0 && (n and (n - 1)) == 0
 * }
 * ```
 *
 * 2: Counting Set Bits (Hamming Weight):
 * Example:
 * ```kotlin
 * fun countSetBits(n: Int): Int {
 *     var count = 0
 *     var num = n
 *     while (num > 0) {
 *         count += num and 1
 *         num = num shr 1
 *     }
 *     return count
 * }
 * ```
 *
 * 3: Swapping Two Numbers Without a Temporary Variable:
 * Example:
 * ```kotlin
 * fun swap(a: Int, b: Int): Pair<Int, Int> {
 *     var x = a
 *     var y = b
 *     x = x xor y
 *     y = x xor y
 *     x = x xor y
 *     return Pair(x, y)
 * }
 * ```
 *
 * 4: Finding the Single Non-Repeated Element in an Array:
 * Example:
 * ```kotlin
 * fun findSingle(nums: IntArray): Int {
 *     var result = 0
 *     for (num in nums) {
 *         result = result xor num
 *     }
 *     return result
 * }
 * ```
 *
 * # Advantages:
 * * Efficiency: Bitwise operations are extremely fast and operate directly on binary representations.
 * * Memory Optimization: Bitwise techniques often allow for solving problems with minimal memory overhead.
 *
 * # Considerations:
 * * Complexity: Understanding and implementing bitwise logic can be challenging for complex problems.
 * * Limited Scope: Bitwise operations are not always intuitive for general-purpose algorithms.
 *
 * The Bitwise approach is indispensable for low-level programming, competitive programming, and optimization scenarios.
 * It provides a powerful toolkit for solving problems that involve binary manipulation and logical operations.
 *
 * @property info An optional string that provides additional information about the bitwise algorithm implementation
 * or usage.
 * @constructor Creates a new Bitwise annotation.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Bitwise(val info: String = "")
