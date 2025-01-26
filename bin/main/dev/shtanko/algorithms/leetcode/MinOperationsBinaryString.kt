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

import kotlin.math.min

/**
 * 1758. Minimum Changes To Make Alternating Binary String
 * @see <a href="https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string">Source</a>
 */
fun interface MinOperationsBinaryString {
    operator fun invoke(s: String): Int
}

/**
 * Approach 1: Start with Zero or Start with One
 */
class MinOperationsBinaryStringStart : MinOperationsBinaryString {
    override fun invoke(s: String): Int {
        var start0 = 0
        var start1 = 0

        for (i in s.indices) {
            if (i % 2 == 0) {
                if (s[i] == '0') {
                    start1++
                } else {
                    start0++
                }
            } else {
                if (s[i] == '1') {
                    start1++
                } else {
                    start0++
                }
            }
        }

        return min(start0.toDouble(), start1.toDouble()).toInt()
    }
}

class MinOperationsBinaryStringCheck : MinOperationsBinaryString {
    override fun invoke(s: String): Int {
        var start0 = 0

        for (i in s.indices) {
            if (i % 2 == 0) {
                if (s[i] == '1') {
                    start0++
                }
            } else {
                if (s[i] == '0') {
                    start0++
                }
            }
        }

        return start0.coerceAtMost(s.length - start0)
    }
}
