/*
 * Copyright 2025 Oleksii Shtanko
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

@file:Suppress("MagicNumber")

package dev.shtanko.concurrency

import java.util.concurrent.atomic.AtomicBoolean
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Livelocks {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val lock1 = Any()
        val lock2 = Any()
        val done = AtomicBoolean(false)

        launch {
            while (!done.get()) {
                synchronized(lock1) {
                    synchronized(lock2) {
                        println("Thread 1: I have lock1 and lock2")
                        // Do some work here
                    }
                }
            }
        }

        launch {
            while (!done.get()) {
                synchronized(lock2) {
                    synchronized(lock1) {
                        println("Thread 2: I have lock2 and lock1")
                        // Do some work here
                    }
                }
            }
        }

        delay(5000) // Run for 5 seconds
        done.set(true)
    }
}
