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
import kotlin.streams.toList
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class NullableListNodeTest {

    private class InputToListArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                NullableListNode(
                    1,
                ),
                listOf(1),
            ),
            Arguments.of(
                NullableListNode(1).apply {
                    next = NullableListNode(2)
                },
                listOf(1, 2),
            ),
            Arguments.of(
                NullableListNode(4).apply {
                    next = NullableListNode(2).apply {
                        next = NullableListNode(1).apply {
                            next = NullableListNode(3)
                        }
                    }
                },
                listOf(4, 2, 1, 3),
            ),
        )
    }

    private class ToListArgs : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf<Int>(),
                NullableListNode(),
            ),
            Arguments.of(
                listOf(1),
                NullableListNode(1),
            ),
            Arguments.of(
                listOf(1, 2),
                NullableListNode(1).apply {
                    next = NullableListNode(2)
                },
            ),
            Arguments.of(
                listOf(1, 2, 3),
                NullableListNode(1).apply {
                    next = NullableListNode(2).apply {
                        next = NullableListNode(3)
                    }
                },
            ),
            Arguments.of(
                listOf(1, 2, 3, 4),
                NullableListNode(1).apply {
                    next = NullableListNode(2).apply {
                        next = NullableListNode(3).apply {
                            next = NullableListNode(4)
                        }
                    }
                },
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

    @ParameterizedTest
    @ArgumentsSource(ToListArgs::class)
    fun `to list node test`(list: List<Int>, expected: NullableListNode) {
        val actual = list.toNullableListNode().toList()
        Assertions.assertThat(actual).isEqualTo(expected.toList())
    }

    @ArgumentsSource(InputToListNullableArgumentsProvider::class)
    @ParameterizedTest
    fun `list node to list nullable test`(head: NullableListNode?, expected: List<Int>) {
        val actual = head.toListOrEmpty()
        Assertions.assertThat(actual).containsAll(expected)
    }
}
