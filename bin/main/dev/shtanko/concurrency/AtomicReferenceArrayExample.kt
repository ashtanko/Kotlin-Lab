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

import java.util.concurrent.atomic.AtomicReferenceArray

/**
 * `AtomicReferenceArray`: An array of AtomicReference elements that supports atomic operations.
 */
object AtomicReferenceArrayExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Initialize an AtomicReferenceArray with 5 elements
        val atomicArray = AtomicReferenceArray<String>(5)

        // Set values in the array
        atomicArray.set(0, "A")
        atomicArray.set(1, "B")
        atomicArray.set(2, "C")
        atomicArray.set(3, "D")
        atomicArray.set(4, "E")

        // Get and print values from the array
        println("Initial values:")
        for (i in 0 until atomicArray.length()) {
            println("Index $i: ${atomicArray.get(i)}")
        }

        // Update a value using compareAndSet
        val indexToUpdate = 2
        val oldValue = "C"
        val newValue = "Z"
        val updated = atomicArray.compareAndSet(indexToUpdate, oldValue, newValue)

        println("Update successful: $updated")
        println("Updated value at index $indexToUpdate: ${atomicArray.get(indexToUpdate)}")

        // Perform a bulk update using lazySet
        for (i in 0 until atomicArray.length()) {
            atomicArray.lazySet(i, "Updated $i")
        }

        println("Values after lazySet:")
        for (i in 0 until atomicArray.length()) {
            println("Index $i: ${atomicArray.get(i)}")
        }
    }
}
