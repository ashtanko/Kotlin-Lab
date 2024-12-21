/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.graph.bfs

import java.util.LinkedList

class GraphBFS(private val vertices: Int) {
    private val adj: Array<LinkedList<Int>> = Array(vertices) { LinkedList() }

    // Function to add an edge into the graph
    fun addEdge(v: Int, w: Int) {
        adj[v].add(w)
    }

    // prints BFS traversal from a given source s
    fun bfs(source: Int): List<Int> {
        val result = mutableListOf<Int>()
        // Mark all the vertices as not visited(By default
        // set as false)
        var s = source
        val visited = BooleanArray(vertices) { false }

        // Create a queue for BFS
        val queue = LinkedList<Int>()

        // Mark the current node as visited and enqueue it
        visited[s] = true
        queue.add(s)
        while (queue.isNotEmpty()) {
            // Dequeue a vertex from queue and print it
            s = queue.poll()
            result.add(s)

            // Get all adjacent vertices of the dequeued vertex s
            // If an adjacent has not been visited, then mark it
            // visited and enqueue it
            val i: Iterator<Int> = adj[s].listIterator()
            while (i.hasNext()) {
                val n = i.next()
                if (!visited[n]) {
                    visited[n] = true
                    queue.add(n)
                }
            }
        }
        return result
    }
}
