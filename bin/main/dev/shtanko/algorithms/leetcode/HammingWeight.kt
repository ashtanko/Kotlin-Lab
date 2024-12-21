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
 * 191. Number of 1 Bits
 * @see <a href="https://leetcode.com/problems/number-of-1-bits">Source</a>
 */
fun interface HammingWeight {
    operator fun invoke(n: Int): Int
}

class HammingWeightSolution : HammingWeight {
    override fun invoke(n: Int): Int {
        var count = 0
        var num = n
        for (i in 0 until Int.SIZE_BITS) {
            if (num and 1 == 1) {
                count++
            }
            num = num shr 1
        }
        return count
    }
}

class HammingWeightStd : HammingWeight {
    override fun invoke(n: Int) = Integer.bitCount(n)
}

class HammingWeightXor : HammingWeight {
    override fun invoke(n: Int): Int {
        var count = 0
        var num = n
        while (num != 0) {
            num = num xor (num and -num)
            count++
        }
        return count
    }
}

class HammingWeightAnd : HammingWeight {
    companion object {
        private const val BITS = 32
    }

    override fun invoke(n: Int): Int {
        var input = n
        var res = 0
        for (i in 0..<BITS) {
            res += input and 1
            input = input shr 1
        }
        return res
    }
}

class HammingWeightUnsignedShiftRight : HammingWeight {
    override fun invoke(n: Int): Int {
        var mutableN = n
        var ones = 0
        while (mutableN != 0) {
            if (mutableN.and(1) == 1) {
                ones++
            }
            mutableN = mutableN.ushr(1)
        }
        return ones
    }
}
