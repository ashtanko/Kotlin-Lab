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

abstract class TriangleTest<out T : Triangle>(private val solution: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    listOf(2),
                    listOf(3, 4),
                    listOf(6, 5, 7),
                    listOf(4, 1, 8, 3),
                ),
                11,
            ),
            Arguments.of(
                listOf(
                    listOf(-10),
                ),
                -10,
            ),
            Arguments.of(
                listOf(
                    listOf(-1),
                    listOf(2, 3),
                    listOf(1, -1, -3),
                ),
                -1,
            ),
            Arguments.of(
                listOf(
                    listOf(1),
                    listOf(2, 3),
                    listOf(4, 5, 6),
                    listOf(7, 8, 9, 10),
                ),
                14,
            ),
            Arguments.of(
                listOf(
                    listOf(1),
                ),
                1,
            ),
            Arguments.of(
                listOf(
                    listOf(1),
                    listOf(2, 3),
                ),
                3,
            ),
            Arguments.of(
                listOf(
                    listOf(1),
                    listOf(2, 3),
                    listOf(4, 5, 6),
                ),
                7,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `minimum total test`(triangle: List<List<Int>>, expected: Int) {
        val actual = solution.invoke(triangle)
        assertEquals(expected, actual)
    }
}

class TriangleBottomUpTest : TriangleTest<TriangleBottomUp>(TriangleBottomUp())
class TriangleAuxiliarySpaceTest : TriangleTest<TriangleAuxiliarySpace>(TriangleAuxiliarySpace())
class TriangleUpsideDownTest : TriangleTest<TriangleUpsideDown>(TriangleUpsideDown())
class TriangleMemoizationTest : TriangleTest<TriangleMemoization>(TriangleMemoization())
