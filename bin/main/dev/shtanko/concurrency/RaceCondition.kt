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

import kotlin.concurrent.thread

/**
 * Problem: A race condition occurs when two or more threads access shared data and try to change it at the same time.
 * If the access is not synchronized, the final outcome can be unpredictable.
 */
@Suppress("MagicNumber")
object RaceConditionExample {

    private var counter = 0

    private fun increment() {
        repeat(10000) {
            counter++
        }
    }

    private fun run() {
        listOf(
            thread { increment() },
            thread { increment() },
            thread { increment() },
            thread { increment() },
        ).forEach {
            it.join()
        }

        println("Counter value: $counter")
    }

    @JvmStatic
    fun main(args: Array<String>) = run()
}

@Suppress("MagicNumber")
object RaceConditionFix {

    private var counter = 0
    private val lock = Any()

    fun increment() {
        repeat(10000) {
            synchronized(lock) {
                counter++
            }
        }
    }

    fun run() {
        listOf(
            thread { increment() },
            thread { increment() },
            thread { increment() },
            thread { increment() },
        ).forEach {
            it.join()
        }

        println("Counter value: $counter")
    }

    @JvmStatic
    fun main(args: Array<String>) = run()
}
