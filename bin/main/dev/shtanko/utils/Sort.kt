/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.utils

import kotlin.random.Random

fun merge(array: IntArray, helper: IntArray, low: Int, middle: Int, high: Int) {
    for (i in low..high) {
        helper[i] = array[i]
    }
    var helperLeft = low
    var helperRight = middle + 1
    var current = low
    while (helperLeft <= middle && helperRight <= high) {
        if (helper[helperLeft] <= helper[helperRight]) {
            array[current] = helper[helperLeft++]
        } else {
            array[current] = helper[helperRight++]
        }
        current++
    }
    while (helperLeft <= middle) {
        array[current++] = helper[helperLeft++]
    }
}

fun Int.toRandomArray(): IntArray {
    val array = IntArray(this)
    for (i in 0 until this) {
        array[i] = Random.nextInt(this)
    }
    return array
}

/**
 * Returns maximum value of an array (a linear complexity operation).
 */
fun IntArray.maximum(): Int {
    var max = 0
    for (value in this) {
        if (value > max) {
            max = value
        }
    }
    return max
}
