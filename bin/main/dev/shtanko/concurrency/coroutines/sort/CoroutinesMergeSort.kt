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

package dev.shtanko.concurrency.coroutines.sort

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class CoroutinesMergeSort {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)

    /**
     * @param array The array to sort.  A copy is made, so it's content is not destroyed.
     * @param minThreshold When the array has a size smaller than this threshold, it is sorted using
     *    a non-threaded version of merge-sort.  A parallel version of merge-sort for small
     *    arrays would not be efficient because too many small arrays would be created.
     */
    fun perform(array: IntArray, minThreshold: Int = 128): IntArray {
        val arrayCopy = array.copyOf()
        return runBlocking {
            val deferred = scope.async { mergeSortRecur(arrayCopy, minThreshold) }
            deferred.await()
        }
    }

    /**
     * @param array The input. Also used to store the output.
     * @param minThreshold When the array has a size smaller than this, it is sorted using
     *    a non-threaded version of merge-sort.  A parallel version of merge-sort for small
     *    lists would not be efficient.
     */
    // Note: a `suspend` function cannot be an inner function.
    private suspend fun mergeSortRecur(array: IntArray, minThreshold: Int): IntArray {
        if (array.size <= minThreshold) {
            return mergeSortIAFast(array)
        }
        val mid = array.size / 2
        val half1 = array.sliceArray(0 until mid)
        val half2 = array.sliceArray(mid until array.size)
        val half1Defer = scope.async { mergeSortRecur(half1, minThreshold) }
        val half2Defer = scope.async { mergeSortRecur(half2, minThreshold) }

        mergeSeparated(half1Defer.await(), half2Defer.await(), output = array)
        return array
    }

    /**
     * @param array The input; also used as a work array, so its content is destroyed.
     */
    private fun mergeSortIAFast(array: IntArray): IntArray {
        val arrayCopy = array.copyOf()
        sortSectionIA(array, arrayCopy, 0, array.size)
        return arrayCopy
    }

    private fun sortSectionIA(
        input: IntArray,
        output: IntArray,
        start: Int,
        exclusiveEnd: Int,
    ) {
        if (exclusiveEnd - start <= 1) {
            return
        }
        val mid = (start + exclusiveEnd) / 2
        sortSectionIA(output, input, start, mid)
        sortSectionIA(output, input, mid, exclusiveEnd)
        mergeHalvesIA(input, output, start, mid, exclusiveEnd)
    }

    /**
     * Sorts the specified section of the array.
     *
     * @param workA Should contain identical values as workB in the specified range.
     *              The final values in the specified range are destroyed (actually they are made
     *              of two adjacent sorted ranged).
     * @param workB Should contain identical values as workA in the specified range.
     *              The final values in the specified range are the sorted values in that range.
     */
    private fun mergeHalvesIA(
        workA: IntArray,
        workB: IntArray,
        start: Int,
        mid: Int,
        exclusiveEnd: Int,
    ) {
        var p1 = start
        var p2 = mid
        for (i in start until exclusiveEnd) {
            if (p1 < mid && (p2 == exclusiveEnd || workA[p1] <= workA[p2])) {
                workB[i] = workA[p1]
                p1++
            } else {
                workB[i] = workA[p2]
                p2++
            }
        }
    }

    /**
     * Merge two sorted array in a single sorted array.
     *
     * @param half1 Sorted array.
     * @param half2 Sorted array.
     * @param output Output array of size `half1.size + half2.size`.  Its original content will be erased.
     */
    private fun mergeSeparated(half1: IntArray, half2: IntArray, output: IntArray) {
        require(half1.size + half2.size == output.size)
        var p1 = 0
        var p2 = 0
        for (i in 0 until half1.size + half2.size) {
            if (p1 < half1.size && (p2 == half2.size || half1[p1] <= half2[p2])) {
                output[i] = half1[p1]
                p1++
            } else {
                output[i] = half2[p2]
                p2++
            }
        }
    }
}
