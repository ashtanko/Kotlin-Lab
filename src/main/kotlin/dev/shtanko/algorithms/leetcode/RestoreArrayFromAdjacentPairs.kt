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

/**
 * 1743. Restore the Array From Adjacent Pairs
 * @see <a href="https://leetcode.com/problems/restore-the-array-from-adjacent-pairs">Source</a>
 */
fun interface RestoreArrayFromAdjacentPairs {
    operator fun invoke(adjacentPairs: Array<IntArray>): IntArray
}

sealed interface RestoreArrayFromAdjacentPairsStrategy {

    @dev.shtanko.algorithms.annotations.DFS
    data object DFS : RestoreArrayFromAdjacentPairs, RestoreArrayFromAdjacentPairsStrategy {
        override fun invoke(adjacentPairs: Array<IntArray>): IntArray {
            val graph = mutableMapOf<Int, MutableList<Int>>()

            for (pair in adjacentPairs) {
                val x = pair[0]
                val y = pair[1]
                graph.computeIfAbsent(x) { mutableListOf() }.add(y)
                graph.computeIfAbsent(y) { mutableListOf() }.add(x)
            }

            var root: Int? = null
            for (num in graph.keys) {
                if (graph[num]?.size == 1) {
                    root = num
                    break
                }
            }

            val ans = mutableListOf<Int>()
            root?.let {
                helper(root, null, ans, graph)
            }
            return ans.toIntArray()
        }

        private fun helper(node: Int, prev: Int?, ans: MutableList<Int>, graph: Map<Int, List<Int>>) {
            ans.add(node)
            graph[node]?.let {
                for (neighbor in it) {
                    if (neighbor != prev) {
                        helper(neighbor, node, ans, graph)
                    }
                }
            }
        }
    }

    @dev.shtanko.algorithms.annotations.Iterative
    data object Iterative : RestoreArrayFromAdjacentPairs, RestoreArrayFromAdjacentPairsStrategy {
        override fun invoke(adjacentPairs: Array<IntArray>) = IntArray(adjacentPairs.size + 1).apply {
            val map = HashMap<Int, Pair<Int, Int?>>()

            adjacentPairs.forEach { (a, b) ->
                map[a] = map[a]?.run { b to first } ?: (b to null)
                map[b] = map[b]?.run { a to first } ?: (a to null)
            }

            foldIndexed(map.asIterable().find { it.value.second == null }!!.key) { i, curr, _ ->
                this[i] = curr
                val (first, second) = map.remove(curr)!!
                if (second != null && first !in map) second else first
            }
        }
    }
}
