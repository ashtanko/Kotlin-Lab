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
 * Counting Elements
 * @see <a href="https://leetcode.com/problems/counting-elements/">Source</a>
 */
fun interface CountingElements {
    operator fun invoke(arr: IntArray): Int
}

/**
 * Approach 1: Search with Array
 * Time complexity : O(N^2)
 * Space complexity : O(1)
 */
class CESearchWithArray : CountingElements {
    override operator fun invoke(arr: IntArray): Int {
        var count = 0
        for (x in arr) {
            if (x.plus(1).isInArray(arr)) {
                count++
            }
        }
        return count
    }

    /**
     * @param arr
     * Check is target in the array
     */
    private fun Int.isInArray(arr: IntArray): Boolean {
        for (x in arr) {
            if (x == this) {
                return true
            }
        }
        return false
    }
}

/**
 * Approach 2: Search with HashSet
 * Time complexity : O(N).
 * Space complexity : O(N).
 */
class CESearchingWithHashSet : CountingElements {
    override operator fun invoke(arr: IntArray): Int {
        val set = arr.toHashSet()
        var count = 0
        for (x in arr) {
            val value = x.plus(1)
            if (set.contains(value)) {
                count++
            }
        }
        return count
    }
}

/**
 * Approach 3: Search with Sorted Array
 * Time complexity : O(NlogN).
 * Space complexity : varies from O(N) to O(1).
 */
class CESearchingWithSortedArray : CountingElements {
    override operator fun invoke(arr: IntArray): Int {
        arr.sort()
        var count = 0
        var runLength = 1
        for (i in 1 until arr.size) {
            if (arr[i - 1] != arr[i]) {
                if (arr[i - 1] + 1 == arr[i]) {
                    count += runLength
                }
                runLength = 0
            }
            runLength++
        }
        return count
    }
}
