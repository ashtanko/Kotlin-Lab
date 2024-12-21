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

package dev.shtanko.algorithms.graph

class AdjacencyMap<T> {
    val graph: HashMap<T, HashSet<T>> = HashMap()

    fun addEdge(sourceVertex: T, destinationVertex: T) {
        // Add edge to source vertex / node.
        graph
            .computeIfAbsent(sourceVertex) { HashSet() }
            .add(destinationVertex)
        // Add edge to destination vertex / node.
        graph
            .computeIfAbsent(destinationVertex) { HashSet() }
            .add(sourceVertex)
    }

    fun toList(): List<List<T>> {
        val result = mutableListOf<List<T>>()
        for (key in graph.keys) {
            val set = graph[key]
            result.add(set?.toList() ?: emptyList())
        }
        return result
    }

    override fun toString(): String = StringBuffer().apply {
        for (key in graph.keys) {
            append("$key -> ")
            append(graph[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()
}
