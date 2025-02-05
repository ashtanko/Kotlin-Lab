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

package dev.shtanko.kotlinlang.any

fun compareHashCodesAny(a: Any, b: Any): Boolean {
    return a.hashCode() == b.hashCode()
}

fun compareHashCodesAnyNullable(a: Any?, b: Any?): Boolean {
    return a.hashCode() == b.hashCode()
}

fun symmetric(x: Any, y: Any): Boolean {
    return x == y && y == x
}

fun Triple<Any, Any, Any>.isTransitive(): Boolean {
    val (x, y, z) = this
    return x == y && y == z && x == z
}

fun Pair<Any, Any>.isConsistent(invokeCount: Int): Boolean {
    val (x, y) = this
    var result = x == y
    repeat(invokeCount) {
        result = x == y
    }
    return result
}
