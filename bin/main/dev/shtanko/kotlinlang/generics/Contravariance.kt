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

package dev.shtanko.kotlinlang.generics

internal class Case<in T> {
    private val contents = mutableListOf<T>()

    // fun produce(): T = contents.last()     // Producer: Error
    fun consume(item: T) = contents.add(item) // Consumer: OK
}

// The Case declared with the in modifier consumes T and its subtypes:
internal fun useConsumer(case: Case<Rifle>) {
    case.consume(SniperRifle())
}
