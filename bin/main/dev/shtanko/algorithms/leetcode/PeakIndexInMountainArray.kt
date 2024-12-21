/*
 * Copyright 2020 Oleksii Shtanko
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

fun interface PeakIndexInMountainArrayStrategy {
    operator fun invoke(arr: IntArray): Int
}

/**
 * Approach 1: Linear Scan
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class PeakIndexInMountainArrayLinearScan : PeakIndexInMountainArrayStrategy {
    override operator fun invoke(arr: IntArray): Int {
        var i = 0
        while (arr[i] < arr[i + 1]) i++
        return i
    }
}

/**
 * Approach 2: Binary Search
 * Time complexity: O(log n)
 * Space complexity: O(1)
 */
class PeakIndexInMountainArrayBinarySearch : PeakIndexInMountainArrayStrategy {
    override operator fun invoke(arr: IntArray): Int {
        var lo = 0
        var hi: Int = arr.size - 1
        while (lo < hi) {
            val mi = lo + (hi - lo) / 2
            if (arr[mi] < arr[mi + 1]) lo = mi + 1 else hi = mi
        }
        return lo
    }
}

class PeakIndexInMountainArrayBetterThanBinarySearch : PeakIndexInMountainArrayStrategy {
    override operator fun invoke(arr: IntArray): Int {
        var i = 1
        while (i + 1 < arr.size) {
            if (arr[i] > arr[i + 1]) return i
            ++i
        }
        return 0
    }
}
