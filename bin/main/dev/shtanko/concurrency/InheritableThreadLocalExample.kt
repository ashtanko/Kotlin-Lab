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

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * InheritableThreadLocal is a class in Java that provides a way to create thread-local variables that are inherited by
 * child threads. When a thread creates a new thread, the new thread will inherit the values of InheritableThreadLocal
 * variables from the parent thread. This is useful when you want to propagate context or settings across threads, such
 * as user sessions or database connections.
 */
object InheritableThreadLocalExample {

    private val threadLocal = object : InheritableThreadLocal<String>() {
        override fun initialValue(): String = "Initial Value"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Set a value in the main thread
        threadLocal.set("Main Thread Value")

        println("Main thread value: ${threadLocal.get()}")

        // Create a thread pool
        val executor = Executors.newFixedThreadPool(2)

        // Submit tasks to the executor
        executor.submit {
            println("Task 1 before inheriting: ${threadLocal.get()}")

            // Create a new thread and print the inherited value
            Thread {
                println("Task 1, Child thread value: ${threadLocal.get()}")
            }.start()
        }

        executor.submit {
            println("Task 2 before inheriting: ${threadLocal.get()}")

            // Create a new thread and print the inherited value
            Thread {
                println("Task 2, Child thread value: ${threadLocal.get()}")
            }.start()
        }

        // Shutdown executor
        executor.shutdown()
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow()
            }
        } catch (e: InterruptedException) {
            executor.shutdownNow()
        }
    }
}
