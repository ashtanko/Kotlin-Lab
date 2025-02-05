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

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindPivotIntegerTest<out T : FindPivotInteger>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                8,
                6,
            ),
            Arguments.of(
                1,
                1,
            ),
            Arguments.of(
                4,
                -1,
            ),
            Arguments.of(
                9,
                -1,
            ),
            Arguments.of(
                3,
                -1,
            ),
            Arguments.of(
                5,
                -1,
            ),
            Arguments.of(
                6,
                -1,
            ),
            Arguments.of(
                7,
                -1,
            ),
            Arguments.of(
                2,
                -1,
            ),
            Arguments.of(
                10,
                -1,
            ),
            Arguments.of(
                11,
                -1,
            ),
            Arguments.of(
                12,
                -1,
            ),
            Arguments.of(
                13,
                -1,
            ),
            Arguments.of(
                14,
                -1,
            ),
            Arguments.of(
                15,
                -1,
            ),
            Arguments.of(
                16,
                -1,
            ),
            Arguments.of(
                17,
                -1,
            ),
            Arguments.of(
                18,
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun pivotIntegerTest(num: Int, expected: Int) {
        val actual = strategy(num)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class FindPivotIntegerBruteForceTest : FindPivotIntegerTest<FindPivotInteger>(FindPivotIntegerBruteForce())
class FindPivotIntegerLookupTableTest : FindPivotIntegerTest<FindPivotInteger>(FindPivotIntegerLookupTable())
class FindPivotIntegerBSTest : FindPivotIntegerTest<FindPivotInteger>(FindPivotIntegerBS())
class FindPivotIntegerTwoPointerTest : FindPivotIntegerTest<FindPivotInteger>(FindPivotIntegerTwoPointer())
class FindPivotIntegerMathTest : FindPivotIntegerTest<FindPivotInteger>(FindPivotIntegerMath())
