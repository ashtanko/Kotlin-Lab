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
 * 1318. Minimum Flips to Make a OR b Equal to c
 * @see <a href="https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/">Source</a>
 */
fun interface MinFlips {
    operator fun invoke(a: Int, b: Int, c: Int): Int
}

/**
 * Approach 1: Bit Manipulation
 */
class MinFlipsBitManipulation : MinFlips {
    override operator fun invoke(a: Int, b: Int, c: Int): Int {
        var answer = 0
        var a1 = a
        var b1 = b
        var c1 = c
        while ((a1 != 0) or (b1 != 0) or (c1 != 0)) {
            if (c1 and 1 == 1) {
                if (a1 and 1 == 0 && b1 and 1 == 0) {
                    answer++
                }
            } else {
                answer += (a1 and 1) + (b1 and 1)
            }
            a1 = a1 shr 1
            b1 = b1 shr 1
            c1 = c1 shr 1
        }

        return answer
    }
}

/**
 * Approach 2: Count the Number of Set Bits Using Built-in Function
 */
class MinFlipsSetBits : MinFlips {
    override operator fun invoke(a: Int, b: Int, c: Int): Int {
        return Integer.bitCount(a or b xor c) + Integer.bitCount(a and b and (a or b xor c))
    }
}
