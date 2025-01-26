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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import java.util.Arrays
import kotlin.math.max

/**
 * 1857. Largest Color Value in a Directed Graph
 * @see <a href="https://leetcode.com/problems/largest-color-value-in-a-directed-graph/">Source</a>
 */
fun interface LargestColorValueInDirectedGraph {
    fun largestPathValue(colors: String, edges: Array<IntArray>): Int
}

class LargestColorValueInDirectedGraphSet : LargestColorValueInDirectedGraph {
    override fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
        val n: Int = colors.length
        val indegrees = IntArray(n)
        val graph: MutableList<MutableList<Int>> = ArrayList()
        for (i in 0 until n) {
            graph.add(ArrayList())
        }
        for (edge in edges) {
            val u = edge[0]
            val v = edge[1]
            graph[u].add(v)
            indegrees[v]++
        }
        val zeroIndegree: MutableSet<Int> = HashSet()
        for (i in 0 until n) {
            if (indegrees[i] == 0) {
                zeroIndegree.add(i)
            }
        }
        val counts = Array(n) { IntArray(ALPHABET_LETTERS_COUNT) }
        for (i in 0 until n) {
            counts[i][colors[i] - 'a']++
        }
        var maxCount = 0
        var visited = 0
        while (zeroIndegree.isNotEmpty()) {
            val u = zeroIndegree.iterator().next()
            zeroIndegree.remove(u)
            visited++
            for (v in graph[u]) {
                for (i in 0 until ALPHABET_LETTERS_COUNT) {
                    counts[v][i] = max(counts[v][i], counts[u][i] + if (colors[v] - 'a' == i) 1 else 0)
                }
                indegrees[v]--
                if (indegrees[v] == 0) {
                    zeroIndegree.add(v)
                }
            }
            maxCount = max(maxCount, Arrays.stream(counts[u]).max().asInt)
        }
        return if (visited == n) maxCount else -1
    }
}
