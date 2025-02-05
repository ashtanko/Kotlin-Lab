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

package dev.shtanko.extensions

import dev.shtanko.algorithms.TOLERANCE
import dev.shtanko.algorithms.math.sqrt
import kotlin.math.abs

/**
 * Checks if the given number is a perfect square.
 *
 * @return true if the number is a perfect square, false otherwise.
 */
fun Number.isSquare(): Boolean {
    val number = this.toDouble()
    val sqrtNumber = sqrt(number)
    val integerPart = sqrtNumber.toInt()
    return abs(sqrtNumber - integerPart) < TOLERANCE
}
