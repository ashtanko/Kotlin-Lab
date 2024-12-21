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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import kotlin.streams.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class ListNodeTest {

    private class PrettyPrintArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListNode(1),
                "1",
            ),
            Arguments.of(
                ListNode(1).apply {
                    next = ListNode(2).apply {
                        next = ListNode(3)
                    }
                },
                "1->2->3",
            ),
            Arguments.of(
                getTwentyItemsList(),
                "0->1->2->3->4->5->6->7->8->9->10->11->12->13->14->15->16->17->18->19",
            ),
        )

        private fun getTwentyItemsList(): ListNode {
            val root = ListNode(0)
            var ptr: ListNode? = root
            for (i in 1 until 20) {
                ptr?.next = ListNode(i)
                ptr = ptr?.next
            }
            return root
        }
    }

    private class InputToListArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListNode(
                    1,
                ),
                listOf(1),
            ),
            Arguments.of(
                ListNode(1).apply {
                    next = ListNode(2)
                },
                listOf(1, 2),
            ),
            Arguments.of(
                ListNode(4).apply {
                    next = ListNode(2).apply {
                        next = ListNode(1).apply {
                            next = ListNode(3)
                        }
                    }
                },
                listOf(4, 2, 1, 3),
            ),
        )
    }

    private class InputToListNullableArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
            InputToListArgumentsProvider().provideArguments(context).toList().toMutableList().apply {
                add(
                    Arguments.of(
                        null,
                        emptyList<Int>(),
                    ),
                )
            }.stream()
    }

    private class ToListArgs : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf<Int>(),
                ListNode(),
            ),
            Arguments.of(
                listOf(1),
                ListNode(1),
            ),
            Arguments.of(
                listOf(1, 2),
                ListNode(1).apply {
                    next = ListNode(2)
                },
            ),
            Arguments.of(
                listOf(1, 2, 3),
                ListNode(1).apply {
                    next = ListNode(2).apply {
                        next = ListNode(3)
                    }
                },
            ),
            Arguments.of(
                listOf(1, 2, 3, 4),
                ListNode(1).apply {
                    next = ListNode(2).apply {
                        next = ListNode(3).apply {
                            next = ListNode(4)
                        }
                    }
                },
            ),
        )
    }

    private class ReverseListArgs : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, 3, 4, 5, 6, 7).toListNode(),
                listOf(1, 2, 3, 4, 5, 6, 7).reversed(),
            ),
            Arguments.of(
                listOf(1).toListNode(),
                listOf(1).reversed(),
            ),
            Arguments.of(
                listOf(1, 2).toListNode(),
                listOf(1, 2).reversed(),
            ),
            Arguments.of(
                listOf<Int>().toListNode(),
                listOf<Int>().reversed(),
            ),
            Arguments.of(
                listOf(-1, -2).toListNode(),
                listOf(-1, -2).reversed(),
            ),
        )
    }

    private class ZipListArgs : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 3).toListNode(),
                listOf(2, 4).toListNode(),
                listOf(1, 2, 3, 4).toListNode(),
            ),
            Arguments.of(
                listOf<Int>().toListNode(),
                listOf(2, 4, 6).toListNode(),
                listOf(0, 2, 4, 6).toListNode(),
            ),
        )
    }

    private class IntArrayToListNodeArgs : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                ListNode(),
            ),
            Arguments.of(
                intArrayOf(1),
                ListNode(1),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                ListNode(1).apply {
                    next = ListNode(2)
                },
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                ListNode(1).apply {
                    next = ListNode(2).apply {
                        next = ListNode(3)
                    }
                },
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                ListNode(1).apply {
                    next = ListNode(2).apply {
                        next = ListNode(3).apply {
                            next = ListNode(4)
                        }
                    }
                },
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(ZipListArgs::class)
    fun `zip node test`(head1: ListNode, head2: ListNode, expected: ListNode) {
        val actual = head1.zip(head2).toList()
        assertThat(actual).isEqualTo(expected.toList())
    }

    @ParameterizedTest
    @ArgumentsSource(ToListArgs::class)
    fun `int list to list node test`(list: List<Int>, expected: ListNode) {
        val actual = list.toListNode().toList()
        assertThat(actual).isEqualTo(expected.toList())
    }

    @ParameterizedTest
    @ArgumentsSource(IntArrayToListNodeArgs::class)
    fun `int array to list node test`(array: IntArray, expected: ListNode) {
        val actual = array.toListNode().toIntArray()
        assertThat(actual).isEqualTo(expected.toIntArray())
    }

    @ArgumentsSource(PrettyPrintArgumentsProvider::class)
    @ParameterizedTest
    fun `print list node test`(node: ListNode, expected: String) {
        val actual = node.prettyPrinted()
        assertEquals(expected, actual)
    }

    @ArgumentsSource(InputToListArgumentsProvider::class)
    @ParameterizedTest
    fun `list node to list test`(head: ListNode, expected: List<Int>) {
        val actual = head.toList()
        assertThat(actual).containsAll(expected)
    }

    @ArgumentsSource(InputToListNullableArgumentsProvider::class)
    @ParameterizedTest
    fun `list node to list nullable test`(head: ListNode?, expected: List<Int>) {
        val actual = head.toListOrEmpty()
        assertThat(actual).containsAll(expected)
    }

    @ArgumentsSource(ReverseListArgs::class)
    @ParameterizedTest
    fun `reverse list test`(head: ListNode, expected: List<Int>) {
        val actual = head.reverseList()?.toList()
        assertThat(actual).containsAll(expected)
    }

    @Test
    fun `when list node is not null, should return true`() {
        val node = ListNode(1)
        assertThat(node.isNotNull()).isTrue()
    }

    @Test
    fun `when list node is null, should return false`() {
        val node: ListNode? = null
        assertThat(node.isNotNull()).isFalse()
    }

    @Test
    fun `addIfNotNull, when node is not null, should add node to collection`() {
        val node = ListNode(1)
        val collection = mutableListOf<ListNode>()

        collection.addIfNotNull(node)

        assertEquals(1, collection.size)
        assertEquals(node, collection[0])
    }

    @Test
    fun `addIfNotNull, when node is null, should not add to collection`() {
        val node: ListNode? = null
        val collection = mutableListOf<ListNode>()

        collection.addIfNotNull(node)

        assertEquals(0, collection.size)
    }
}
