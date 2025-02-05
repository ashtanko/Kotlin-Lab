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

package dev.shtanko.kotlinlang.functions.reduce

fun reduceList(list: List<Int>): Int = list.filter { n -> n % 2 == 0 }.map { a ->
    a * 10
}.reduce { a, b ->
    a + b
}

fun reduceList(list: List<String>) = list.reduce { acc, string -> acc + string }

fun reduceListIndexed(list: List<String>) = list.reduceIndexed { index, acc, string -> acc + string + index }
