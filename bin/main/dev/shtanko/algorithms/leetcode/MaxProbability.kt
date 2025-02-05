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
import java.util.PriorityQueue
import java.util.Queue

/**
 * 1514. Path with Maximum Probability
 * @see <a href="https://leetcode.com/problems/path-with-maximum-probability/">Source</a>
 */
fun interface MaxProbability {
    operator fun invoke(n: Int, edges: Array<IntArray>, probabilities: DoubleArray, start: Int, end: Int): Double
}

fun Array<IntArray>.toGraph(probabilities: DoubleArray): MutableMap<Int, MutableList<Pair<Int, Double>>> {
    val graph: MutableMap<Int, MutableList<Pair<Int, Double>>> = HashMap()
    for (i in this.indices) {
        val node1 = this[i][0]
        val node2 = this[i][1]
        val probability = probabilities[i]
        graph.computeIfAbsent(node1) { ArrayList() }.add(Pair(node2, probability))
        graph.computeIfAbsent(node2) { ArrayList() }.add(Pair(node1, probability))
    }
    return graph
}

/**
 * Approach 1: Bellman-Ford Algorithm
 */
class MaxProbabilityBellmanFord : MaxProbability {
    override operator fun invoke(
        n: Int,
        edges: Array<IntArray>,
        probabilities: DoubleArray,
        start: Int,
        end: Int,
    ): Double {
        val maxProbabilities = DoubleArray(n)
        maxProbabilities[start] = 1.0

        for (i in 0 until n - 1) {
            var updated = false
            for (j in edges.indices) {
                val node1 = edges[j][0]
                val node2 = edges[j][1]
                val probability = probabilities[j]
                if (maxProbabilities[node1] * probability > maxProbabilities[node2]) {
                    maxProbabilities[node2] = maxProbabilities[node1] * probability
                    updated = true
                }
                if (maxProbabilities[node2] * probability > maxProbabilities[node1]) {
                    maxProbabilities[node1] = maxProbabilities[node2] * probability
                    updated = true
                }
            }
            if (!updated) {
                break
            }
        }

        return maxProbabilities[end]
    }
}

/**
 * Approach 2: Shortest Path Faster Algorithm
 */
class MaxProbabilityShortestPath : MaxProbability {
    override operator fun invoke(
        n: Int,
        edges: Array<IntArray>,
        probabilities: DoubleArray,
        start: Int,
        end: Int,
    ): Double {
        val graph = edges.toGraph(probabilities)
        val maxProb = DoubleArray(n)
        maxProb[start] = 1.0

        val queue: Queue<Int> = LinkedList()
        queue.offer(start)
        while (queue.isNotEmpty()) {
            val curNode: Int = queue.poll()
            for (neighbor in graph.getOrDefault(curNode, ArrayList())) {
                val nxtNode: Int = neighbor.first
                val pathProb: Double = neighbor.second

                // Only update maxProb[nxtNode] if the current path increases
                // the probability of reach nxtNode.
                if (maxProb[curNode] * pathProb > maxProb[nxtNode]) {
                    maxProb[nxtNode] = maxProb[curNode] * pathProb
                    queue.offer(nxtNode)
                }
            }
        }

        return maxProb[end]
    }
}

/**
 * Approach 3: Dijkstra's Algorithm
 */
class MaxProbabilityDijkstra : MaxProbability {
    override operator fun invoke(
        n: Int,
        edges: Array<IntArray>,
        probabilities: DoubleArray,
        start: Int,
        end: Int,
    ): Double {
        val graph = edges.toGraph(probabilities)

        val maxProb = DoubleArray(n)
        maxProb[start] = 1.0

        val pq: PriorityQueue<Pair<Double, Int>> = PriorityQueue { a, b ->
            -a.first.compareTo(b.first)
        }
        pq.add(Pair(1.0, start))
        while (pq.isNotEmpty()) {
            val cur: Pair<Double, Int> = pq.poll()
            val curProb: Double = cur.first
            val curNode: Int = cur.second
            if (curNode == end) {
                return curProb
            }
            for (nxt in graph.getOrDefault(curNode, ArrayList())) {
                val nxtNode: Int = nxt.first
                val pathProb: Double = nxt.second
                if (curProb * pathProb > maxProb[nxtNode]) {
                    maxProb[nxtNode] = curProb * pathProb
                    pq.add(Pair(maxProb[nxtNode], nxtNode))
                }
            }
        }

        return 0.0
    }
}
