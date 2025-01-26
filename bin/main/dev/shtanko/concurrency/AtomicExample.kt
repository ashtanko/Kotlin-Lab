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

import java.util.concurrent.atomic.AtomicInteger

@Suppress("MagicNumber")
object AtomicExample {
    private val counter = AtomicInteger(0)

    @JvmStatic
    fun main(args: Array<String>) {
        val task = Runnable {
            for (i in 0 until 1000) {
                counter.incrementAndGet()
            }
        }

        val t1 = Thread(task)
        val t2 = Thread(task)

        t1.start()
        t2.start()

        try {
            t1.join()
            t2.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        println("Counter: ${counter.get()}")
    }
}
