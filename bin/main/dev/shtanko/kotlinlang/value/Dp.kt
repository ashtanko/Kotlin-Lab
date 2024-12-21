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

package dev.shtanko.kotlinlang.value

@JvmInline
value class Dp(val value: Float) : Comparable<Dp> {

    infix operator fun plus(other: Dp) = Dp(value = this.value + other.value)

    override operator fun compareTo(other: Dp) = value.compareTo(other.value)
}

inline val Int.dp: Dp get() = Dp(value = this.toFloat())
