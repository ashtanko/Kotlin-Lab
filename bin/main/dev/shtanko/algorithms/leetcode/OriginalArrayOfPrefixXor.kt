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
 * 2433. Find The Original Array of Prefix Xor
 * @see <a href="https://leetcode.com/problems/find-the-original-array-of-prefix-xor">Source</a>
 */
fun interface OriginalArrayOfPrefixXor {
    operator fun invoke(pref: IntArray): IntArray
}

sealed interface OriginalArrayOfPrefixXorSolution {

    /**
     * Approach 1: Using XOR Properties
     */
    data object XORProperties : OriginalArrayOfPrefixXor, OriginalArrayOfPrefixXorSolution {
        override fun invoke(pref: IntArray): IntArray {
            val n: Int = pref.size

            val arr = IntArray(n)
            arr[0] = pref.first()
            for (i in 1 until n) {
                arr[i] = pref[i] xor pref[i - 1]
            }

            return arr
        }
    }

    /**
     * Approach 2: Using XOR Properties, Space Optimized
     */
    data object SpaceOptimized : OriginalArrayOfPrefixXor, OriginalArrayOfPrefixXorSolution {
        override fun invoke(pref: IntArray): IntArray {
            val n: Int = pref.size

            for (i in n - 1 downTo 1) {
                pref[i] = pref[i] xor pref[i - 1]
            }

            return pref
        }
    }
}
