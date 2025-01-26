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

/**
 * 1460. Make Two Arrays Equal by Reversing Subarrays
 * @see <a href="https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays">Source</a>
 */
fun interface CanBeEqual {
    operator fun invoke(target: IntArray, arr: IntArray): Boolean
}

/**
 * Approach 1: Sorting
 */
class CanBeEqualSorting : CanBeEqual {
    override fun invoke(target: IntArray, arr: IntArray): Boolean {
        return arr.sortedArray().contentEquals(target.sortedArray())
    }
}

/**
 * Approach 2: Frequency Counting With 2 Dictionaries
 */
class CanBeEqualCounting : CanBeEqual {
    override fun invoke(target: IntArray, arr: IntArray): Boolean {
        val targetFreq = target.toList().groupingBy { it }.eachCount()
        val arrFreq = arr.toList().groupingBy { it }.eachCount()

        return targetFreq == arrFreq
    }
}

/**
 * Approach 3: Frequency Counting With 1 Dictionary
 */
class CanBeEqual1Dictionary : CanBeEqual {
    override fun invoke(target: IntArray, arr: IntArray): Boolean {
        val arrFreq = arr.toList().groupingBy { it }.eachCount().toMutableMap()

        for (num in target) {
            // If num does not appear in arrFreq or its count is 0, return false
            val count = arrFreq[num] ?: return false

            // Decrement the frequency count for num and remove key if the count goes to 0
            if (count == 1) {
                arrFreq.remove(num)
            } else {
                arrFreq[num] = count - 1
            }
        }

        return arrFreq.isEmpty()
    }
}
