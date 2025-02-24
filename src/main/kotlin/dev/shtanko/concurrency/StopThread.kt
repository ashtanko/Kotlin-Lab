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
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SharedFlagThread(private val stopFlag: AtomicBoolean, private val job: () -> Unit) : Thread() {
    override fun run() {
        while (!stopFlag.get()) {
            job.invoke()
        }
    }
}

private fun sharedFlagRun() {
    val stopFlag = AtomicBoolean(false)
    val thread = SharedFlagThread(stopFlag) {
        println("Thread is running")
        Thread.sleep(1000)
    }
    thread.start()
    Thread.sleep(5000)
    stopFlag.set(true)
    thread.join()
}

class InterruptibleThread(private val job: () -> Unit) : Thread() {
    override fun run() {
        try {
            while (!interrupted()) {
                println("Thread is running")
                job()
            }
        } catch (e: InterruptedException) {
            println("Thread is interrupted: ${e.message}")
        }
    }
}

private fun interruptibleThreadRun() {
    val thread = InterruptibleThread {
        println("Thread is running")
        Thread.sleep(1000)
    }
    thread.start()

    // Simulate some work
    Thread.sleep(5000)

    // Interrupt the thread
    thread.interrupt()
    thread.join()
}

private fun coroutineStop(task: () -> Unit) = runBlocking {
    val job = launch {
        while (isActive) {
            println("Coroutine is active")
            task()
        }
    }
    delay(5000)
    job.cancel()
}

fun main() {
    // sharedFlagRun()
    // interruptibleThreadRun()
    coroutineStop {
        Thread.sleep(1000)
    }
    Thread.sleep(5000)
}
