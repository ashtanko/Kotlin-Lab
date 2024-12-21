/*
 * Copyright 2022 Oleksii Shtanko
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
 * 397. Integer Replacement
 * @see <a href="https://leetcode.com/problems/integer-replacement/">Source</a>
 */
fun interface IntegerReplacement {
    operator fun invoke(n: Int): Int
}

class IntegerReplacementBitCount : IntegerReplacement {
    override operator fun invoke(n: Int): Int {
        var n0 = n
        var c = 0
        while (n0 != 1) {
            if (n0 and 1 == 0) {
                n0 = n0 ushr 1
            } else if (n == 3 || Integer.bitCount(n0 + 1) > Integer.bitCount(n0 - 1)) {
                --n0
            } else {
                ++n0
            }
            ++c
        }
        return c
    }
}

class IntegerReplacementBinary : IntegerReplacement {
    override operator fun invoke(n: Int): Int {
        var c = 0
        var n0 = n
        while (n0 != 1) {
            if (n0 and 1 == 0) {
                n0 = n0 ushr 1
            } else if (n0 == 3 || n0 ushr 1 and 1 == 0) {
                --n0
            } else {
                ++n0
            }
            ++c
        }
        return c
    }
}
