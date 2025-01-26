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
    val person = listOf("Ashley", "Barbara", "Cyprian", "David")
    println(person.windowed(size = 1, step = 1))
    // [[Ashley], [Barbara], [Cyprian], [David]]
    // so similar to map { listOf(it) }

    println(person.windowed(size = 2, step = 1))
    // [[Ashley, Barbara], [Barbara, Cyprian],
    // [Cyprian, David]]
    // so similar to zipWithNext().map { it.toList() }

    println(person.windowed(size = 1, step = 2))
    // [[Ashley], [Cyprian]]

    println(person.windowed(size = 2, step = 2))
    // [[Ashley, Barbara], [Cyprian, David]]

    println(person.windowed(size = 3, step = 1))
    // [[Ashley, Barbara, Cyprian], [Barbara, Cyprian, David]]

    println(person.windowed(size = 3, step = 2))
    // [[Ashley, Barbara, Cyprian]]

    println(
        person.windowed(
            size = 3,
            step = 1,
            partialWindows = true,
        ),
    )
    // [[Ashley, Barbara, Cyprian], [Barbara, Cyprian, David],
    // [Cyprian, David], [David]]

    println(
        person.windowed(
            size = 3,
            step = 2,
            partialWindows = true,
        ),
    )
    // [[Ashley, Barbara, Cyprian], [Cyprian, David]]
}
