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
 * A deadlock in the Java Virtual Machine (JVM) occurs when two or more threads are blocked forever, each waiting for
 * the other to release a resource they need.
 */
@Suppress("MagicNumber")
object DeadlocksExample {
    data class Human(val name: String) {
        @Synchronized
        fun sayHi(to: Human) {
            println("$name saying hi to ${to.name}")
            Thread.sleep(500)
            to.sayHiBack(this)
        }

        @Synchronized
        fun sayHiBack(to: Human) {
            println("$name saying hi back to ${to.name}")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val adam = Human("adam")
        val eve = Human("eve")
        val adamThread = Thread {
            adam.sayHi(eve)
        }.apply {
            start()
        }

        val eveThread = Thread {
            eve.sayHi(adam)
        }.apply {
            start()
        }
        adamThread.join()
        eveThread.join()
    }
}

object DeadlocksExample2 {
    private val lock1 = Any()
    private val lock2 = Any()

    fun method1() {
        synchronized(lock1) {
            println("Thread 1: Holding lock 1...")
            Thread.sleep(100)
            println("Thread 1: Waiting for lock 2...")
            synchronized(lock2) {
                println("Thread 1: Holding lock 1 & 2...")
            }
        }
    }

    fun method2() {
        synchronized(lock2) {
            println("Thread 2: Holding lock 2...")
            Thread.sleep(100)
            println("Thread 2: Waiting for lock 1...")
            synchronized(lock1) {
                println("Thread 2: Holding lock 2 & 1...")
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val t1 = thread { method1() }
        val t2 = thread { method2() }

        t1.join()
        t2.join()
    }
}

object DeadlockPrevention {
    private val lock1 = Any()
    private val lock2 = Any()

    fun method1() {
        synchronized(lock1) {
            println("Thread 1: Holding lock 1...")
            Thread.sleep(100)
            synchronized(lock2) {
                println("Thread 1: Holding lock 1 & 2...")
            }
        }
    }

    fun method2() {
        synchronized(lock1) { // Acquire locks in the same order as method1
            println("Thread 2: Holding lock 1...")
            Thread.sleep(100)
            synchronized(lock2) {
                println("Thread 2: Holding lock 1 & 2...")
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val t1 = thread { method1() }
        val t2 = thread { method2() }

        t1.join()
        t2.join()
    }
}

object DeadlockExample3 {
    private val lockA = Any()
    private val lockB = Any()
    private val lockC = Any()

    fun thread1() {
        synchronized(lockA) {
            println("Thread 1: Holding lock A...")
            Thread.sleep(100)
            println("Thread 1: Waiting for lock B...")
            synchronized(lockB) {
                println("Thread 1: Holding lock A & B...")
                Thread.sleep(100)
                println("Thread 1: Waiting for lock C...")
                synchronized(lockC) {
                    println("Thread 1: Holding lock A, B & C...")
                }
            }
        }
    }

    fun thread2() {
        synchronized(lockB) {
            println("Thread 2: Holding lock B...")
            Thread.sleep(100)
            println("Thread 2: Waiting for lock C...")
            synchronized(lockC) {
                println("Thread 2: Holding lock B & C...")
                Thread.sleep(100)
                println("Thread 2: Waiting for lock A...")
                synchronized(lockA) {
                    println("Thread 2: Holding lock A, B & C...")
                }
            }
        }
    }

    fun thread3() {
        synchronized(lockC) {
            println("Thread 3: Holding lock C...")
            Thread.sleep(100)
            println("Thread 3: Waiting for lock A...")
            synchronized(lockA) {
                println("Thread 3: Holding lock C & A...")
                Thread.sleep(100)
                println("Thread 3: Waiting for lock B...")
                synchronized(lockB) {
                    println("Thread 3: Holding lock C, A & B...")
                }
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val t1 = thread { thread1() }
        val t2 = thread { thread2() }
        val t3 = thread { thread3() }

        t1.join()
        t2.join()
        t3.join()
    }
}

object DeadlockPrevention3 {

    private val lockA = Any()
    private val lockB = Any()
    private val lockC = Any()

    fun thread1() {
        synchronized(lockA) {
            println("Thread 1: Holding lock A...")
            Thread.sleep(100)
            synchronized(lockB) {
                println("Thread 1: Holding lock A & B...")
                Thread.sleep(100)
                synchronized(lockC) {
                    println("Thread 1: Holding lock A, B & C...")
                }
            }
        }
    }

    fun thread2() {
        synchronized(lockA) { // Acquire locks in the same order as thread1
            println("Thread 2: Holding lock A...")
            Thread.sleep(100)
            synchronized(lockB) {
                println("Thread 2: Holding lock A & B...")
                Thread.sleep(100)
                synchronized(lockC) {
                    println("Thread 2: Holding lock A, B & C...")
                }
            }
        }
    }

    fun thread3() {
        synchronized(lockA) { // Acquire locks in the same order as thread1 and thread2
            println("Thread 3: Holding lock A...")
            Thread.sleep(100)
            synchronized(lockB) {
                println("Thread 3: Holding lock A & B...")
                Thread.sleep(100)
                synchronized(lockC) {
                    println("Thread 3: Holding lock A, B & C...")
                }
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val t1 = thread { thread1() }
        val t2 = thread { thread2() }
        val t3 = thread { thread3() }

        t1.join()
        t2.join()
        t3.join()
    }
}
