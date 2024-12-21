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

import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

/**
 * 2360. Longest Cycle in a Graph
 * @see <a href="https://leetcode.com/problems/longest-cycle-in-a-graph/">Source</a>
 */
fun interface LongestCycle {
    operator fun invoke(edges: IntArray): Int
}

/**
 * Approach 1: Depth First Search
 */
class LongestCycleDFS : LongestCycle {
    private var answer = -1

    override operator fun invoke(edges: IntArray): Int {
        val n: Int = edges.size
        val visit = BooleanArray(n)

        for (i in 0 until n) {
            if (!visit[i]) {
                val dist: MutableMap<Int, Int> = HashMap()
                dist[i] = 1
                dfs(i, edges, dist, visit)
            }
        }
        return answer
    }

    fun dfs(node: Int, edges: IntArray, dist: MutableMap<Int, Int>, visit: BooleanArray) {
        visit[node] = true
        val neighbor = edges[node]
        if (neighbor != -1 && !visit[neighbor]) {
            dist[neighbor] = dist.getOrDefault(node, 0) + 1
            dfs(neighbor, edges, dist, visit)
        } else if (neighbor != -1 && dist.containsKey(neighbor)) {
            answer = max(answer, dist.getOrDefault(node, 0) - dist.getOrDefault(neighbor, 0) + 1)
        }
    }
}

/**
 * Approach 2: Kahn's Algorithm
 */
class LongestCycleKahnsAlgorithm : LongestCycle {
    override operator fun invoke(edges: IntArray): Int {
        val n: Int = edges.size
        val visit = BooleanArray(n)
        val inDegree = IntArray(n)
        // Count inDegree of each node.
        for (edge in edges) {
            if (edge != -1) {
                inDegree[edge]++
            }
        }
        // Kahn's algorithm starts.
        val q: Queue<Int> = LinkedList()
        for (i in 0 until n) {
            if (inDegree[i] == 0) {
                q.offer(i)
            }
        }

        while (q.isNotEmpty()) {
            val node: Int = q.poll()
            visit[node] = true
            val neighbor = edges[node]
            if (neighbor != -1) {
                inDegree[neighbor]--
                if (inDegree[neighbor] == 0) {
                    q.offer(neighbor)
                }
            }
        }
        // Kahn's algorithm ends.
        var answer = -1
        for (i in 0 until n) {
            if (!visit[i]) {
                var neighbor = edges[i]
                var count = 1
                visit[i] = true
                // Iterate in the cycle.
                while (neighbor != i) {
                    visit[neighbor] = true
                    count++
                    neighbor = edges[neighbor]
                }
                answer = max(answer, count)
            }
        }
        return answer
    }
}
