/*
 * Copyright 2021 Oleksii Shtanko
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

import java.io.Serializable

/**
 * Quadruple
 */
data class Quadruple<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
) : Serializable {

    /**
     * Returns string representation of the [Quadruple] including its [first], [second] and [third] and [fourth] values.
     */
    override fun toString(): String = "($first, $second, $third, $fourth)"

    companion object
}

/**
 * Converts this quadruple into a list.
 */
fun <T> Quadruple<T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth)

fun <T> List<T>.from(): Quadruple<T, T, T, T> {
    if (isEmpty()) throw NoSuchElementException("List is empty")
    if (size < 4) throw NoSuchElementException("List is less than 4")
    return Quadruple(first(), second(), third(), fourth())
}

/**
 * Quintuple
 */
data class Quintuple<out A, out B, out C, out D, out E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
) : Serializable {

    /**
     * Returns string representation of the [Quadruple] including its [first], [second] and [third] and [fourth]
     * and [fifth] values.
     */
    override fun toString(): String = "($first, $second, $third, $fourth), $fifth)"

    companion object
}

/**
 * Converts this quintuple into a list.
 */
fun <T> Quintuple<T, T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth, fifth)
