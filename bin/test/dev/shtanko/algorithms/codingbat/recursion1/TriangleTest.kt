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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class TriangleTest<out T : Triangle>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                0,
            ),
            Arguments.of(
                1,
                1,
            ),
            Arguments.of(
                2,
                3,
            ),
            Arguments.of(
                3,
                6,
            ),
            Arguments.of(
                4,
                10,
            ),
            Arguments.of(
                5,
                15,
            ),
            Arguments.of(
                6,
                21,
            ),
            Arguments.of(
                7,
                28,
            ),
            Arguments.of(
                8,
                36,
            ),
            Arguments.of(
                9,
                45,
            ),
            Arguments.of(
                10,
                55,
            ),
            Arguments.of(
                11,
                66,
            ),
            Arguments.of(
                12,
                78,
            ),
            Arguments.of(
                13,
                91,
            ),
            Arguments.of(
                14,
                105,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `triangle test`(rows: Int, expected: Int) {
        val actual = strategy(rows)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

internal class TriangleIterativeTest : TriangleTest<Triangle>(TriangleIterative())
internal class TriangleRecursiveTest : TriangleTest<Triangle>(TriangleRecursive())
internal class TriangleBottomUpTest : TriangleTest<Triangle>(TriangleBottomUp())
