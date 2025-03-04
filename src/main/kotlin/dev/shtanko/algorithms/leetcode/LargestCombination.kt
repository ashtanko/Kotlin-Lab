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
 * 2275. Largest Combination With Bitwise AND Greater Than Zero
 * @see <a href="https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/">Source</a>
 */
@Medium("https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero")
fun interface LargestCombination {
    operator fun invoke(candidates: IntArray): Int
}

@Bitwise
class LargestCombinationBitCount : LargestCombination {
    override fun invoke(candidates: IntArray): Int {
        var maxCount = 0 // Variable to track the maximum count of set bits.
        for (i in 0 until 24) {
            var count = 0 // Count of numbers with the i-th bit set.
            for (num in candidates) {
                if ((num and (1 shl i)) != 0) { // Check if the i-th bit is set.
                    count++
                }
            }
            maxCount = maxOf(maxCount, count) // Update the maximum count.
        }
        return maxCount
    }
}
