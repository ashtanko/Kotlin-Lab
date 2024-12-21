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

abstract class RemoveZeroSumSublistsTest<out T : RemoveZeroSumSublists>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, -3, 3, 1).toListNode(),
                listOf(3, 1),
            ),
            Arguments.of(
                listOf(1, 2, 3, -3, 4).toListNode(),
                listOf(1, 2, 4),
            ),
            Arguments.of(
                listOf(1, 2, 3, -3, -2).toListNode(),
                listOf(1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `remove zero sum sublists test`(head: ListNode?, expected: List<Int>) {
        val actual = strategy(head)
        Assertions.assertThat(actual.toListOrEmpty()).isEqualTo(expected)
    }
}

class RemoveZeroSumSublistsMapTest : RemoveZeroSumSublistsTest<RemoveZeroSumSublists>(RemoveZeroSumSublistsMap())
class RemoveZeroSumSublistsTwoPassesTest :
    RemoveZeroSumSublistsTest<RemoveZeroSumSublists>(RemoveZeroSumSublistsTwoPasses())
