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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.DECIMAL

/**
 * 1291. Sequential Digits
 *
 */
fun interface SequentialDigits {
    operator fun invoke(low: Int, high: Int): List<Int>
}

class SequentialDigitsIterative : SequentialDigits {
    override fun invoke(low: Int, high: Int): List<Int> {
        val res: MutableList<Int> = ArrayList()

        for (i in 1..<DECIMAL) {
            var num = i
            var nextDigit = i + 1

            while (num <= high && nextDigit <= DECIMAL - 1) {
                num = num * DECIMAL + nextDigit
                if (num in low..high) {
                    res.add(num)
                }
                ++nextDigit
            }
        }

        res.sort()
        return res
    }
}
