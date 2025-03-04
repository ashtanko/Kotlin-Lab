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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.Bitwise
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 3133. Minimum Array End
 * @see <a href="https://leetcode.com/problems/minimum-array-end/">Source</a>
 */
@Medium("https://leetcode.com/problems/minimum-array-end")
fun interface MinArrayEnd {
    operator fun invoke(arraySize: Int, initialValue: Int): Long
}

@Bitwise
class MinArrayEndBitmask : MinArrayEnd {
    override fun invoke(arraySize: Int, initialValue: Int): Long {
        var result = initialValue.toLong()
        var bitMask = 1L
        var remainingBits = arraySize - 1 // Reducing arraySize by 1 to exclude initialValue from the iteration

        // Step 1: Iterate over each bit position with bitMask starting at 1 and shifting left
        while (remainingBits > 0) {
            // Step 2: If the corresponding bit in initialValue is 0
            if ((bitMask and initialValue.toLong()) == 0L) {
                // Set the bit in result based on the least significant bit of remainingBits
                result = result or ((remainingBits and 1) * bitMask)
                // Shift remainingBits to the right by 1 to process the next bit
                remainingBits = remainingBits shr 1
            }
            bitMask = bitMask shl 1
        }

        return result
    }
}
