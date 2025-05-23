/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.leetcode

/**
 * Two City Scheduling
 */
fun Array<IntArray>.twoCitySchedCost(): Int {
    var cost = 0

    sortWith { a: IntArray, b: IntArray ->
        val first = a[1] - a[0]
        val second = b[1] - b[0]
        first - second
    }

    for (i in 0 until size / 2) {
        cost += this[i][1] + this[size - i - 1][0]
    }

    return cost
}
