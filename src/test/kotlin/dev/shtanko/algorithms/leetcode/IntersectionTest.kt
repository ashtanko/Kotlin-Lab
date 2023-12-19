/*
 * Copyright 2020 Oleksii Shtanko
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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AbstractIntersectionTest<out T : IntersectionStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 2, 1) to intArrayOf(2, 2),
                intArrayOf(2),
            ),
            Arguments.of(
                intArrayOf(4, 9, 5) to intArrayOf(9, 4, 9, 8, 4),
                intArrayOf(4, 9),
            ),
            Arguments.of(
                intArrayOf(4, 8) to intArrayOf(15, 16, 23, 4),
                intArrayOf(4),
            ),
            Arguments.of(
                intArrayOf(4, 8) to intArrayOf(15, 16, 23, 42),
                intArrayOf(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `intersection test`(pair: Pair<IntArray, IntArray>, expected: IntArray) {
        val actual = strategy.invoke(pair)
        assertArrayEquals(expected, actual)
    }
}

class IntersectionTwoSetsTest : AbstractIntersectionTest<IntersectionTwoSets>(IntersectionTwoSets())

class IntersectionTwoPointersTest :
    AbstractIntersectionTest<IntersectionTwoPointers>(IntersectionTwoPointers())

class IntersectionBinarySearchTest :
    AbstractIntersectionTest<IntersectionBinarySearch>(IntersectionBinarySearch())
