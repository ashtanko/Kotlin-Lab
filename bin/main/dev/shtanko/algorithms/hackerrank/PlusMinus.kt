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

package dev.shtanko.algorithms.hackerrank

import dev.shtanko.algorithms.annotations.level.Easy
import java.util.Locale

/**
 * Plus Minus
 * @see <a href="https://www.hackerrank.com/challenges/plus-minus/problem">Source</a>
 */
@Easy
@Suppress("MagicNumber")
sealed interface PlusMinus {
    operator fun invoke(arr: IntArray): DoubleArray

    data object BruteForce : PlusMinus {
        override fun invoke(arr: IntArray): DoubleArray {
            if (arr.isEmpty()) return doubleArrayOf()
            val size = arr.size.toDouble()
            val counts = listOf(
                arr.count { it > 0 },
                arr.count { it < 0 },
                arr.count { it == 0 },
            )

            return counts.map { count -> count / size }
                .onEach { ratio -> println(String.format(Locale.getDefault(), "%.6f", ratio)) }
                .toDoubleArray()
        }
    }
}
