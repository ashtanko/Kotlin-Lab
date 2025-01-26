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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class MinimumAbsDifferenceTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `minimum abs difference test`(arr: IntArray, expected: List<List<Int>>) {
        val actual = arr.minimumAbsDifference()
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `minimum abs difference 2 test`(arr: IntArray, expected: List<List<Int>>) {
        val actual = arr.minimumAbsDifference2()
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 2, 1, 3),
                listOf(
                    listOf(1, 2),
                    listOf(2, 3),
                    listOf(3, 4),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 3, 6, 10, 15),
                listOf(
                    listOf(1, 3),
                ),
            ),
            Arguments.of(
                intArrayOf(3, 8, -10, 23, 19, -4, -14, 27),
                listOf(
                    listOf(-14, -10),
                    listOf(19, 23),
                    listOf(23, 27),
                ),
            ),
        )
    }
}
