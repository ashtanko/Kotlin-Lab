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
 * Problem: Threads can be interrupted while they are waiting or sleeping. Proper handling of interruptions is essential
 * to ensure that threads can terminate gracefully when requested.
 */
@Suppress("MagicNumber")
object ThreadInterruption {
    fun longRunningTask() {
        try {
            println("Task started.")
            Thread.sleep(5000)
            println("Task completed.")
        } catch (e: InterruptedException) {
            println("Task was interrupted.")
        }
    }

    fun run() {
        val taskThread = thread { longRunningTask() }
        Thread.sleep(1000)
        taskThread.interrupt()
        taskThread.join()
    }

    @JvmStatic
    fun main(args: Array<String>) = run()
}
