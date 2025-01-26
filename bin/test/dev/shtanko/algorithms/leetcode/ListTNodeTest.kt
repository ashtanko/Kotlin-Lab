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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class ListTNodeTest {
    private class PrettyPrintIntArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListTNode(1),
                "1",
            ),
            Arguments.of(
                ListTNode(1).apply {
                    next = ListTNode(2).apply {
                        next = ListTNode(3)
                    }
                },
                "1->2->3",
            ),
            Arguments.of(
                getTwentyItemsList(),
                "0->1->2->3->4->5->6->7->8->9->10->11->12->13->14->15->16->17->18->19",
            ),
        )

        private fun getTwentyItemsList(): ListTNode<Int> {
            val root = ListTNode(0)
            var ptr: ListTNode<Int>? = root
            for (i in 1 until 20) {
                ptr?.next = ListTNode(i)
                ptr = ptr?.next
            }
            return root
        }
    }

    private class PrettyPrintStringArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListTNode("A").apply {
                    next = ListTNode("B")
                },
                "A->B",
            ),
            Arguments.of(
                ListTNode("A").apply {
                    next = ListTNode("B").apply {
                        next = ListTNode("C").apply {
                            next = ListTNode("D")
                        }
                    }
                },
                "A->B->C->D",
            ),
        )
    }

    private class PrettyPrintObjectArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListTNode(DummyObject(0, "John")).apply {
                    next = ListTNode(DummyObject(1, "Doe"))
                },
                "{id: 0 name: John}->{id: 1 name: Doe}",
            ),
        )
    }

    private class ListObjectArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(DummyObject(0, "John"), DummyObject(1, "Doe")),
                ListTNode(DummyObject(0, "John")).apply {
                    next = ListTNode(DummyObject(1, "Doe"))
                },
            ),
        )
    }

    @ArgumentsSource(PrettyPrintIntArgumentsProvider::class)
    @ParameterizedTest
    fun `print list node int test`(node: ListTNode<Int>, expected: String) {
        val actual = node.prettyPrint()
        Assertions.assertEquals(expected, actual)
    }

    @ArgumentsSource(PrettyPrintStringArgumentsProvider::class)
    @ParameterizedTest
    fun `print list node string test`(node: ListTNode<String>, expected: String) {
        val actual = node.prettyPrint()
        Assertions.assertEquals(expected, actual)
    }

    @ArgumentsSource(PrettyPrintObjectArgumentsProvider::class)
    @ParameterizedTest
    fun `print list node object test`(node: ListTNode<DummyObject>, expected: String) {
        val actual = node.prettyPrint()
        Assertions.assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(ListObjectArgumentsProvider::class)
    fun `to list t node test`(list: List<DummyObject>, expected: ListTNode<DummyObject>) {
        val actual = list.toListTNode()?.toList()
        assertThat(actual).isEqualTo(expected.toList())
    }

    data class DummyObject(private val id: Int, private val name: String) {
        override fun toString(): String {
            return "{id: $id name: $name}"
        }
    }
}
