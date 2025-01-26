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

package dev.shtanko.algorithms.search

/**
 * In computer science, binary search, also known as half-interval search, logarithmic search, or binary chop, is a
 * search algorithm that finds the position of a target value within a sorted array.
 * Binary search compares the target value to the middle element of the array.
 *
 * Worst-case performance       O(log(n))
 * Best-case performance        O(1)
 * Average performance          O(log(n))
 * Worst-case space complexity  O(1)
 */
class BinarySearch<T : Comparable<T>> : AbstractSearchStrategy<T> {
    override fun perform(arr: Array<T>, element: T): Int {
        var lo = 0
        var hi = arr.size - 1
        while (lo <= hi) {
            val mid = (lo + hi) / 2
            when {
                element < arr[mid] -> hi = mid - 1
                element > arr[mid] -> lo = mid + 1
                else -> return mid
            }
        }
        return -1
    }
}
