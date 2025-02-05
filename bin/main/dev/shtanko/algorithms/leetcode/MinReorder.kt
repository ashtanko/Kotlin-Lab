/*
 * Copyright 2023 Oleksii Shtanko
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

import kotlin.math.abs

/**
 * 1466. Reorder Routes to Make All Paths Lead to the City Zero
 * @see <a href="https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero">
 *     Source</a>
 */
fun interface MinReorder {
    operator fun invoke(n: Int, connections: Array<IntArray>): Int
}

class MinReorderDFS : MinReorder {
    override operator fun invoke(n: Int, connections: Array<IntArray>): Int {
        val al: MutableList<MutableList<Int>> = ArrayList()
        for (i in 0 until n) al.add(ArrayList())
        for (c in connections) {
            al[c[0]].add(c[1])
            al[c[1]].add(-c[0])
        }
        return dfs(al, BooleanArray(n), 0)
    }

    private fun dfs(al: List<List<Int>>, visited: BooleanArray, from: Int): Int {
        var change = 0
        visited[from] = true
        for (to in al[from]) {
            if (!visited[abs(to)]) change += dfs(al, visited, abs(to)) + if (to > 0) 1 else 0
        }
        return change
    }
}
