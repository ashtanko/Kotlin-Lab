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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class LinkedListCycleTest<out T : LinkedListCycle>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        val first = ListNode(1)
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListNode(3).apply {
                    val node = ListNode(2)
                    next = node.apply {
                        next = ListNode(0).apply {
                            next = ListNode(-4).apply {
                                next = node
                            }
                        }
                    }
                },
                true,
            ),
            Arguments.of(
                first.apply {
                    next = ListNode(2).apply {
                        next = first
                    }
                },
                true,
            ),
            Arguments.of(
                listOf(1).toListNode(),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `has cycle test`(head: ListNode?, expected: Boolean) {
        val actual = strategy(head)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class LinkedListCycleSolutionTest : LinkedListCycleTest<LinkedListCycle>(LinkedListCycleSolution())
