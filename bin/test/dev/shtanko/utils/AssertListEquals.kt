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

package dev.shtanko.utils

fun <T : Comparable<T>> assertListEquals(expected: List<T>, actual: List<T>): Boolean {
    if (expected.size != actual.size) return false
    val expectedMutable = expected.toMutableList()
    val actualMutable = actual.toMutableList()
    expectedMutable.sort()
    actualMutable.sort()
    val ex2 = expectedMutable == actualMutable
    val ex1 = expectedMutable.removeAll(actual)
    return ex1 && ex2
}
