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

import java.util.concurrent.CountedCompleter
import java.util.concurrent.ForkJoinPool

/**
 * `CountedCompleter` is a class in Java’s java.util.concurrent package that provides a mechanism for tasks to be
 * completed when their sub-tasks are finished. It's a variant of ForkJoinTask that is useful for parallel algorithms
 * that involve a complex hierarchy of tasks. Unlike RecursiveTask or RecursiveAction, which return results or perform
 * actions, CountedCompleter is designed to track the completion of sub-tasks and manage task dependencies.
 */
@Suppress("MagicNumber")
object CountedCompleterExample {
    // Define a CountedCompleter task for summing elements in an array
    class SumTask(
        private val array: IntArray,
        private val start: Int,
        private val end: Int,
        parent: CountedCompleter<*>?,
    ) : CountedCompleter<Int>(parent) {

        private var result: Int = 0
        private var leftTask: SumTask? = null
        private var rightTask: SumTask? = null

        override fun compute() {
            if (end - start <= 10) {
                // Base case: compute the sum directly
                result = array.slice(start until end).sum()
                // Notify the parent task of completion
                tryComplete()
            } else {
                // Recursive case: split the task into subtasks
                val mid = (start + end) / 2
                leftTask = SumTask(array, start, mid, this)
                rightTask = SumTask(array, mid, end, this)

                // Fork the subtasks
                leftTask?.fork()
                rightTask?.fork()
            }
        }

        override fun onCompletion(completeTask: CountedCompleter<*>) {
            // Aggregate results from subtasks when all are completed
            if (leftTask != null && rightTask != null) {
                result = (leftTask?.result ?: 0) + (rightTask?.result ?: 0)
            }
        }

        fun getResult(): Int = result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val array = IntArray(100) { (0..100).random() } // Create an array with random integers

        println("Array: ${array.joinToString(", ")}")

        // Create a ForkJoinPool
        val forkJoinPool = ForkJoinPool()

        // Create and submit the root task to the pool
        val rootTask = SumTask(array, 0, array.size, null)
        forkJoinPool.invoke(rootTask)

        // Get the result
        val result = rootTask.getResult()

        println("Sum of array elements: $result")

        // Shutdown the ForkJoinPool
        forkJoinPool.shutdown()
    }
}
