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
    println((1..4).zipWithNext())
    // [(1, 2), (2, 3), (3, 4)]

    val person = listOf("Ashley", "Barbara", "Cyprian")
    println(person.zipWithNext())
    // [(Ashley, Barbara), (Barbara, Cyprian)]

    val person2 = listOf("A", "B", "C", "D", "E")
    println(person2.zipWithNext { prev, next -> "$prev$next" })
    // [AB, BC, CD, DE]
}
