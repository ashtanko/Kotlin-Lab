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

/**
 * Determines whether the number is odd by iterating through numbers from 0 to `n`
 * and checking if any of them are odd.
 *
 * The function uses a bitwise AND operation (`& 0x1`) to check if a number is odd.
 * If any number in the range from 0 to `n` is odd, it returns `true`, otherwise `false`.
 *
 * @param n The upper bound of the range (inclusive).
 * @return `true` if any number in the range from 0 to `n` is odd, otherwise `false`.
 */
@Bitwise
fun Int.evenOrOdd(): Boolean {
    // Iterate through all numbers from 0 to this number
    for (number in 0..this) {
        // Check if the current number is odd by using bitwise AND with 1
        if (number and 0x1 != 0) {
            // Return true if an odd number is found
            return true
        }
    }
    // Return false if no odd number is found in the range
    return false
}
