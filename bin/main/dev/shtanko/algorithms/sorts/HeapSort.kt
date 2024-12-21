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
 * Heap sort is a comparison based sorting technique based on Binary Heap data structure. It is similar to selection
 * sort where we first find the maximum element and place the maximum element at the end. We repeat the same process
 * for remaining element.
 *
 * Worst-case performance       O(n*log(n))
 * Best-case performance        O(n*log(n))
 * Average-case performance     O(n*log(n))
 * Worst-case space complexity  O(1) (auxiliary)
 */
class HeapSort : AbstractSortStrategy {

    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        val n = arr.size
        for (i in n / 2 - 1 downTo 0) {
            heapify(arr, n, i)
        }

        for (i in n - 1 downTo 0) {
            arr.swap(0, i)
            heapify(arr, i, 0)
        }
    }

    private fun <T : Comparable<T>> heapify(arr: Array<T>, n: Int, i: Int) {
        var largest = i
        val l = 2 * i + 1
        val r = 2 * i + 2

        if (l < n && arr[l] > arr[largest]) {
            largest = l
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r
        }

        if (largest != i) {
            arr.swap(i, largest)
            heapify(arr, n, largest)
        }
    }
}
