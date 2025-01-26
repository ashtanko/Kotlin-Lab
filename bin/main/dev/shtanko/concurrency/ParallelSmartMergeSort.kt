/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.concurrency

import dev.shtanko.algorithms.sorts.MergeSort
import dev.shtanko.utils.merge
import java.util.concurrent.RecursiveAction

class ParallelSmartMergeSort(
    val array: IntArray,
    val low: Int,
    val high: Int,
) : RecursiveAction() {
    private val helper = IntArray(array.size)
    private val arr = array.toTypedArray()

    override fun compute() {
        if (low < high) {
            if (high - low <= MAX) {
                MergeSort().perform(arr)
            } else {
                val mid = low.plus(high).div(2)
                val left = ParallelSmartMergeSort(array, low, mid)
                val right = ParallelSmartMergeSort(array, mid + 1, high)
                invokeAll(left, right)
                merge(array, helper, low, mid, high)
            }
        }
    }

    companion object {
        private const val MAX = 1 shl 13
    }
}
