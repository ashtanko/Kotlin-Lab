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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.DECIMAL

/**
 * Multiply Strings.
 * @see <a href="https://leetcode.com/problems/multiply-strings/">Source</a>
 */
object MultiplyStrings {
    operator fun invoke(num1: String, num2: String): String {
        val length1: Int = num1.length
        val length2: Int = num2.length
        val position = IntArray(length1 + length2)

        for (i in length1 - 1 downTo 0) {
            for (j in length2 - 1 downTo 0) {
                val product: Int = (num1[i] - '0') * (num2[j] - '0')
                val currentPosition1 = i + j
                val currentPosition2 = i + j + 1
                val sum = product + position[currentPosition2]
                position[currentPosition1] += sum / DECIMAL
                position[currentPosition2] = sum % DECIMAL
            }
        }

        val stringBuilder = StringBuilder()
        for (p in position) if (!(stringBuilder.isEmpty() && p == 0)) stringBuilder.append(p)
        return if (stringBuilder.isEmpty()) "0" else stringBuilder.toString()
    }
}
