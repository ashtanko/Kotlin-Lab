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

package dev.shtanko.algorithms.sorts

import dev.shtanko.extensions.swap

/**
 * Developed by Tony Hoare in 1959, with his work published in 1961, Quicksort is an efficient sort algorithm using
 * divide and conquer approach. Quicksort first divides a large array into two smaller sub-arrays: the low elements
 * and the high elements. Quicksort can then recursively sort the sub-arrays. The steps are:
 * 1) Pick an element, called a pivot, from the array.
 * 2) Partitioning: reorder the array so that all elements with values less than the pivot come before the pivot,
 * while all elements with values greater than the pivot come after it (equal values can go either way).
 * After this partitioning, the pivot is in its final position. This is called the partition operation.
 * 3) Recursively apply the above steps to the sub-array of elements with smaller values and separately to
 * the sub-array of elements with greater values.
 *
 * Worst-case performance       O(n^2)
 * Best-case performance        O(nLogn)
 * Average performance          O(nLogn)
 * Worst-case space complexity  O(1)
 */
class QuickSort : AbstractSortStrategy {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        sort(arr, 0, arr.size - 1)
    }

    private fun <T : Comparable<T>> sort(arr: Array<T>, low: Int, high: Int) {
        if (arr.isEmpty()) return

        val divideIndex = partition(arr, low, high)

        if (low < divideIndex - 1) { // 2) Sorting left half
            sort(arr, low, divideIndex - 1)
        }
        if (divideIndex < high) { // 3) Sorting right half
            sort(arr, divideIndex, high)
        }
    }

    private fun <T : Comparable<T>> partition(array: Array<T>, low: Int, high: Int): Int {
        var left = low
        var right = high
        val pivot = array[(left + right) / 2] // 4) Pivot Point
        while (left <= right) {
            while (array[left] < pivot) left++ // 5) Find the elements on left that should be on right

            while (array[right] > pivot) right-- // 6) Find the elements on right that should be on left

            // 7) Swap elements, and move left and right indices
            if (left <= right) {
                array.swap(left, right)
                left++
                right--
            }
        }
        return left
    }
}
