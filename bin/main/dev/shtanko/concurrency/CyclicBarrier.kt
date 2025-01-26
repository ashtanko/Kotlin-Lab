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

import java.util.Random
import java.util.concurrent.CyclicBarrier

@Suppress("MagicNumber")
fun main() {
    val createdValues = mutableListOf<Int>()

    /**
     *  create a cyclic barrier that waits for 3 threads to finish their jobs, and after that,
     *  prints the sum of all the values.
     */
    val cyclicBarrier = CyclicBarrier(3) {
        println("Sum of all values is ${createdValues.sum()}")
    }

    val threads = 1.rangeTo(3).map { number ->
        Thread {
            Thread.sleep(Random().nextInt(500).toLong())
            createdValues.add(number) // add a value to the list after 500ms
            cyclicBarrier.await()
            println("I am thread ${Thread.currentThread().name} and I finished my Job!")
        }.apply {
            start()
        }
    }
    threads.forEach { thread ->
        thread.join()
    }
}
