/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.bitwise

import dev.shtanko.algorithms.annotations.Bitwise

// responsible for the fast approximation
// The "magic number" used for the fast approximation (derived empirically).
// This constant helps adjust the bits in such a way that a good approximation of the inverse square root is
// produced.
private const val FAST_APPROXIMATION = 0x5f3759df

@Bitwise
@Suppress("MagicNumber")
fun fastInverseSquareRoot(number: Float): Float {
    var bitRepresentation: Long
    // halfNumber holds half the value of the original number for use in the approximation formula.
    val halfNumber = number * 0.5f

    // initialApproximation starts as the original number and will be adjusted through the approximation.
    var initialApproximation = number

    // Convert the float number to an integer representation by getting its bits.
    // We use the Java method `floatToIntBits()` to get the raw bits of the float.
    bitRepresentation = java.lang.Float.floatToIntBits(initialApproximation).toLong()

    // Apply the magic number and perform a bitwise right shift to get the approximation.
    // This step adjusts the bits to give a starting point for the inverse square root calculation.
    bitRepresentation = FAST_APPROXIMATION - bitRepresentation.shr(1)

    // Convert the bits back to a float.
    // This is where the magic happens, turning the bitwise manipulation into a floating-point approximation.
    initialApproximation = java.lang.Float.intBitsToFloat(bitRepresentation.toInt())

    // Refine the approximation using the formula: y = y * (1.5 - (halfNumber * y * y))
    // This step further improves the approximation using a few iterations of multiplication.
    initialApproximation *= 1.5f - halfNumber * initialApproximation * initialApproximation

    // Return the final approximation of the inverse square root.
    return initialApproximation
}
