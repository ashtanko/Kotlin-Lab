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

import org.junit.jupiter.api.Test

internal class DijkstraTest {

    companion object {
        private const val START = "a"
        private const val END = "e"

        private val GRAPH = listOf(
            Edge("a", "b", 7),
            Edge("a", "c", 9),
            Edge("a", "f", 14),
            Edge("b", "c", 10),
            Edge("b", "d", 15),
            Edge("c", "d", 11),
            Edge("c", "f", 2),
            Edge("d", "e", 6),
            Edge("e", "f", 9),
        )
    }

    @Test
    internal fun `should calculate correct shortest paths`() {
        with(Graph(edges = GRAPH, true)) { // directed
            dijkstra(START)
            printPath(END)
        }
    }
}
