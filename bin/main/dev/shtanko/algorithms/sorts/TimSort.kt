/*
 * Copyright 2023 Oleksii Shtanko
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

import kotlin.math.min

class TimSort : AbstractSortStrategy {

    companion object {
        private const val RUN = 32
    }

    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        // Sort individual subarrays of size THRESHOLD
        var i = 0
        val length = arr.size
        while (i < length) {
            // perform insertion sort
            insertionSort(arr, i, min(i + RUN - 1, length - 1))
            i += RUN
        }
        var size: Int = RUN
        while (size < length) {
            var left = 0
            while (left < length) {
                left += 2 * size
            }
            size *= 2
        }
    }

    // this function sorts array from left index
    // to right index which is of size almost THRESHOLD
    private fun <T : Comparable<T>> insertionSort(arr: Array<T>, left: Int, right: Int) {
        for (i in left + 1..right) {
            val temp = arr[i]
            var j = i - 1
            while (j >= 0 && arr[j] > temp && j >= left) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = temp
        }
    }

    // merge function merges the sorted runs
    private inline fun <reified T : Comparable<T>> merge(arr: Array<T>, left: Int, mid: Int, right: Int) {
        val leftArryLen = mid - left + 1
        val rightArrLen = right - mid

        val leftArr = arrayOfNulls<T>(leftArryLen)
        val rightArr = arrayOfNulls<T>(rightArrLen)
        for (x in 0 until leftArryLen) {
            leftArr[x] = arr[left + x]
        }
        for (x in 0 until rightArrLen) {
            rightArr[x] = arr[mid + 1 + x]
        }
        var i = 0
        var j = 0
        var k = left
        while (i < leftArryLen && j < rightArrLen) {
            if (leftArr[i]!! <= rightArr[j]!!) {
                arr[k] = leftArr[i]!!
                i++
            } else {
                arr[k] = rightArr[j]!!
                j++
            }
            k++
        }

        // copy remaining elements of left, if any
        while (i < leftArryLen) {
            arr[k] = leftArr[i]!!
            k++
            i++
        }

        // copy remaining element of right, if any
        while (j < rightArrLen) {
            arr[k] = rightArr[j]!!
            k++
            j++
        }
    }
}
