/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1519. Number of Nodes in the Sub-Tree With the Same Label
 * @see <a href="https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/">Source</a>
 */
@Medium("https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label")
fun interface CountSubTrees {
    operator fun invoke(num: Int, edges: Array<IntArray>, labels: String): IntArray
}

class CountSubTreesDFS : CountSubTrees {
    override operator fun invoke(num: Int, edges: Array<IntArray>, labels: String): IntArray {
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        for (e in edges) {
            graph.computeIfAbsent(e[0]) { ArrayList() }.add(e[1])
            graph.computeIfAbsent(e[1]) { ArrayList() }.add(e[0])
        }
        val ans = IntArray(num)
        dfs(graph, 0, -1, labels, ans)
        return ans
    }

    private fun dfs(graph: Map<Int, List<Int>>, node: Int, parent: Int, labels: String, ans: IntArray): IntArray {
        val count = IntArray(ALPHABET_LETTERS_COUNT)
        val currentLabel = labels[node]
        for (child in graph[node] ?: emptyList()) {
            if (child != parent) {
                val sub = dfs(graph, child, node, labels, ans)
                for (i in 0 until ALPHABET_LETTERS_COUNT) {
                    count[i] += sub[i]
                }
            }
        }
        ++count[currentLabel.code - 'a'.code]
        ans[node] = count[currentLabel.code - 'a'.code]
        return count
    }
}
