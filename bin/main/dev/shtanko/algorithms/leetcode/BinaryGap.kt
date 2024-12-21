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

import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.OnePass
import dev.shtanko.algorithms.annotations.level.Easy

private const val MAX_SIZE = 32

/**
 * 868. Binary Gap
 * @see <a href="https://leetcode.com/problems/binary-gap/">Source</a>
 */
@Easy("https://leetcode.com/problems/binary-gap")
fun interface BinaryGap {
    operator fun invoke(num: Int): Int
}

/**
 * Approach 1: Store Indexes
 * Time Complexity: O(log N).
 * Space Complexity: O(log N).
 */
@Iterative
class BGStoreIndexes : BinaryGap {

    override fun invoke(num: Int): Int {
        val indexes = IntArray(MAX_SIZE)
        var indexCount = 0
        for (i in 0 until MAX_SIZE) {
            if (num shr i and 1 != 0) {
                indexes[indexCount++] = i
            }
        }
        var maxGap = 0
        for (i in 0 until indexCount - 1) maxGap = maxGap.coerceAtLeast(indexes[i + 1] - indexes[i])
        return maxGap
    }
}

/**
 * Approach 2: One Pass
 * Time Complexity: O(log N).
 * Space Complexity: O(1).
 */
@OnePass
class BGOnePass : BinaryGap {
    override fun invoke(num: Int): Int {
        var last = -1
        var ans = 0
        for (i in 0 until MAX_SIZE) if (num shr i and 1 > 0) {
            if (last >= 0) ans = ans.coerceAtLeast(i - last)
            last = i
        }

        return ans
    }
}

class BGOther : BinaryGap {
    override fun invoke(num: Int): Int {
        var max = 0
        var pos = 0
        var lastPos = -1
        var changed = num
        while (changed != 0) {
            pos++
            if (changed and 1 == 1) {
                if (lastPos != -1) {
                    max = max.coerceAtLeast(pos - lastPos)
                }
                lastPos = pos
            }
            changed = changed shr 1
        }
        return max
    }
}
