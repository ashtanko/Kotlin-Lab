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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SumOfSquareNumbersTest<out T : SumOfSquareNumbers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                5,
                true,
            ),
            Arguments.of(
                3,
                false,
            ),
            Arguments.of(
                0,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `judge square sum test`(target: Int, expected: Boolean) {
        val actual = strategy(target)
        assertThat(actual).isEqualTo(expected)
    }
}

class SumOfSquareNumbersBruteForceTest : SumOfSquareNumbersTest<SumOfSquareNumbers>(SumOfSquareNumbersBruteForce())

class SumOfSquareNumbersBetterBruteForceTest :
    SumOfSquareNumbersTest<SumOfSquareNumbers>(SumOfSquareNumbersBetterBruteForce())

class SumOfSquareNumbersSqrtTest : SumOfSquareNumbersTest<SumOfSquareNumbers>(SumOfSquareNumbersSqrt())

class SumOfSquareNumbersBinarySearchTest : SumOfSquareNumbersTest<SumOfSquareNumbers>(SumOfSquareNumbersBinarySearch())

class SumOfSquareNumbersFermatTheoremTest :
    SumOfSquareNumbersTest<SumOfSquareNumbers>(SumOfSquareNumbersFermatTheorem())
