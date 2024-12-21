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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SortListTest<out T : SortListStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListNode(
                    1,
                ),
                ListNode(
                    1,
                ),
            ),
            Arguments.of(
                ListNode(1).apply {
                    next = ListNode(2)
                },
                ListNode(1).apply {
                    next = ListNode(2)
                },
            ),
            Arguments.of(
                ListNode(2).apply {
                    next = ListNode(1)
                },
                ListNode(1).apply {
                    next = ListNode(2)
                },
            ),
            Arguments.of(
                ListNode(4).apply {
                    next = ListNode(2).apply {
                        next = ListNode(1).apply {
                            next = ListNode(3)
                        }
                    }
                },
                ListNode(1).apply {
                    next = ListNode(2).apply {
                        next = ListNode(3).apply {
                            next = ListNode(4)
                        }
                    }
                },
            ),
            Arguments.of(
                ListNode(-1).apply {
                    next = ListNode(5).apply {
                        next = ListNode(3).apply {
                            next = ListNode(4).apply {
                                next = ListNode(0)
                            }
                        }
                    }
                },
                ListNode(-1).apply {
                    next = ListNode(0).apply {
                        next = ListNode(3).apply {
                            next = ListNode(4).apply {
                                next = ListNode(5)
                            }
                        }
                    }
                },
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sort list test`(head: ListNode, expected: ListNode) {
        val actual = strategy.invoke(head)?.toList()
        assertThat(actual).containsAll(expected.toList())
    }
}

class TopDownMergeSortTest : SortListTest<TopDownMergeSort>(TopDownMergeSort())
class BottomUpMergeSortTest : SortListTest<BottomUpMergeSort>(BottomUpMergeSort())
