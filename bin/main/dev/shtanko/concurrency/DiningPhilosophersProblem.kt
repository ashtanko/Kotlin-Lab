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

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * Problem: The dining philosophers problem involves multiple philosophers sitting around a table, each needing two
 * forks to eat. Proper synchronization is required to avoid deadlocks and ensure that all philosophers can eventually
 * eat.
 */
@Suppress("MagicNumber")
object DiningPhilosophersProblem {
    private val forks = Array(5) { ReentrantLock() }

    fun philosopher(id: Int) {
        while (true) {
            think(id)
            eat(id)
        }
    }

    private fun think(id: Int) {
        println("Philosopher $id is thinking.")
        Thread.sleep(1000)
    }

    private fun eat(id: Int) {
        val leftFork = forks[id]
        val rightFork = forks[(id + 1) % 5]

        // Deadlock prevention: pick up forks in a fixed order
        val firstFork = if (id % 2 == 0) leftFork else rightFork
        val secondFork = if (id % 2 == 0) rightFork else leftFork

        firstFork.lock()
        secondFork.lock()

        try {
            println("Philosopher $id is eating.")
            Thread.sleep(1000)
        } finally {
            secondFork.unlock()
            firstFork.unlock()
        }
    }

    fun run() {
        val philosophers = (0 until 5).map { id ->
            thread { philosopher(id) }
        }

        philosophers.forEach { it.join() }
    }

    @JvmStatic
    fun main(args: Array<String>) = run()
}
