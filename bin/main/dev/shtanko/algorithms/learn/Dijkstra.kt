/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.learn

import java.util.TreeSet

internal class Edge(val v1: String, val v2: String, val dist: Int)

/** One vertex of the graph, complete with mappings to neighbouring vertices */
internal class Vertex(val name: String) : Comparable<Vertex> {

    var dist = Int.MAX_VALUE // MAX_VALUE assumed to be infinity
    var previous: Vertex? = null
    val neighbours = HashMap<Vertex, Int>()

    fun printPath() {
        when (previous) {
            this -> {
                print(name)
            }

            null -> {
                print("$name(unreached)")
            }

            else -> {
                previous?.printPath()
                print(" -> $name($dist)")
            }
        }
    }

    override fun compareTo(other: Vertex): Int {
        if (dist == other.dist) return name.compareTo(other.name)
        return dist.compareTo(other.dist)
    }

    override fun toString() = "($name, $dist)"
}

internal class Graph(
    edges: List<Edge>,
    private val directed: Boolean,
    private val showAllPaths: Boolean = false,
) {
    // mapping of vertex names to Vertex objects, built from a set of Edges
    private val mainGraph = HashMap<String, Vertex>(edges.size)

    init {
        // one pass to find all vertices
        for (e in edges) {
            if (!mainGraph.containsKey(e.v1)) mainGraph[e.v1] = Vertex(e.v1)
            if (!mainGraph.containsKey(e.v2)) mainGraph[e.v2] = Vertex(e.v2)
        }

        // another pass to set neighbouring vertices
        for (e in edges) {
            mainGraph[e.v2]?.let { vrx ->
                val n = mainGraph[e.v1]?.neighbours
                n?.let {
                    it[vrx] = e.dist
                }

                // also do this for an undirected graph if applicable
                if (!directed) {
                    mainGraph[e.v1]?.let { v1 ->
                        vrx.neighbours[v1] = e.dist
                    }
                }
            }
        }
    }

    /** Runs dijkstra using a specified source vertex */
    fun dijkstra(startName: String) {
        if (!mainGraph.containsKey(startName)) {
            println("Graph doesn't contain start vertex '$startName'")
            return
        }
        val source = mainGraph[startName]
        val q = TreeSet<Vertex>()

        // set-up vertices
        for (v in mainGraph.values) {
            v.previous = if (v == source) source else null
            v.dist = if (v == source) 0 else Int.MAX_VALUE
            q.add(v)
        }

        dijkstra(q)
    }

    /** Implementation of dijkstra's algorithm using a binary heap */
    private fun dijkstra(q: TreeSet<Vertex>) {
        while (q.isNotEmpty()) {
            // vertex with the shortest distance (first iteration will return source)
            val u = q.pollFirst() ?: return
            // if distance is infinite we can ignore 'u' (and any other remaining vertices)
            // since they are unreachable
            if (u.dist == Int.MAX_VALUE) break

            // look at distances to each neighbour
            for (a in u.neighbours) {
                val v = a.key // the neighbour in this iteration

                val alternateDist = u.dist + a.value
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v)
                    v.dist = alternateDist
                    v.previous = u
                    q.add(v)
                }
            }
        }
    }

    /** Prints a path from the source to the specified vertex */
    fun printPath(endName: String) {
        if (!mainGraph.containsKey(endName)) {
            println("Graph doesn't contain end vertex '$endName'")
            return
        }
        print(if (directed) "Directed   : " else "Undirected : ")
        val v = mainGraph[endName]
        v?.printPath()
        println()
        if (showAllPaths) printAllPaths() else println()
    }

    /** Prints the path from the source to every vertex (output order is not guaranteed) */
    private fun printAllPaths() {
        for (v in mainGraph.values) {
            v.printPath()
            println()
        }
        println()
    }
}
