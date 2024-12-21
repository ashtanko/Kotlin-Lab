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

abstract class MergeNodesInBetweenZerosTest<out T : MergeNodesInBetweenZeros>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(0, 3, 1, 0, 4, 5, 2, 0).toListNode(),
                intArrayOf(4, 11).toListNode(),
            ),
            Arguments.of(
                intArrayOf(0, 1, 0, 3, 0, 2, 2, 0).toListNode(),
                intArrayOf(1, 3, 4).toListNode(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun mergeNodesTest(head: ListNode?, expected: ListNode) {
        val actual = strategy(head)
        assertListNodeEquals(expected, actual)
        assertThat(actual).isEqualTo(expected)
    }
}

class MergeNodesTwoPointerTest : MergeNodesInBetweenZerosTest<MergeNodesInBetweenZeros>(MergeNodesTwoPointer())
class MergeNodesRecursionTest : MergeNodesInBetweenZerosTest<MergeNodesInBetweenZeros>(MergeNodesRecursion())
