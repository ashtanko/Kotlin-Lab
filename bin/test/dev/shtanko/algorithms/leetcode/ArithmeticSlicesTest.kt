/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class ArithmeticSlicesTest<out T : ArithmeticSlices>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                0,
            ),
            Arguments.of(
                intArrayOf(11),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                28,
            ),
            Arguments.of(
                intArrayOf(-1, -2, -3, -4),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                10,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                15,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
                21,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                28,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                36,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
                45,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
                55,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13),
                66,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14),
                78,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
                91,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `number of arithmetic slices test`(arr: IntArray, expected: Int) {
        val actual = strategy.invoke(arr)
        assertThat(actual).isEqualTo(expected)
    }
}

class ArSlicesBruteForceTest : ArithmeticSlicesTest<ArSlicesBruteForce>(ArSlicesBruteForce())
class ArSlicesBetterBruteForceTest : ArithmeticSlicesTest<ArSlicesBetterBruteForce>(ArSlicesBetterBruteForce())
class ArSlicesRecursionTest : ArithmeticSlicesTest<ArSlicesRecursion>(ArSlicesRecursion())
class ArSlicesDPTest : ArithmeticSlicesTest<ArSlicesDP>(ArSlicesDP())
class ArSlicesConstantSpaceDPTest : ArithmeticSlicesTest<ArSlicesConstantSpaceDP>(ArSlicesConstantSpaceDP())
class ArSlicesFormulaTest : ArithmeticSlicesTest<ArSlicesFormula>(ArSlicesFormula())
