/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.extensions

import kotlin.random.Random

fun ClosedRange<Char>.randomString(length: Int) =
    (1..length)
        .map { (Random.nextInt(endInclusive.code - start.code) + start.code).toChar() }
        .joinToString("")

fun Char.isVowel(): Boolean {
    val ae = this == 'a' || this == 'e'
    val io = this == 'i' || this == 'o'
    val iou = io || this == 'u'
    return ae || iou
}
