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

import java.util.Collections
import java.util.Hashtable
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentSkipListMap
import java.util.concurrent.ConcurrentSkipListSet
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * This is useful when you need to ensure that multiple threads can access the list safely.
 */
object CollectionsSynchronizedList {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a normal list
        val normalList = mutableListOf<String>()

        // Create a synchronized list from the normal list
        val synchronizedList = Collections.synchronizedList(normalList)

        // Use a thread pool to demonstrate concurrent access
        val threadPool = Executors.newFixedThreadPool(10)

        for (i in 0 until 100) {
            threadPool.submit {
                synchronizedList.add("Item $i")
            }
        }

        // Shutdown the thread pool and wait for all tasks to complete
        threadPool.shutdown()
        while (!threadPool.isTerminated) {
            // Wait for all threads to finish
        }

        // Print the synchronized list
        synchronizedList.forEach {
            println(it)
        }
    }
}

/**
 * `CopyOnWriteArrayList` It is particularly useful when you have a list that is mostly read and rarely modified because
 * it provides a thread-safe implementation with minimal synchronization overhead for read operations.
 */
object CopyOnWriteArrayListExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val cowList = CopyOnWriteArrayList<String>()

        // Use a thread pool to demonstrate concurrent access
        val threadPool = Executors.newFixedThreadPool(10)

        for (i in 0 until 100) {
            threadPool.submit {
                cowList.add("Item $i")
            }
        }

        // Shutdown the thread pool and wait for all tasks to complete
        threadPool.shutdown()
        while (!threadPool.isTerminated) {
            // Wait for all threads to finish
        }

        // Print the CopyOnWriteArrayList
        println(cowList)
    }
}

/**
 * `Hashtable` It synchronizes all methods, making it a good choice for scenarios where thread safety is a concern,
 * although it may have performance drawbacks compared to more modern alternatives like ConcurrentHashMap.
 */
object HashtableExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a Hashtable
        val hashtable = Hashtable<Int, String>()

        // Use a thread pool to demonstrate concurrent access
        val threadPool = Executors.newFixedThreadPool(10)

        for (i in 0 until 100) {
            threadPool.submit {
                hashtable[i] = "Value $i"
            }
        }

        // Shutdown the thread pool and wait for all tasks to complete
        threadPool.shutdown()
        while (!threadPool.isTerminated) {
            // Wait for all threads to finish
        }

        // Print the Hashtable
        hashtable.forEach { (key, value) ->
            println("Key: $key, Value: $value")
        }
    }
}

/**
 * `ConcurrentHashMap` is a thread-safe variant of HashMap that is designed for concurrent access. It provides better
 * performance in multi-threaded environments compared to Hashtable because it allows concurrent read and write
 * operations without locking the entire map.
 */
object ConcurrentHashMapExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a ConcurrentHashMap
        val concurrentMap = ConcurrentHashMap<Int, String>()

        // Use a thread pool to demonstrate concurrent access
        val threadPool = Executors.newFixedThreadPool(10)

        for (i in 0 until 100) {
            threadPool.submit {
                concurrentMap[i] = "Value $i"
            }
        }

        // Shutdown the thread pool and wait for all tasks to complete
        threadPool.shutdown()
        while (!threadPool.isTerminated) {
            // Wait for all threads to finish
        }

        // Print the ConcurrentHashMap
        concurrentMap.forEach { (key, value) ->
            println("Key: $key, Value: $value")
        }
    }
}

/**
 * `ConcurrentLinkedQueue`
 * Description: A thread-safe, non-blocking queue that uses a linked node structure.
 * Use Case: Suitable for scenarios where you need a FIFO queue with high concurrency and non-blocking operations.
 */
object ConcurrentLinkedQueueExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val queue = ConcurrentLinkedQueue<String>()

        // Adding items to the queue
        queue.add("Item 1")
        queue.add("Item 2")

        // Polling items from the queue
        println(queue.poll()) // Output: Item 1
        println(queue.poll()) // Output: Item 2
    }
}

/**
 * `ConcurrentLinkedDeque`
 * Description: A thread-safe, non-blocking deque (double-ended queue) that allows for concurrent insertion and removal
 * of elements from both ends.
 * Use Case: Ideal for scenarios where you need a concurrent deque with non-blocking operations.
 */
object ConcurrentLinkedDequeExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val deque = ConcurrentLinkedDeque<String>()

        // Adding items to the deque
        deque.addFirst("Item 1")
        deque.addLast("Item 2")

        // Polling items from the deque
        println(deque.pollFirst()) // Output: Item 1
        println(deque.pollLast()) // Output: Item 2
    }
}

/**
 * `BlockingQueue`
 * Description: An interface for thread-safe blocking queues that support operations that wait for the queue to become
 * non-empty when retrieving elements and wait for space to become available when adding elements.
 * Implementations:
 * ArrayBlockingQueue: A bounded blocking queue backed by an array.
 * LinkedBlockingQueue: An optionally bounded blocking queue backed by linked nodes.
 * PriorityBlockingQueue: An unbounded blocking queue that uses priority ordering.
 * Use Case: Useful for producer-consumer scenarios where blocking operations are needed.
 */
object BlockingQueueExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val queue = ArrayBlockingQueue<String>(10)

        // Adding items to the queue
        queue.put("Item 1")
        queue.put("Item 2")

        // Polling items from the queue with timeout
        println(queue.poll()) // Output: Item 1
        println(queue.poll()) // Output: Item 2
    }
}

/**
 * `ConcurrentSkipListMap`
 * Description: A scalable concurrent NavigableMap based on a skip list data structure.
 * Use Case: Provides a thread-safe, sorted map with concurrent access and high concurrency.
 */
object ConcurrentSkipListMapExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val map = ConcurrentSkipListMap<Int, String>()

        // Adding items to the map
        map[1] = "Value 1"
        map[2] = "Value 2"

        // Iterating over the map
        map.forEach { (key, value) ->
            println("Key: $key, Value: $value")
        }
    }
}

object SharedMutableStateExample {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val concurrentMap = ConcurrentHashMap<Int, String>()

        val jobs = List(10) {
            launch {
                repeat(100) {
                    concurrentMap[it] = "Value $it"
                }
            }
        }

        jobs.forEach { it.join() }
        concurrentMap.forEach { (key, value) -> println("Key: $key, Value: $value") }
    }
}

/**
 * ConcurrentSkipListSet is a thread-safe, scalable implementation of a sorted set in Java, based on a skip list.
 * It supports efficient concurrent access and modification, making it suitable for high-performance applications
 * where multiple threads might be accessing and modifying a sorted set concurrently.
 */
@Suppress("MagicNumber")
object ConcurrentSkipListSetExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val concurrentSkipListSet = ConcurrentSkipListSet<Int>()

        // Create a thread pool
        val executor = Executors.newFixedThreadPool(4)

        // Submit tasks to the executor to add elements to the set
        executor.submit {
            for (i in 1..10) {
                concurrentSkipListSet.add(i)
                println("Added $i")
                Thread.sleep(100) // Simulate work
            }
        }

        executor.submit {
            for (i in 11..20) {
                concurrentSkipListSet.add(i)
                println("Added $i")
                Thread.sleep(150) // Simulate work
            }
        }

        // Submit a task to print elements in the set
        executor.submit {
            Thread.sleep(500) // Wait for other tasks to finish adding elements
            println("Current elements in the set: ${concurrentSkipListSet.joinToString(", ")}")
        }

        // Shutdown executor
        executor.shutdown()
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow()
            }
        } catch (e: InterruptedException) {
            executor.shutdownNow()
        }
    }
}
