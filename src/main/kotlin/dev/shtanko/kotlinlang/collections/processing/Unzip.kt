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

package dev.shtanko.kotlinlang.collections.processing

fun main() {
    // zip can be used with infix notation
    val zipped = (1..4) zip ('a'..'d')
    println(zipped) // [(1, a), (2, b), (3, c), (4, d)]
    val (numbers, letters) = zipped.unzip()
    println(numbers) // [1, 2, 3, 4]
    println(letters) // [a, b, c, d]
}
