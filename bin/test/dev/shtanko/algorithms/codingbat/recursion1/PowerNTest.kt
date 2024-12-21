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

internal abstract class PowerNTest<out T : PowerN>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                1,
                3,
            ),
            Arguments.of(
                2,
                1,
                2,
            ),
            Arguments.of(
                3,
                2,
                9,
            ),
            Arguments.of(
                3,
                3,
                27,
            ),
            Arguments.of(
                2,
                2,
                4,
            ),
            Arguments.of(
                2,
                3,
                8,
            ),
            Arguments.of(
                2,
                4,
                16,
            ),
            Arguments.of(
                2,
                5,
                32,
            ),
            Arguments.of(
                10,
                1,
                10,
            ),
            Arguments.of(
                10,
                2,
                100,
            ),
            Arguments.of(
                10,
                3,
                1000,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `power n test`(base: Int, num: Int, expected: Int) {
        val actual = strategy(base, num)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

internal class PowerNIterativeTest : PowerNTest<PowerN>(PowerNIterative())
internal class PowerNRecursiveTest : PowerNTest<PowerN>(PowerNRecursive())
internal class PowerNMemoTest : PowerNTest<PowerN>(PowerNMemo())
