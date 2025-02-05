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

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

fun main() {
    val lock = ReentrantLock()
    thread {
        lock.lock()
        try {
            Thread.sleep(400L)
            println("New thread started")
        } finally {
            lock.unlock()
        }
    }
    Thread.sleep(100L)
    if (lock.tryLock(2, TimeUnit.SECONDS)) {
        try {
            println("Main thread")
        } finally {
            lock.unlock()
        }
    }
}
