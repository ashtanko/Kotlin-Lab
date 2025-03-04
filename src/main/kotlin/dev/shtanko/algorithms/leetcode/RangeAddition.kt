/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.extensions.second

/**
 * 370. Range Addition
 * @see <a href="https://leetcode.com/problems/range-addition/">Source</a>
 */
fun interface RangeAddition {
    operator fun invoke(length: Int, updates: Array<IntArray>): IntArray
}

/**
 * Approach 1: Naive Approach
 * Time complexity : O(n * k)
 * Space complexity : O(1)
 */
class RangeAdditionBruteForce : RangeAddition {
    override operator fun invoke(length: Int, updates: Array<IntArray>): IntArray {
        val ans = IntArray(length) { 0 }
        for (triplet in updates) {
            val startIndex = triplet.first()
            val endIndex = triplet.second()
            val inc = triplet.last()
            for (i in startIndex..endIndex) {
                ans[i] += inc
            }
        }
        return ans
    }
}

/**
 * Approach 2: Range Caching
 * Time complexity : O(n + k)
 * Space complexity : O(1)
 */
class RangeAdditionCaching : RangeAddition {
    override operator fun invoke(length: Int, updates: Array<IntArray>): IntArray {
        val ans = IntArray(length) { 0 }
        for (triplet in updates) {
            val startIndex = triplet.first()
            val endIndex = triplet.second()
            val inc = triplet.last()
            ans[startIndex] += inc
            if (endIndex < length - 1) {
                ans[endIndex + 1] -= inc
            }
        }
        var sum = 0
        for (i in 0 until length) {
            sum += ans[i]
            ans[i] = sum
        }
        return ans
    }
}
