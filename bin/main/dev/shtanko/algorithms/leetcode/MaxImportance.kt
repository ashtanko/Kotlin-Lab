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

import dev.shtanko.algorithms.annotations.Sort

/**
 * 2285. Maximum Total Importance of Roads
 * @see <a href="https://leetcode.com/problems/maximum-total-importance-of-roads/">Source</a>
 */
fun interface MaxImportance {
    operator fun invoke(n: Int, roads: Array<IntArray>): Long
}

@Sort
class MaxImportanceSort : MaxImportance {
    override fun invoke(n: Int, roads: Array<IntArray>): Long {
        val degree = LongArray(n)

        roads.forEach { edge ->
            degree[edge[0]]++
            degree[edge[1]]++
        }

        degree.sort()

        var totalImportance: Long = 0
        degree.withIndex().forEach { (index, d) ->
            totalImportance += (index + 1) * d
        }

        return totalImportance
    }
}
