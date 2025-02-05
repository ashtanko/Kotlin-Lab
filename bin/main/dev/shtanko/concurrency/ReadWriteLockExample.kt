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

import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * `ReadWriteLock` is a synchronization aid in Java that allows multiple threads to read shared data concurrently while
 * ensuring that writes to the data are exclusive. It provides a way to improve performance in scenarios where reads are
 * more frequent than writes, as it allows multiple readers to access the data simultaneously but only one writer can
 * modify the data at a time.
 *
 * Key Concepts
 * 1. Read and Write Locks
 * Read Lock: Multiple threads can acquire the read lock simultaneously as long as no thread holds the write lock.
 * This is suitable for scenarios where reading is more frequent than writing.
 * Write Lock: Only one thread can acquire the write lock at a time, and it has exclusive access to the data.
 * During this period, no other thread can acquire either the read or write lock.
 * 2. Locking Behavior
 * Shared Access: Multiple threads can hold the read lock concurrently.
 * Exclusive Access: Only one thread can hold the write lock, and it must be the only thread holding the lock when
 * modifying the shared data.
 * Usage
 * In Kotlin, you use ReadWriteLock from the java.util.concurrent.locks package. Here’s an example of how to
 * use ReadWriteLock:
 */
object ReadWriteLockExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val lock = ReentrantReadWriteLock()
        val readLock = lock.readLock()
        val writeLock = lock.writeLock()

        val sharedResource = StringBuilder("Initial Data")

        val readerTask = Runnable {
            readLock.lock()
            try {
                println("Reading: $sharedResource")
            } finally {
                readLock.unlock()
            }
        }

        val writerTask = Runnable {
            writeLock.lock()
            try {
                sharedResource.append(" - Updated Data")
                println("Writing: $sharedResource")
            } finally {
                writeLock.unlock()
            }
        }

        val thread1 = Thread(readerTask)
        val thread2 = Thread(writerTask)
        val thread3 = Thread(readerTask)

        thread1.start()
        thread2.start()
        thread3.start()

        thread1.join()
        thread2.join()
        thread3.join()
    }
}
