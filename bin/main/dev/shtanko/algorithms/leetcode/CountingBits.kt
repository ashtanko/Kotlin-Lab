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

/**
 * 338. Counting Bits
 * @see <a href="https://leetcode.com/problems/counting-bits">Source</a>
 */
fun interface CountingBits {
    operator fun invoke(num: Int): IntArray
}

/**
 * Approach 1: Pop Count
 * Time complexity: O(n log n)
 * Space complexity: O(1)
 */
class CountingBitsPopCount : CountingBits {
    override operator fun invoke(num: Int): IntArray {
        val ans = IntArray(num + 1)
        for (x in 0..num) {
            ans[x] = popCount(x)
        }
        return ans
    }

    private fun popCount(x: Int): Int {
        var count = 0
        var z = x
        while (z != 0) {
            z = z and z - 1 // zeroing out the least significant nonzero bit
            ++count
        }
        return count
    }
}

/**
 * Approach 2: DP + Most Significant Bit
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class MostSignificantBit : CountingBits {
    override operator fun invoke(num: Int): IntArray {
        val ans = IntArray(num + 1)
        var x = 0
        var b = 1

        while (b <= num) {
            while (x < b && x + b <= num) {
                ans[x + b] = ans[x] + 1
                ++x
            }
            x = 0
            b = b shl 1
        }

        return ans
    }
}

/**
 * Approach 3: DP + Least Significant Bit
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class LeastSignificantBit : CountingBits {
    override operator fun invoke(num: Int): IntArray {
        val ans = IntArray(num + 1)
        for (x in 1..num) {
            ans[x] = ans[x shr 1] + (x and 1)
        }
        return ans
    }
}

/**
 * Approach 4: DP + Last Set Bit
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class LastSetBit : CountingBits {
    override operator fun invoke(num: Int): IntArray {
        val ans = IntArray(num + 1)
        for (x in 1..num) {
            ans[x] = ans[x and x - 1] + 1
        }
        return ans
    }
}
