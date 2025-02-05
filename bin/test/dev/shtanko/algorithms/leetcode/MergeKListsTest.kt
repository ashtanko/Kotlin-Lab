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

abstract class MergeKListsTest<out T : MergeKLists>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    listOf(1, 4, 5).toListNode(),
                    listOf(1, 3, 4).toListNode(),
                    listOf(2, 6).toListNode(),
                ),
                listOf(1, 1, 2, 3, 4, 4, 5, 6),
            ),
            Arguments.of(
                emptyArray<ListNode>(),
                emptyList<Int>(),
            ),
            Arguments.of(
                arrayOf<ListNode?>(
                    null,
                ),
                emptyList<Int>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `merge k lists test`(lists: Array<ListNode?>, expected: List<Int>) {
        val actual = strategy.invoke(lists).toListOrEmpty()
        assertThat(actual).isEqualTo(expected)
    }
}

class MergeKListsPQTest : MergeKListsTest<MergeKLists>(MergeKListsPQ())
