/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.Bitwise
import dev.shtanko.algorithms.annotations.BruteForce
import kotlin.math.ln

/**
 * 1009. Complement of Base 10 Integer
 * @see <a href="https://leetcode.com/problems/complement-of-base-10-integer/">Source</a>
 */
@Bitwise
fun interface BitwiseComplement {
    operator fun invoke(n: Int): Int
}

/**
 * Approach 1: Flip Bit by Bit
 * Time Complexity: O(1), since we're doing not more than 32 iterations here.
 * Space Complexity: O(1).
 */
class BitwiseComplementFlipBit : BitwiseComplement {
    override operator fun invoke(n: Int): Int {
        if (n == 0) return 1
        var todo = n
        var bit = 1
        var c = n
        while (todo != 0) {
            c = c xor bit
            bit = bit shl 1
            todo = todo shr 1
        }
        return c
    }
}

/**
 * Approach 2: Compute Bit Length and Construct 1-bits Bitmask
 * Time Complexity: O(1).
 * Space Complexity: O(1).
 */
class BitwiseComplementBitmask : BitwiseComplement {
    override operator fun invoke(n: Int): Int {
        if (n == 0) return 1
        val l = ln(n.toDouble()).div(ln(2.0)).plus(1).toInt()
        val bitmask = 1.shl(l).minus(1)
        return bitmask xor n
    }
}

/**
 * Approach 3: Built-in Functions to Construct 1-bits Bitmask
 * Time Complexity: O(1), since we're doing not more than 32 iterations here.
 * Space Complexity: O(1).
 */
class BitwiseComplementBuiltInFunc : BitwiseComplement {
    override operator fun invoke(n: Int): Int {
        return if (n == 0) 1 else Integer.highestOneBit(n).shl(1).minus(n).minus(1)
    }
}

/**
 * Approach 4: highestOneBit OpenJDK algorithm from Hacker's Delight
 * Time Complexity: O(1).
 * Space Complexity: O(1).
 */
class HighestOneBit : BitwiseComplement {
    override operator fun invoke(n: Int): Int {
        if (n == 0) return 1
        var bitmask: Int = n
        bitmask = bitmask or bitmask.shr(BIT_COUNT_POW)
        bitmask = bitmask or bitmask.shr(BIT_COUNT_POW_1)
        bitmask = bitmask or bitmask.shr(BIT_COUNT_POW_2)
        bitmask = bitmask or bitmask.shr(BIT_COUNT_POW_3)
        bitmask = bitmask or bitmask.shr(BIT_COUNT_POW_4)
        // flip all bits
        return bitmask xor n
    }

    companion object {
        private const val BIT_COUNT_POW = 1
        private const val BIT_COUNT_POW_1 = 2
        private const val BIT_COUNT_POW_2 = 4
        private const val BIT_COUNT_POW_3 = 8
        private const val BIT_COUNT_POW_4 = 16
    }
}

@BruteForce
class BitwiseComplementBruteForce : BitwiseComplement {
    override operator fun invoke(n: Int): Int {
        var x = 1
        while (n > x) x = x * 2 + 1
        return x - n
    }
}
