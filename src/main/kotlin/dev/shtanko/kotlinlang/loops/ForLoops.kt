/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.kotlinlang.loops

fun simpleForLoopUntil() {
    // Iterates from 0 to 9 (excludes 10)
    for (i in 0 until 10) {
        println(i)
    }
}

fun simpleForLoopRange() {
    // Iterates from 0 to 10 (includes both bounds)
    for (i in 0..10) {
        println(i)
    }
}

fun simpleForLoopRangeStep() {
    // Iterates from 0 to 10, incrementing by 2
    // Output: 0, 2, 4, 6, 8, 10
    for (i in 0..10 step 2) {
        println(i)
    }
}

fun simpleForLoopDownTo() {
    // Iterates from 10 down to 0, decrementing by 1
    for (i in 10 downTo 0) {
        println(i)
    }
}

fun simpleForLoopDownToStep() {
    // Iterates from 10 down to 0, decrementing by 3
    // Output: 10, 7, 4, 1
    for (i in 10 downTo 0 step 3) {
        println(i)
    }
}

fun simpleForLoopDownToStep1() {
    // Iterates from 10 down to 0, decrementing by 10
    // Output: 10, 0
    for (i in 10 downTo 0 step 10) {
        println(i)
    }
}

fun simpleForLoopDownToStep2() {
    // Iterates from 10 down to 5, decrementing by 5
    // Output: 10, 5
    for (i in 10 downTo 5 step 5) {
        println(i)
    }
}

fun simpleForLoopArray() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // Iterates over array indices (0 to 9)
    for (i in arr.indices) {
        println("Index: $i, Value: ${arr[i]}")
    }
}

fun simpleForLoopArrayWithIndex() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // Iterates over array with both index and value
    for ((index, value) in arr.withIndex()) {
        println("Index: $index, Value: $value")
    }
}

fun main() {
    println("For loop using 'until' (0 to 9):")
    simpleForLoopUntil()

    println("\nFor loop using range (0 to 10):")
    simpleForLoopRange()

    println("\nFor loop using range with step 2 (0 to 10):")
    simpleForLoopRangeStep()

    println("\nFor loop using 'downTo' (10 to 0):")
    simpleForLoopDownTo()

    println("\nFor loop using 'downTo' with step 3 (10 to 0):")
    simpleForLoopDownToStep()

    println("\nFor loop using 'downTo' with step 10 (10 to 0):")
    simpleForLoopDownToStep1()

    println("\nFor loop using 'downTo' with step 5 (10 to 5):")
    simpleForLoopDownToStep2()

    println("\nFor loop over array indices:")
    simpleForLoopArray()

    println("\nFor loop over array with index and value:")
    simpleForLoopArrayWithIndex()
}
