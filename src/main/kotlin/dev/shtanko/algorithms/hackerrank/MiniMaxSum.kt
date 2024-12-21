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

/**
 * Mini-Max Sum
 * @see <a href="https://www.hackerrank.com/challenges/mini-max-sum/problem">Source</a>
 */
@Easy
sealed interface MiniMaxSum {
    operator fun invoke(arr: IntArray): LongArray

    data object Sorting : MiniMaxSum {
        override fun invoke(arr: IntArray): LongArray {
            if (arr.isEmpty()) return longArrayOf()

            val longArray = arr.map { it.toLong() }

            val sums = longArray.map {
                longArray.sum() - it
            }
            return longArrayOf(
                sums.min().toLong(),
                sums.max().toLong(),
            )
        }
    }
}
