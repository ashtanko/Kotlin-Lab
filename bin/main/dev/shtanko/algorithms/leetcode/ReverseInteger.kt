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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.DECIMAL

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 */
fun Int.reverseInteger(): Int {
    var value = this
    var result = 0

    while (value != 0) {
        val tail = value % DECIMAL
        val newResult = result * DECIMAL + tail
        if ((newResult - tail) / DECIMAL != result) {
            return 0
        }
        result = newResult
        value /= DECIMAL
    }

    return result
}
