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

import kotlin.math.ln
import kotlin.math.pow

/**
 * 970. Powerful Integers
 * @see <a href="https://leetcode.com/problems/powerful-integers/">Source</a>
 */
fun interface PowerfulIntegers {
    operator fun invoke(x: Int, y: Int, bound: Int): List<Int>
}

/**
 * Approach: Logarithmic Bounds
 */
class LogarithmicBounds : PowerfulIntegers {
    override operator fun invoke(x: Int, y: Int, bound: Int): List<Int> {
        val a = if (x == 1) bound else (ln(bound.toDouble()) / ln(x.toDouble())).toInt()
        val b = if (y == 1) bound else (ln(bound.toDouble()) / ln(y.toDouble())).toInt()

        val powerfulIntegers = HashSet<Int>()

        for (i in 0..a) {
            for (j in 0..b) {
                val value = x.toDouble().pow(i.toDouble()).toInt() + y.toDouble().pow(j.toDouble())
                    .toInt()
                if (value <= bound) {
                    powerfulIntegers.add(value)
                }
                // No point in considering other powers of "1".
                if (y == 1) {
                    break
                }
            }
            if (x == 1) {
                break
            }
        }

        return powerfulIntegers.toList()
    }
}
