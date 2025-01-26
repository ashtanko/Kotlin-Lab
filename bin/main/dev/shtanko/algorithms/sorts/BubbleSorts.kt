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
 * Bubble sort is a simple sorting algorithm that repeatedly steps through the list to be sorted, compares
 * each pair of adjacent items and swaps them if they are in the wrong order. The pass through the list is repeated
 * until no swaps are needed, which indicates that the list is sorted. The algorithm, which is a comparison sort,
 * is named for the way smaller or larger elements "bubble" to the top of the list. Although the algorithm is simple,
 * it is too slow and impractical for most problems even when compared to insertion sort. It can be practical
 * if the input is usually in sorted order but may occasionally have some out-of-order elements nearly in position.
 *
 * Worst-case performance       O(n^2)
 * Best-case performance        O(n)
 * Average performance          O(n^2)
 * Worst-case space complexity  O(1)
 */
class BubbleSort : AbstractSortStrategy {

    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        var exchanged: Boolean

        do {
            exchanged = false
            for (i in 1 until arr.size) {
                if (arr[i] < arr[i - 1]) {
                    arr.swap(i, i - 1)
                    exchanged = true
                }
            }
        } while (exchanged)
    }
}

class SimpleBubbleSort : AbstractSortStrategy {

    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        for (i in 0 until arr.size - 1) {
            for (j in i + 1 until arr.size) {
                if (arr[i] > arr[j]) {
                    arr.swap(i, j)
                }
            }
        }
    }
}
