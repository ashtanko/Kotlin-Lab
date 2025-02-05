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

abstract class AddTwoNumbersTest<out T : AddTwoNumbers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(2, 4, 3).toListNode(),
                listOf(5, 6, 4).toListNode(),
                listOf(7, 0, 8).toListNode(),
            ),
            Arguments.of(
                listOf(0).toListNode(),
                listOf(0).toListNode(),
                listOf(0).toListNode(),
            ),
            Arguments.of(
                listOf(9, 9, 9, 9, 9, 9, 9).toListNode(),
                listOf(9, 9, 9, 9).toListNode(),
                listOf(8, 9, 9, 9, 0, 0, 0, 1).toListNode(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `add two numbers test`(l1: ListNode?, l2: ListNode?, expected: ListNode?) {
        val actual = strategy.invoke(l1, l2)
        assertThat(actual).isEqualTo(expected)
    }
}

class AddTwoNumbersMathTest : AddTwoNumbersTest<AddTwoNumbers>(AddTwoNumbersMath())
