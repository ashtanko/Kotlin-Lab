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

abstract class AbstractSortByParityTest<out T : AbstractSortByParity>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sort by parity test`(arr: IntArray, expected: IntArray) {
        val actual = strategy.invoke(arr)
        assertArrayEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 2, 5, 7),
                intArrayOf(4, 5, 2, 7),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(2, 1, 4, 3),
            ),
            Arguments.of(
                intArrayOf(4, 8, 15, 16, 23, 41),
                intArrayOf(4, 15, 8, 23, 16, 41),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
        )
    }
}

class SortByParityTwoPassTest : AbstractSortByParityTest<AbstractSortByParity>(SortByParityTwoPass())

class SortByParityHeadsTest : AbstractSortByParityTest<AbstractSortByParity>(SortByParityHeads())

class SortByParityStraightForwardTest : AbstractSortByParityTest<AbstractSortByParity>(SortByParityStraightForward())
