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
 * 1539. Kth Missing Positive Number
 * @see <a href="https://leetcode.com/problems/kth-missing-positive-number/">Source</a>
 */
fun interface FindKthPositive {
    operator fun invoke(arr: IntArray, k: Int): Int
}

class FindKthPositiveImpl : FindKthPositive {
    override operator fun invoke(arr: IntArray, k: Int): Int {
        var l = 0
        var r: Int = arr.size
        var m: Int
        while (l < r) {
            m = (l + r) / 2
            if (arr[m] - 1 - m < k) {
                l = m + 1
            } else {
                r = m
            }
        }
        return l + k
    }
}
