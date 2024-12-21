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

abstract class RemoveElementsTest<out T : RemoveElements>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, 6, 3, 4, 5, 6).toListNode(),
                6,
                listOf(1, 2, 3, 4, 5).toListNode(),
            ),
            Arguments.of(
                ListNode(),
                1,
                ListNode(),
            ),
            Arguments.of(
                listOf(7, 7, 7, 7).toListNode(),
                7,
                null,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `remove elements test`(head: ListNode?, value: Int, expected: ListNode?) {
        val actual = strategy.invoke(head, value)
        assertThat(actual).isEqualTo(expected)
    }
}

class RemoveElementsIterativeTest : RemoveElementsTest<RemoveElements>(RemoveElementsIterative())
class RemoveElementsRecursiveTest : RemoveElementsTest<RemoveElements>(RemoveElementsRecursive())
