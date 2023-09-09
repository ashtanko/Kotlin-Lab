/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.awt.Point
import java.util.LinkedList
import java.util.Queue

/**
 * 815. Bus Routes
 * @see <a href="https://leetcode.com/problems/bus-routes/">leetcode page</a>
 */
fun interface BusRoutes {
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int
}

/**
 * Approach #1: Breadth First Search
 */
class BusRoutesBFS : BusRoutes {
    override fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if (source == target) return 0
        val n: Int = routes.size

        val graph: MutableList<MutableList<Int>> = ArrayList()
        for (i in 0 until n) {
            routes[i].sort()
            graph.add(ArrayList())
        }
        val seen: MutableSet<Int> = HashSet()
        val targets: MutableSet<Int> = HashSet()
        val queue: Queue<Point> = LinkedList()

        // Build the graph.  Two buses are connected if
        // they share at least one bus stop.
        for (i in 0 until n) for (j in i + 1 until n) if (intersect(routes[i], routes[j])) {
            graph[i].add(j)
            graph[j].add(i)
        }

        // Initialize seen, queue, targets.
        // seen represents whether a node has ever been enqueued to queue.
        // queue handles our breadth first search.
        // targets is the set of goal states we have.
        for (i in 0 until n) {
            if (routes[i].binarySearch(source) >= 0) {
                seen.add(i)
                queue.offer(Point(i, 0))
            }
            if (routes[i].binarySearch(target) >= 0) {
                targets.add(i)
            }
        }

        while (!queue.isEmpty()) {
            val info: Point = queue.poll()
            val node: Int = info.x
            val depth: Int = info.y
            if (targets.contains(node)) return depth + 1
            for (nei in graph[node]) {
                if (!seen.contains(nei)) {
                    seen.add(nei)
                    queue.offer(Point(nei, depth + 1))
                }
            }
        }
        return -1
    }

    private fun intersect(a: IntArray, b: IntArray): Boolean {
        var i = 0
        var j = 0
        while (i < a.size && j < b.size) {
            if (a[i] == b[j]) return true
            if (a[i] < b[j]) i++ else j++
        }
        return false
    }
}

class BusRoutesBFS2 : BusRoutes {
    override fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        val n: Int = routes.size
        val toRoutes = HashMap<Int, HashSet<Int>>()
        for (i in routes.indices) {
            for (j in routes[i]) {
                if (!toRoutes.containsKey(j)) toRoutes[j] = HashSet()
                toRoutes[j]?.add(i)
            }
        }
        val bfs: Queue<IntArray> = LinkedList()
        bfs.offer(intArrayOf(source, 0))
        val seen = HashSet<Int>()
        seen.add(source)
        val seenRoutes = BooleanArray(n)
        while (!bfs.isEmpty()) {
            val stop = bfs.peek()[0]
            val bus = bfs.peek()[1]
            bfs.poll()
            if (stop == target) return bus
            for (i in toRoutes.getOrDefault(stop, HashSet())) {
                if (seenRoutes[i]) continue
                for (j in routes[i]) {
                    if (!seen.contains(j)) {
                        seen.add(j)
                        bfs.offer(intArrayOf(j, bus + 1))
                    }
                }
                seenRoutes[i] = true
            }
        }
        return -1
    }
}
