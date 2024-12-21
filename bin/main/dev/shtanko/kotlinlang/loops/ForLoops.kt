/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.loops

fun simpleForLoopUntil() {
    // 0...9
    for (i in 0 until 10) {
        println(i)
    }
}

fun simpleForLoopRange() {
    // 0...10
    for (i in 0..10) {
        println(i)
    }
}

fun simpleForLoopRangeStep() {
    // 0,2,4,6,8,10
    for (i in 0..10 step 2) {
        println(i)
    }
}

fun simpleForLoopDownTo() {
    // 10...0
    for (i in 10 downTo 0) {
        println(i)
    }
}

fun simpleForLoopDownToStep() {
    // 10,7,4,1
    for (i in 10 downTo 0 step 3) {
        println(i)
    }
}

fun simpleForLoopDownToStep1() {
    // 10,0
    for (i in 10 downTo 0 step 10) {
        println(i)
    }
}

fun simpleForLoopDownToStep2() {
    // 10,5
    for (i in 10 downTo 5 step 5) {
        println(i)
    }
}

fun simpleForLoopArray() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (i in arr.indices) {
        println(i)
    }
}

fun simpleForLoopArrayWithIndex() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for ((index, value) in arr.withIndex()) {
        println("$index $value")
    }
}

fun main() {
    println("For loop using until keyword: ")
    simpleForLoopUntil()
    println("For loop using range: ")
    simpleForLoopRange()
    println("For loop using range with step: ")
    simpleForLoopRangeStep()
    println("For loop using downTo keyword: ")
    simpleForLoopDownTo()
    println("For loop using downTo keyword 2: ")
    simpleForLoopDownToStep()
    println("For loop using downTo step 1: ")
    simpleForLoopDownToStep1()
    println("For loop using downTo keyword 4: ")
    simpleForLoopDownToStep2()
    println("For loop using downTo keyword 5: ")
    simpleForLoopArray()
    println("For loop array: ")
    simpleForLoopArray()
    println("For loop array with index: ")
    simpleForLoopArrayWithIndex()
}
