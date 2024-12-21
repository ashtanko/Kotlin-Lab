/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.utils.assertListNodeEquals
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MergeInBetweenLinkedListsTest<out T : MergeInBetweenLinkedLists>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(10, 1, 13, 6, 9, 5).toListNode(),
                3,
                4,
                listOf(1000000, 1000001, 1000002).toListNode(),
                listOf(10, 1, 13, 1000000, 1000001, 1000002, 5).toListNode(),
            ),
            Arguments.of(
                listOf(0, 1, 2, 3, 4, 5, 6).toListNode(),
                2,
                5,
                listOf(1000000, 1000001, 1000002, 1000003, 1000004).toListNode(),
                listOf(0, 1, 1000000, 1000001, 1000002, 1000003, 1000004, 6).toListNode(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun mergeInBetweenTest(firstList: ListNode?, start: Int, end: Int, secondList: ListNode?, expected: ListNode) {
        val actual = strategy(firstList, start, end, secondList)
        assertListNodeEquals(expected, actual)
        assertThat(actual).isEqualTo(expected)
    }
}

class MergeInBetweenLinkedListsArrayTest :
    MergeInBetweenLinkedListsTest<MergeInBetweenLinkedLists>(MergeInBetweenLinkedListsArray())

class MergeInBetweenLinkedListsTwoPointerTest :
    MergeInBetweenLinkedListsTest<MergeInBetweenLinkedLists>(MergeInBetweenLinkedListsTwoPointer())
