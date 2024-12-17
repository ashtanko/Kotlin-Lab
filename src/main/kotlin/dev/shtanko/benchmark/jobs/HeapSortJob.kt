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

package dev.shtanko.benchmark.jobs

@Suppress("MagicNumber")
class HeapSortJob : BenchmarkJob {
    override suspend fun invoke() {
        // Heap sort algorithm
        val arr = IntArray(1_000_000) { (0..100).random() }

        fun heapify(arr: IntArray, n: Int, i: Int) {
            var largest = i
            val left = 2 * i + 1
            val right = 2 * i + 2
            if (left < n && arr[left] > arr[largest]) largest = left
            if (right < n && arr[right] > arr[largest]) largest = right
            if (largest != i) {
                arr[i] = arr[largest].also { arr[largest] = arr[i] }
                heapify(arr, n, largest)
            }
        }

        fun heapSort(arr: IntArray) {
            val n = arr.size
            for (i in n / 2 - 1 downTo 0) heapify(arr, n, i)
            for (i in n - 1 downTo 1) {
                arr[0] = arr[i].also { arr[i] = arr[0] }
                heapify(arr, i, 0)
            }
        }

        heapSort(arr)
    }
}
