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

package dev.shtanko.kotlinlang.types.nothing

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun describeNumber(x: Int): String {
    return when (x) {
        1 -> "One"
        2 -> "Two"
        else -> fail("Invalid number") // `fail` returns Nothing, so this branch is unreachable
    }
}

fun main() {
    println(describeNumber(1)) // Output: One
    println(describeNumber(2)) // Output: Two
    println(describeNumber(3)) // Throws IllegalArgumentException
    val result = fail("This function never returns")
    // println(result)  // This line will never be reached
}
