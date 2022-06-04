/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.ext

import java.math.BigInteger
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class BigIntegerExtTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    BigInteger.valueOf(2L),
                    BigInteger.valueOf(2L),
                ),
                BigInteger.valueOf(4L)
            ),
            Arguments.of(
                listOf(
                    0,
                    0,
                    0,
                    0
                ).map {
                    it.toBigInteger()
                },
                0.toBigInteger()
            ),
            Arguments.of(
                listOf(
                    0,
                ).map {
                    it.toBigInteger()
                },
                0.toBigInteger()
            ),
            Arguments.of(
                emptyList<BigInteger>(),
                BigInteger.ZERO
            ),
            Arguments.of(
                listOf(
                    -2,
                    2
                ).map {
                    it.toBigInteger()
                },
                0.toBigInteger()
            ),
            Arguments.of(
                listOf(
                    -2,
                    -2
                ).map {
                    it.toBigInteger()
                },
                (-4).toBigInteger()
            ),
            Arguments.of(
                (1..10_000).map {
                    it.toBigInteger()
                },
                50005000.toBigInteger()
            ),
            Arguments.of(
                (-10_000..10_000).map {
                    it.toBigInteger()
                },
                0.toBigInteger()
            ),
            Arguments.of(
                (50_000..100_000).map {
                    it.toBigInteger()
                },
                3750075000.toBigInteger()
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun sum(nums: List<BigInteger>, expected: BigInteger) {
        val actual = nums.sum()
        assertThat(actual).isEqualTo(expected)
    }
}
