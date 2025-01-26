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

import java.util.concurrent.Phaser

/**
 * `Phaser`: A more advanced synchronization aid that can be used for coordinating multiple phases of execution among
 * threads.
 */
@Suppress("MagicNumber")
object PhaserExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val phaser = Phaser(1) // The phaser is initially registered with one party (the main thread)

        // Worker threads that will be synchronized using the phaser
        val thread1 = Thread {
            try {
                println("Thread 1: Performing phase 1 task")
                Thread.sleep(1000) // Simulate work
                phaser.arriveAndAwaitAdvance() // Wait for other threads to reach this point

                println("Thread 1: Performing phase 2 task")
                Thread.sleep(1000) // Simulate work
                phaser.arriveAndAwaitAdvance() // Wait for other threads to reach this point
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        val thread2 = Thread {
            try {
                println("Thread 2: Performing phase 1 task")
                Thread.sleep(1000) // Simulate work
                phaser.arriveAndAwaitAdvance() // Wait for other threads to reach this point

                println("Thread 2: Performing phase 2 task")
                Thread.sleep(1000) // Simulate work
                phaser.arriveAndAwaitAdvance() // Wait for other threads to reach this point
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        // Register the threads with the phaser
        phaser.register() // Register thread 1
        phaser.register() // Register thread 2

        thread1.start()
        thread2.start()

        // Wait for the threads to finish their tasks
        thread1.join()
        thread2.join()

        // Deregister the main thread
        phaser.arriveAndDeregister()
    }
}
