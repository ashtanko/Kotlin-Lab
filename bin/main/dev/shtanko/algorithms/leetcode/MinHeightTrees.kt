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

/**
 * 310. Minimum Height Trees
 * @see <a href="https://leetcode.com/problems/minimum-height-trees">Source</a>
 */
fun interface MinHeightTrees {
    operator fun invoke(num: Int, edges: Array<IntArray>): List<Int>
}

class MinHeightTreesBFS : MinHeightTrees {
    override fun invoke(num: Int, edges: Array<IntArray>): List<Int> {
        if (num == 1) return listOf(0)

        val degree = IntArray(num)
        val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
        for (edge in edges) {
            degree[edge[0]]++
            degree[edge[1]]++
            adjacencyList.getOrPut(edge[0]) { mutableListOf() }.add(edge[1])
            adjacencyList.getOrPut(edge[1]) { mutableListOf() }.add(edge[0])
        }

        val leaves = ArrayDeque<Int>()
        for (i in degree.indices) {
            if (degree[i] == 1) {
                leaves.add(i)
            }
        }

        var remainingNodes = num
        while (remainingNodes > 2) {
            val size = leaves.size
            remainingNodes -= size
            repeat(size) {
                val leaf = leaves.removeFirst()
                for (neighbor in adjacencyList[leaf] ?: emptyList()) {
                    if (--degree[neighbor] == 1) {
                        leaves.add(neighbor)
                    }
                }
            }
        }

        return leaves.toList()
    }
}
