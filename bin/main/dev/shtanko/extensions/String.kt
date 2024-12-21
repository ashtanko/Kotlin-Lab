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

import kotlin.math.min

private val alphabet = charArrayOf(
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g',
    'h',
    'i',
    'j',
    'k',
    'l',
    'm',
    'n',
    'o',
    'p',
    'q',
    'r',
    's',
    't',
    'u',
    'v',
    'w',
    'x',
    'y',
    'z',
)

/**
 * Check is a string binary.
 */
fun String.isBinary(): Boolean {
    if (this.isBlank()) return false
    val set = toSet().toMutableSet().apply {
        removeAll(listOf('0', '1'))
    }
    return set.isEmpty()
}

/**
 * @return common prefix of two strings
 */
fun Pair<String, String>.commonPrefix(): String {
    val (left, right) = this
    val min = min(left.length, right.length)
    for (i in 0 until min) {
        if (left[i] != right[i]) return left.substring(0, i)
    }
    return left.substring(0, min)
}

/**
 * Count zeroes in binary string
 * @return int array of zeroes
 */
fun String.countZeroesOnes(): IntArray {
    val c = IntArray(2)
    for (element in this) {
        c[element - '0']++
    }
    return c
}

fun String.getNumberOfLetter(): Int {
    val sb = StringBuilder()
    for (c in this) {
        sb.append("${alphabet.indexOf(c)}")
    }
    return sb.toString().removeZeroesInBegin().toInt()
}

fun String.removeZeroesInBegin(): String {
    if (this.isEmpty()) {
        return ""
    }
    if (this.length == 1 && this == "0") {
        return this
    }
    if (this.toCharArray().first().isZero().not()) {
        return this
    }
    if (this.isAllZeroes()) {
        return "0"
    }
    return this.toInt().toString()
}

fun String.isAllZeroes(): Boolean {
    return this.none { it.isZero().not() }
}

fun Char.isZero(): Boolean {
    return this == '0'
}
