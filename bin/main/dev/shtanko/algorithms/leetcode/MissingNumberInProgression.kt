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
 * 1228. Missing Number In Arithmetic Progression
 * @see <a href="https://leetcode.com/problems/missing-number-in-arithmetic-progression/">Source</a>
 */
fun interface MissingNumberInProgression {
    operator fun invoke(arr: IntArray): Int
}

/**
 * Approach 1: Linear search
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class MNLinearSearch : MissingNumberInProgression {
    override operator fun invoke(arr: IntArray): Int {
        if (arr.isEmpty() || arr.size < 2) return 0
        val n = arr.size
        val diff = arr.last().minus(arr.first()).div(n)
        var expected = arr.first()

        for (value in arr) {
            if (value != expected) return expected
            expected += diff
        }
        return expected
    }
}

/**
 * Approach 2: Binary Search
 * Time complexity : O(log n).
 * Space complexity : O(1).
 */
class MNBinarySearch : MissingNumberInProgression {
    override operator fun invoke(arr: IntArray): Int {
        if (arr.isEmpty() || arr.size < 2) return 0
        val n = arr.size
        val diff = arr.last().minus(arr.first()).div(n)
        var lo = 0
        var hi = n - 1
        while (lo < hi) {
            val mid = lo.plus(hi).div(2)
            if (arr[mid] == arr.first() + mid * diff) {
                lo = mid + 1
            } else {
                hi = mid
            }
        }
        return arr.first() + diff * lo
    }
}
