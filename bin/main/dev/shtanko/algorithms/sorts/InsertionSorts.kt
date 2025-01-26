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
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * Each iteration, insertion sort removes one element from the input data, finds the location it belongs within
 * the sorted list, and inserts it there. It repeats until no input elements remain.
 */
class InsertionSort : AbstractSortStrategy {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        for (i in 1 until arr.size) {
            for (j in i downTo 1) {
                if (arr[j - 1] < arr[j]) break
                arr.swap(j, j - 1)
            }
        }
    }
}

/**
 * This method implements the Generic Insertion Sort
 * Sorts the array in increasing order
 *
 * Worst-case performance       O(n^2)
 * Best-case performance        O(n)
 * Average performance          O(n^2)
 * Worst-case space complexity  O(1)
 **/
class InsertionSort2 : AbstractSortStrategy {

    /**
     *  * @param arr The array to be sorted
     *  * Sorts the array in increasing order
     */
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        for (i in 1 until arr.size) {
            val x = arr[i]
            var j = i
            while (j > 0 && arr[j - 1] > x) {
                arr[j] = arr[j - 1]
                j--
            }
            arr[j] = x
        }
    }
}
