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

abstract class AbstractDecodeWays2StrategyTest<out T : DecodeWays2Strategy>(private val strategy: DecodeWays2Strategy) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("", 0),
            Arguments.of("0", 0),
            Arguments.of("1", 1),
            Arguments.of("2", 1),
            Arguments.of("4", 1),
            Arguments.of("7", 1),
            Arguments.of("10", 1),
            Arguments.of("11", 2),
            Arguments.of("*", 9),
            Arguments.of("1*", 18),
            Arguments.of("12*", 24),
            Arguments.of("22", 2),
            Arguments.of("**", 96),
            Arguments.of("62", 1),
            Arguments.of("26", 2),
            Arguments.of("27", 1),
            Arguments.of("266", 2),
            Arguments.of("2266", 3),
            Arguments.of("2*", 15),
            Arguments.of("*6", 11),
            Arguments.of("*7", 10),
            Arguments.of("3*7", 10),
            Arguments.of("1*7", 19),
            Arguments.of("2*7", 16),
            Arguments.of("2*6", 17),
            Arguments.of("2*9", 16),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `simple test`(str: String, expected: Int) {
        val actual = strategy.invoke(str)
        assertEquals(expected, actual)
    }
}

class DecodeWays2RecursionWithMemoizationTest :
    AbstractDecodeWays2StrategyTest<DecodeWays2RecursionWithMemoization>(DecodeWays2RecursionWithMemoization())

class DecodeWays2DynamicProgrammingTest :
    AbstractDecodeWays2StrategyTest<DecodeWays2DynamicProgramming>(DecodeWays2DynamicProgramming())

class DecodeWays2ConstantSpaceDynamicProgrammingTest :
    AbstractDecodeWays2StrategyTest<DecodeWays2ConstantSpaceDynamicProgramming>(
        DecodeWays2ConstantSpaceDynamicProgramming(),
    )
