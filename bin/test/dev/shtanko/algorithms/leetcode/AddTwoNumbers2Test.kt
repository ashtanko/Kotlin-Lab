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

abstract class AddTwoNumbers2Test<out T : AddTwoNumbers2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(7, 2, 4, 3).toListNode(),
                listOf(5, 6, 4).toListNode(),
                listOf(7, 8, 0, 7),
            ),
            Arguments.of(
                listOf(2, 4, 3).toListNode(),
                listOf(5, 6, 4).toListNode(),
                listOf(8, 0, 7),
            ),
            Arguments.of(
                listOf(0).toListNode(),
                listOf(0).toListNode(),
                listOf(0),
            ),
            Arguments.of(
                listOf(9, 9, 9, 9, 9, 9, 9).toListNode(),
                listOf(9, 9, 9, 9).toListNode(),
                listOf(1, 0, 0, 0, 9, 9, 9, 8),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `add to array form test`(l1: ListNode?, l2: ListNode?, expected: List<Int>) {
        val actual = strategy.invoke(l1, l2).toListOrEmpty()
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class AddTwoNumbers2StackTest : AddTwoNumbers2Test<AddTwoNumbers2>(AddTwoNumbers2Stack())
class AddTwoNumbers2ReverseTest : AddTwoNumbers2Test<AddTwoNumbers2>(AddTwoNumbers2Reverse())
