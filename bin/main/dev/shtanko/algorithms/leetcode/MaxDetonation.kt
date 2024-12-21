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

import java.util.Deque
import java.util.LinkedList
import java.util.Stack
import kotlin.math.max

/**
 * 2101. Detonate the Maximum Bombs
 * @see <a href="https://leetcode.com/problems/detonate-the-maximum-bombs/">Source</a>
 */
fun interface MaxDetonation {
    fun maximumDetonation(bombs: Array<IntArray>): Int

    fun getGraph(bombs: Array<IntArray>): Pair<MutableMap<Int, MutableList<Int>>, Int> {
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        val n: Int = bombs.size

        // Build the graph

        // Build the graph
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i == j) {
                    continue
                }
                val xi = bombs[i][0]
                val yi = bombs[i][1]
                val ri = bombs[i][2]
                val xj = bombs[j][0]
                val yj = bombs[j][1]

                // Create a path from node i to node j, if bomb i detonates bomb j.
                if (ri.toLong() * ri >= (xi - xj).toLong() * (xi - xj) + (yi - yj).toLong() * (yi - yj)) {
                    graph.computeIfAbsent(
                        i,
                    ) { _: Int? -> ArrayList() }.add(j)
                }
            }
        }
        return graph to n
    }
}

/**
 * Approach 1: Depth-First Search, Recursive
 */
class MaxDetonationRecursive : MaxDetonation {
    override fun maximumDetonation(bombs: Array<IntArray>): Int {
        val (graph, n) = getGraph(bombs)
        var answer = 0
        for (i in 0 until n) {
            val count = dfs(i, HashSet(), graph)
            answer = max(answer, count)
        }

        return answer
    }

    // DFS to get the number of nodes reachable from a given node cur
    private fun dfs(cur: Int, visited: MutableSet<Int>, graph: Map<Int, List<Int>>): Int {
        visited.add(cur)
        var count = 1
        for (neib in graph[cur] ?: ArrayList()) {
            if (!visited.contains(neib)) {
                count += dfs(neib, visited, graph)
            }
        }
        return count
    }
}

/**
 * Approach 2: Depth-First Search, Iterative
 */
class MaxDetonationIterative : MaxDetonation {
    override fun maximumDetonation(bombs: Array<IntArray>): Int {
        val (graph, n) = getGraph(bombs)

        var answer = 0
        for (i in 0 until n) {
            answer = max(answer, dfs(i, graph))
        }

        return answer
    }

    private fun dfs(i: Int, graph: Map<Int, List<Int>>): Int {
        val stack: Stack<Int> = Stack()
        val visited: MutableSet<Int> = HashSet()
        stack.push(i)
        visited.add(i)
        while (stack.isNotEmpty()) {
            val cur: Int = stack.pop()
            for (neib in graph[cur] ?: ArrayList()) {
                if (!visited.contains(neib)) {
                    visited.add(neib)
                    stack.push(neib)
                }
            }
        }
        return visited.size
    }
}

/**
 * Approach 3: Breadth-First Search
 */
class MaxDetonationBFS : MaxDetonation {
    override fun maximumDetonation(bombs: Array<IntArray>): Int {
        val (graph, n) = getGraph(bombs)

        var answer = 0
        for (i in 0 until n) {
            answer = max(answer, bfs(i, graph))
        }

        return answer
    }

    private fun bfs(i: Int, graph: Map<Int, List<Int>>): Int {
        val queue: Deque<Int> = LinkedList()
        val visited: MutableSet<Int> = HashSet()
        queue.offer(i)
        visited.add(i)
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (nib in graph[cur] ?: ArrayList()) {
                if (!visited.contains(nib)) {
                    visited.add(nib)
                    queue.offer(nib)
                }
            }
        }
        return visited.size
    }
}
