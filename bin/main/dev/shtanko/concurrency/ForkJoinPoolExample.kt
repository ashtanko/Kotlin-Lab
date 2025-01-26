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

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask

/**
 * `ForkJoinPool` is a framework in Java designed for parallel computing. It allows you to efficiently manage and
 * execute tasks that can be broken down into smaller subtasks, which can then be processed in parallel.
 */
object ForkJoinPoolExample {

    // Define a task that performs a divide-and-conquer computation
    class SumTask(private val array: IntArray, private val start: Int, private val end: Int) : RecursiveTask<Int>() {

        companion object {
            private const val THRESHOLD = 10 // Threshold for splitting tasks
        }

        override fun compute(): Int {
            return if (end - start <= THRESHOLD) {
                // Base case: compute the sum directly
                array.slice(start until end).sum()
            } else {
                // Recursive case: split the task into subtasks
                val mid = (start + end) / 2
                val leftTask = SumTask(array, start, mid)
                val rightTask = SumTask(array, mid, end)

                // Fork the subtasks
                leftTask.fork()
                // Compute the right task directly in the current thread
                val rightResult = rightTask.compute()
                // Join the result of the left task
                val leftResult = leftTask.join()

                leftResult + rightResult
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = IntArray(100) { it + 1 } // Create an array of integers from 1 to 100

        // Create a ForkJoinPool
        val forkJoinPool = ForkJoinPool()

        // Create and submit the root task to the pool
        val rootTask = SumTask(array, 0, array.size)
        val result = forkJoinPool.invoke(rootTask)

        println("Sum of array elements: $result")

        // Shutdown the ForkJoinPool
        forkJoinPool.shutdown()
    }
}

@Suppress("MagicNumber")
object ForkJoinPoolMergeSort {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = IntArray(100_000) { (0..10_0000).random() } // Create an array with random integers

        println("Unsorted array: ${array.joinToString(", ")}")

        // Create a ForkJoinPool
        val forkJoinPool = ForkJoinPool()

        // Create and submit the root task to the pool
        val rootTask = MergeSortTask(array, 0, array.size)
        forkJoinPool.invoke(rootTask)

        println("Sorted array: ${array.joinToString(", ")}")

        // Shutdown the ForkJoinPool
        forkJoinPool.shutdown()
    }
}
