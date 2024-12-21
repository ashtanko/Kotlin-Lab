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

package dev.shtanko.datastructures.graphs

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class AdjacencyListGraphKoTest : DescribeSpec(
    {

        describe("An AdjacencyListGraph") {

            it("should add and remove an edge between two vertices") {
                val graph = AdjacencyListGraph<String>()
                graph.addEdge("A", "B")
                graph.addEdge("B", "C")

                graph.removeEdge("A", "B") shouldBe true
                graph.removeEdge("B", "C") shouldBe true

                graph.toString() shouldBe "Vertex: A\nAdjacent vertices: \nVertex: B\nAdjacent vertices: \nVertex: C\nAdjacent vertices: \n"
            }

            it("should not remove nonexistent edges") {
                val graph = AdjacencyListGraph<String>()
                graph.addEdge("A", "B")

                graph.removeEdge("B", "C") shouldBe false
                graph.removeEdge("C", "D") shouldBe false
            }

            it("should create vertex if not present when adding edges") {
                val graph = AdjacencyListGraph<String>()
                graph.addEdge("A", "B")

                graph.toString() shouldBe "Vertex: A\nAdjacent vertices: B \nVertex: B\nAdjacent vertices: \n"
            }

            it("should not remove nonexistent adjacent vertices") {
                val graph = AdjacencyListGraph<String>()
                graph.addEdge("A", "B")

                graph.removeEdge("A", "C") shouldBe false
                graph.removeEdge("C", "D") shouldBe false
            }
        }
    },
)
