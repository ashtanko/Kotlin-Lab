/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.DFS

/**
 * 886. Possible Bipartition
 * @see <a href="https://leetcode.com/problems/possible-bipartition">Source</a>
 */
fun interface PossibleBipartition {
    operator fun invoke(n: Int, dislikes: Array<IntArray>): Boolean
}

@DFS
class PossibleBipartitionDFS : PossibleBipartition {
    override operator fun invoke(n: Int, dislikes: Array<IntArray>): Boolean {
        val graph: Array<MutableList<Int>> = Array(n + 1) { mutableListOf() }

        for (dislike in dislikes) {
            graph[dislike[0]].add(dislike[1])
            graph[dislike[1]].add(dislike[0])
        }

        val colors = arrayOfNulls<Int>(n + 1)

        for (i in 1..n) {
            // If the connected component that node i belongs to hasn't been colored yet then try coloring it.
            if (colors[i] == null && !dfs(graph, colors, i, 1)) {
                return false
            }
        }
        return true
    }

    private fun dfs(graph: Array<MutableList<Int>>, colors: Array<Int?>, currNode: Int, currColor: Int): Boolean {
        colors[currNode] = currColor

        // Color all uncolored adjacent nodes.
        for (adjacentNode in graph[currNode]) {
            if (colors[adjacentNode] == null) {
                if (!dfs(graph, colors, adjacentNode, currColor * -1)) {
                    return false
                }
            } else if (colors[adjacentNode] == currColor) {
                return false
            }
        }
        return true
    }
}
