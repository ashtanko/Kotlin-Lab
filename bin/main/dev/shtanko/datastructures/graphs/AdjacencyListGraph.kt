/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.datastructures.graphs

/**
 * Represents a graph using an adjacency list data structure.
 *
 * @param <E> the type of data held in the graph's vertices, must be comparable.
 */
class AdjacencyListGraph<E : Comparable<E>>(vertices: List<Vertex<E>> = ArrayList()) {

    private val mVertices: MutableList<Vertex<E>> = vertices.toMutableList()

    /**
     * Removes an edge between two vertices in the graph.
     *
     * @param from the data of the source vertex.
     * @param to   the data of the target vertex.
     * @return true if the edge was removed, false otherwise.
     */
    fun removeEdge(from: E, to: E): Boolean {
        var fromV: Vertex<E>? = null
        for (v in mVertices) {
            if (from.compareTo(v.data) == 0) {
                fromV = v
                break
            }
        }
        return fromV?.removeAdjacentVertex(to) ?: false
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param from the data of the source vertex.
     * @param to   the data of the target vertex.
     * @return true if the edge was added, false otherwise.
     */
    fun addEdge(from: E, to: E): Boolean {
        var fromV: Vertex<E>? = null
        var toV: Vertex<E>? = null
        for (v in mVertices) {
            if (from.compareTo(v.data) == 0) { // see if from vertex already exists
                fromV = v
            } else if (to.compareTo(v.data) == 0) { // see if to vertex already exists
                toV = v
            }
            if (fromV != null && toV != null) {
                break // both nodes exist so stop searching
            }
        }
        if (fromV == null) {
            fromV = Vertex(from)
            mVertices.add(fromV)
        }
        if (toV == null) {
            toV = Vertex(to)
            mVertices.add(toV)
        }
        return fromV.addAdjacentVertex(toV)
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return a string representing the graph's vertices and their adjacent vertices.
     */
    override fun toString(): String {
        val sb = StringBuilder()
        for ((data, adjacentVertices) in mVertices) {
            sb.append("Vertex: ")
            sb.append(data)
            sb.append("\n")
            sb.append("Adjacent vertices: ")
            for ((data1) in adjacentVertices) {
                sb.append(data1)
                sb.append(" ")
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    /**
     * Represents a vertex in the graph.
     *
     * @param <E> the type of data held in the vertex, must be comparable.
     */
    data class Vertex<E : Comparable<E>>(
        var data: E,
        var adjacentVertices: MutableList<Vertex<E>> = ArrayList(),
    ) {

        /**
         * Adds an adjacent vertex to this vertex.
         *
         * @param to the vertex to be added as an adjacent vertex.
         * @return true if the vertex was added, false if the edge already exists.
         */
        fun addAdjacentVertex(to: Vertex<E>): Boolean {
            for (v in adjacentVertices) {
                if (v.data.compareTo(to.data) == 0) {
                    return false // the edge already exists
                }
            }
            return adjacentVertices.add(to) // this will return true;
        }

        /**
         * Removes an adjacent vertex from this vertex.
         *
         * @param to the data of the vertex to be removed.
         * @return true if the vertex was removed, false if it was not found.
         */
        fun removeAdjacentVertex(to: E): Boolean {
            // use indexes here, so it is possible to
            // remove easily without implementing
            // equals method that ArrayList.remove(Object o) uses
            for (i in 0 until adjacentVertices.size) {
                if (adjacentVertices[i].data.compareTo(to) == 0) {
                    adjacentVertices.removeAt(i)
                    return true
                }
            }
            return false
        }
    }
}
