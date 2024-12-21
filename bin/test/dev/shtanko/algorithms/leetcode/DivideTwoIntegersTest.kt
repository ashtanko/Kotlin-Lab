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

abstract class DivideTwoIntegersTest<out T : DivideTwoIntegers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                10,
                3,
                3,
            ),
            Arguments.of(
                7,
                -3,
                -2,
            ),
            Arguments.of(
                0,
                1,
                0,
            ),
            Arguments.of(
                1,
                1,
                1,
            ),
            Arguments.of(
                Int.MIN_VALUE,
                -1,
                Int.MAX_VALUE,
            ),
            Arguments.of(
                Int.MIN_VALUE,
                1,
                Int.MIN_VALUE,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                1,
                Int.MAX_VALUE,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                2,
                1073741823,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                3,
                715827882,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                4,
                536870911,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                5,
                429496729,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                6,
                357913941,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                7,
                306783378,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `divide two integers test`(dividend: Int, divisor: Int, expected: Int) {
        val actual = strategy.divide(dividend, divisor)
        assertThat(actual).isEqualTo(expected)
    }
}

class DivideIntegersBitShiftingTest :
    DivideTwoIntegersTest<DivideIntegersBitShifting>(DivideIntegersBitShifting())

class DivideIntegersBitShiftingTest2 :
    DivideTwoIntegersTest<DivideIntegersBitShifting2>(DivideIntegersBitShifting2())
