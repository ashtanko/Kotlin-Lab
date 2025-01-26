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

/**
 * In Kotlin, the concept of `ThreadLocal` is similar to Java. You use the ThreadLocal class from Java to provide
 * thread-local variables. Here’s how you can use ThreadLocal in Kotlin:
 *
 * Key Concepts
 * 1. Isolation of Data
 * ThreadLocal provides each thread with its own instance of a variable, ensuring that the variable is not shared
 * between threads.
 *
 * 2. How It Works
 * Each thread that accesses the ThreadLocal variable gets its own copy, and changes made by one thread are not visible
 * to others.
 */
@Suppress("MagicNumber")
object ThreadLocalExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val threadLocalValue = ThreadLocal.withInitial { 1 }

        val task = Runnable {
            val value = threadLocalValue.get()
            println("Initial value: $value")

            threadLocalValue.set(42)
            println("Updated value: ${threadLocalValue.get()}")
        }

        val thread1 = Thread(task)
        val thread2 = Thread(task)

        thread1.start()
        thread2.start()

        thread1.join()
        thread2.join()
    }
}
