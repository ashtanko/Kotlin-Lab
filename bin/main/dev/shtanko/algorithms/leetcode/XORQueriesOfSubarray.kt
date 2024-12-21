/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1310. XOR Queries of a Subarray
 * @see <a href="https://leetcode.com/problems/xor-queries-of-a-subarray/">Source</a>
 */
@Medium("https://leetcode.com/problems/xor-queries-of-a-subarray")
fun interface XORQueriesOfSubarray {
    operator fun invoke(arr: IntArray, queries: Array<IntArray>): IntArray
}

class XORQueriesOfSubarrayInPlace : XORQueriesOfSubarray {
    override fun invoke(arr: IntArray, queries: Array<IntArray>): IntArray {
        val result = mutableListOf<Int>()

        // Step 1: Convert arr into an in-place prefix XOR array
        for (i in 1 until arr.size) {
            arr[i] = arr[i] xor arr[i - 1]
        }

        // Step 2: Resolve each query using the prefix XOR array
        for (query in queries) {
            val xorResult = if (query[0] > 0) {
                arr[query[0] - 1] xor arr[query[1]]
            } else {
                arr[query[1]]
            }
            result.add(xorResult)
        }

        return result.toIntArray()
    }
}
