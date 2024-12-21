/*
 * Copyright 2022 Oleksii Shtanko
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

import java.util.TreeMap
import kotlin.math.max

/**
 * 1632. Rank Transform of a Matrix
 * @see <a href="https://leetcode.com/problems/rank-transform-of-a-matrix/">Source</a>
 */
fun interface MatrixRankTransform {
    operator fun invoke(matrix: Array<IntArray>): Array<IntArray>
}

class MatrixRankTransformMap : MatrixRankTransform {
    override operator fun invoke(matrix: Array<IntArray>): Array<IntArray> {
        val m: Int = matrix.size
        val n: Int = matrix[0].size
        val map = TreeMap<Int, MutableList<IntArray>>()
        for (i in 0 until m) {
            for (j in 0 until n) {
                map.putIfAbsent(matrix[i][j], ArrayList())
                map[matrix[i][j]]?.add(intArrayOf(i, j))
            }
        }

        val rank = IntArray(m + n)
        for ((_, value1) in map) {
            val parentMap = HashMap<Int, Int>()
            for (pair in value1) {
                union(parentMap, pair[0], pair[1] + m)
            }
            val groups = HashMap<Int, MutableList<Int>>()
            for (value in parentMap.keys) {
                val parent = find(parentMap, value)
                groups.putIfAbsent(parent, ArrayList())
                groups[parent]?.add(value)
            }
            for ((_, v) in groups) {
                var maxRank = 0
                for (value in v) {
                    maxRank = max(maxRank, rank[value])
                }
                for (value in v) {
                    rank[value] = maxRank + 1
                }
            }
            for (pair in value1) {
                matrix[pair[0]][pair[1]] = rank[pair[0]]
            }
        }

        return matrix
    }

    private fun find(parentMap: HashMap<Int, Int>, value: Int): Int {
        if (parentMap[value] == value) {
            return value
        }
        parentMap[value] = find(parentMap, parentMap.getOrDefault(value, 0))
        return parentMap.getOrDefault(value, 0)
    }

    private fun union(parentMap: HashMap<Int, Int>, u: Int, v: Int) {
        if (!parentMap.containsKey(u)) {
            parentMap[u] = u
        }
        if (!parentMap.containsKey(v)) {
            parentMap[v] = v
        }
        val pu = find(parentMap, u)
        val pv = find(parentMap, v)
        if (pu != pv) {
            parentMap[pu] = pv
        }
    }
}
