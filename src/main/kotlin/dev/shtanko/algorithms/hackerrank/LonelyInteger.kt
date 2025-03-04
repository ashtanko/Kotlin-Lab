/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
 * Lonely Integer
 * @see <a href="https://www.hackerrank.com/challenges/lonely-integer/problem">Lonely Integer</a>
 */
@Easy
sealed interface LonelyInteger {
    operator fun invoke(arr: IntArray): Int

    @dev.shtanko.algorithms.annotations.BruteForce
    data object BruteForce : LonelyInteger {
        override fun invoke(arr: IntArray): Int {
            if (arr.isEmpty()) return 0
            val map: MutableMap<Int, Int> = HashMap()
            for (element in arr) {
                map[element] = map.getOrDefault(element, 0) + 1
            }
            return map.filter { it.value == 1 }.keys.firstOrNull() ?: 0
        }
    }

    data object BitManipulation : LonelyInteger {
        override fun invoke(arr: IntArray): Int {
            return arr.fold(0) { acc, num -> acc xor num }
        }
    }
}
