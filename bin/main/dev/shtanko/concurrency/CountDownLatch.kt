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

import java.util.concurrent.CountDownLatch

fun main() {
    val createdValues = mutableListOf<Int>()

    /**
     *  create a countdown latch counts down from 5, and 3 threads await for the final countdown.
     *  after reaching zero, each thread prints their name.
     */
    val countDownLatch = CountDownLatch(5)

    val threads = 1.rangeTo(5).map { number ->
        Thread {
            createdValues.add(number)
            countDownLatch.countDown() // signal the CountDownLatch
        }.apply {
            start()
        }
    }

    /**
     * Let 3 threads wait and print the sum.
     */
    val waitingThreads = 1.rangeTo(3).map {
        Thread {
            countDownLatch.await()
            println("I'm thread ${Thread.currentThread().name} and the sum of all values is: ${createdValues.sum()}")
        }.apply {
            start()
        }
    }
    threads.forEach { thread ->
        thread.join()
    }
    waitingThreads.forEach { thread ->
        thread.join()
    }
}
