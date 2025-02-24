/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.extensions

import kotlin.math.sqrt
import kotlin.random.Random

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

val isEven = IntPredicate { it % 2 == 0 }

val Int.isEven: Boolean
    get() = this % 2 == 0

fun Int.generateRandomArray(): IntArray {
    val array = IntArray(this)
    for (i in 0 until this) {
        array[i] = Random.nextInt(this)
    }
    return array
}

fun Int.lessThanZero(): Boolean {
    return this < 0
}

fun Int.isPrime(): Boolean {
    if (this < 2) return false
    val r = sqrt(this.toDouble()).toInt()
    for (d in 2..r) if (this % d == 0) return false
    return true
}

fun Int.isUgly2(): Boolean {
    var n = this
    // A non-positive integer cannot be ugly
    if (n <= 0) {
        return false
    }

    // Factorize by dividing with permitted factors
    for (factor in intArrayOf(2, 3, 5)) {
        n = (n to factor).keepDividingWhenDivisible()
    }

    // Check if the integer is reduced to 1 or not.
    return n == 1
}

/**
 * Function to check if a number is ugly or not
 */
fun Int.isUgly(): Boolean {
    var n = this
    prms.map {
        n = (n to it).maxDivide()
    }
    return n == 1
}

private val prms = listOf(2, 3, 5)
