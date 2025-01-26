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
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SwapNodesTest<out T : SwapNodes>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, 3, 4, 5).toListNode(),
                2,
                listOf(1, 4, 3, 2, 5),
            ),
            Arguments.of(
                listOf(7, 9, 6, 6, 7, 8, 3, 0, 9, 5).toListNode(),
                5,
                listOf(7, 9, 6, 6, 8, 7, 3, 0, 9, 5),
            ),
            Arguments.of(
                listOf(1).toListNode(),
                1,
                listOf(1),
            ),
            Arguments.of(
                listOf(1, 2).toListNode(),
                1,
                listOf(2, 1),
            ),
            Arguments.of(
                listOf(1, 2, 3).toListNode(),
                2,
                listOf(1, 2, 3),
            ),
            Arguments.of(
                listOf(1, 2, 3, 4).toListNode(),
                2,
                listOf(1, 3, 2, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `swap nodes test`(head: ListNode?, k: Int, expected: List<Int>) {
        val actual = strategy.invoke(head, k).toListOrEmpty()
        assertThat(actual).isEqualTo(expected)
    }
}

class SwapNodesTwoPointersTest : SwapNodesTest<SwapNodes>(SwapNodesTwoPointers())
