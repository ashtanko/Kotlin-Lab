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

package dev.shtanko.concurrency

import java.util.concurrent.RecursiveAction

// Define a task for merge sort
class MergeSortTask(private val array: IntArray, private val left: Int, private val right: Int) : RecursiveAction() {

    companion object {
        private const val THRESHOLD = 10 // Threshold for switching to sequential sort
    }

    override fun compute() {
        if (right - left <= THRESHOLD) {
            // Base case: Sort the array segment using a simple sort
            array.sort(left, right)
        } else {
            // Recursive case: Split the task into two subtasks
            val mid = (left + right) / 2
            val leftTask = MergeSortTask(array, left, mid)
            val rightTask = MergeSortTask(array, mid, right)

            // Fork the left task and compute the right task
            invokeAll(leftTask, rightTask)

            // Merge the sorted segments
            merge(left, mid, right)
        }
    }

    private fun merge(left: Int, mid: Int, right: Int) {
        val temp = array.copyOfRange(left, right)
        var i = left
        var j = mid
        var k = left

        while (i < mid && j < right) {
            if (temp[i - left] <= temp[j - left]) {
                array[k++] = temp[i - left]
                i++
            } else {
                array[k++] = temp[j - left]
                j++
            }
        }

        while (i < mid) {
            array[k++] = temp[i - left]
            i++
        }

        while (j < right) {
            array[k++] = temp[j - left]
            j++
        }
    }
}
