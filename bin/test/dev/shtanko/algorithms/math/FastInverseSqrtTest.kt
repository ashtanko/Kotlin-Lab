/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.algorithms.math

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FastInverseSqrtTest<out T : FastInverseSqrt>(private val strategy: T) {
    private class InputDoubleArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1.0,
                5.596801445214226E-231,
            ),
            Arguments.of(
                9.0,
                1.6931626605548626E-230,
            ),
            Arguments.of(
                2.0,
                7.932771325819902E-231,
            ),
            Arguments.of(
                4522.0,
                3.834678493447521E-229,
            ),
            Arguments.of(
                3.834678493447521E-229,
                0.0,
            ),
            Arguments.of(
                3.834,
                1.0988485456013884E-230,
            ),
            Arguments.of(
                0.70710678,
                4.654240942784563E-231,
            ),
        )
    }

    private class InputFloatArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1f,
                3.2492518E-29f,
            ),
            Arguments.of(
                2f,
                2.3026187E-29f,
            ),
            Arguments.of(
                4522f,
                4.923122E-31f,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputDoubleArgumentsProvider::class)
    fun `inverse sqrt double test`(num: Double, expected: Double) {
        val actual = strategy.inverseSqrt(num)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputFloatArgumentsProvider::class)
    fun `inverse sqrt float test`(num: Float, expected: Float) {
        val actual = strategy.inverseSqrt(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class FastInverseSqrtImplTest : FastInverseSqrtTest<FastInverseSqrt>(FastInverseSqrtImpl())
