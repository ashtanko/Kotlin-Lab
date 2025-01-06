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

import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.thread

object MultipleReading {
    @JvmStatic
    fun main(args: Array<String>) {
        val list = ScalableThreadSafeArrayList<Int>(capacity = 1000)
        list.addElement(0)
        for (i in 1 until 1000) {
            thread(name = "ReadThread-${i + 1}", priority = 10) {
                list.getElement(0)
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            val index = i

            thread(name = "WriteThread-${i + 1}") {
                list.addElement(index)
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}

object MultipleReadingExample2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val list = ScalableThreadSafeArrayList<Int>(capacity = 1000)
        list.addElement(999)

        for (i in 0 until 5000) {
            thread(name = "ReadThread-${i + 1}", priority = 10) {
                list.getElement(0)
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        for (i in 1 until 1000) {
            thread(name = "WriteThread-${i + 1}") {
                list.addElement(i)
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}

class ScalableThreadSafeArrayList<E>(capacity: Int = 1000) {
    private val list: MutableList<E> = ArrayList<E>(capacity)
    private val rwLock = ReentrantReadWriteLock()
    private val readLock = rwLock.readLock()
    private val writeLock = rwLock.writeLock()

    fun addElement(element: E) {
        writeLock.lock()
        try {
            list.add(element)
            println("Element $element was added by thread: ${Thread.currentThread().name}")
        } finally {
            writeLock.unlock()
        }
    }

    fun getElement(index: Int): E {
        readLock.lock()
        try {
            println("Element at $index was extracted by: ${Thread.currentThread().name}")
            return list[index]
        } finally {
            readLock.unlock()
        }
    }
}
