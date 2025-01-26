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

class UnionFind(size: Int) {
    var parent: IntArray = IntArray(size)
    var rank: IntArray = IntArray(size)

    init {
        for (i in 0 until size) parent[i] = i
    }

    fun find(x: Int): Int {
        if (parent[x] != x) parent[x] = find(parent[x])
        return parent[x]
    }

    fun unionSet(x: Int, y: Int) {
        val xSet = find(x)
        val ySet = find(y)
        when {
            xSet == ySet -> {
                return
            }

            rank[xSet] < rank[ySet] -> {
                parent[xSet] = ySet
            }

            rank[xSet] > rank[ySet] -> {
                parent[ySet] = xSet
            }

            else -> {
                parent[ySet] = xSet
                rank[xSet]++
            }
        }
    }
}
