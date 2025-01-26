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
 * N-Repeated Element in Size 2N Array
 * @see <a href="https://leetcode.com/problems/n-repeated-element-in-size-2n-array/">Source</a>
 */
fun interface RepeatedNTimes {
    operator fun invoke(arr: IntArray): Int
}

/**
 * Approach 1: Count
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
class RepeatedNTimesCount : RepeatedNTimes {
    override operator fun invoke(arr: IntArray): Int {
        val count: MutableMap<Int, Int> = HashMap()
        for (x in arr) {
            count[x] = count.getOrDefault(x, 0) + 1
        }

        for (k in count.keys) {
            count[k]?.let { kCount ->
                if (kCount > 1) {
                    return k
                }
            }
        }
        return 0
    }
}

/**
 * Approach 2: Compare
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
class RepeatedNTimesCompare : RepeatedNTimes {
    override operator fun invoke(arr: IntArray): Int {
        for (k in 1..3) for (i in 0 until arr.size - k) if (arr[i] == arr[i + k]) return arr[i]
        return 0
    }
}
