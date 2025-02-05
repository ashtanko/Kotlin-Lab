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
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class CombinationSumTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 3, 6, 7),
                7,
                listOf(
                    listOf(2, 2, 3),
                    listOf(7),
                ),
            ),
            Arguments.of(
                intArrayOf(2, 3, 5),
                8,
                listOf(
                    listOf(2, 2, 2, 2),
                    listOf(2, 3, 3),
                    listOf(3, 5),
                ),
            ),
            Arguments.of(
                intArrayOf(2),
                1,
                listOf<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(1),
                1,
                listOf(
                    listOf(1),
                ),
            ),
            Arguments.of(
                intArrayOf(1),
                2,
                listOf(
                    listOf(1, 1),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                4,
                listOf(
                    listOf(1, 1, 1, 1),
                    listOf(1, 1, 2),
                    listOf(2, 2),
                ),
            ),
        )
    }

    @ExperimentalStdlibApi
    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `combination sum test`(candidates: IntArray, target: Int, expected: List<List<Int>>) {
        val actual = combinationSum(candidates, target)
        assertThat(actual, `is`(expected))
        assertEquals(expected, actual)
    }
}
