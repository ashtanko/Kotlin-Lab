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

package dev.shtanko.algorithms.exercises

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class MaxPowerOfTwoTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 0),
            Arguments.of(2, 2),
            Arguments.of(5, 4),
            Arguments.of(8, 8),
            Arguments.of(9, 8),
            Arguments.of(20, 16),
            Arguments.of(55, 32),
            Arguments.of(114, 64),
            Arguments.of(220, 128),
            Arguments.of(467, 256),
            Arguments.of(879, 512),
            Arguments.of(1489, 1024),
            Arguments.of(3378, 2048),
            Arguments.of(4815, 4096),
            Arguments.of(12_346, 8192),
            Arguments.of(20_896, 16_384),
        )
    }

    internal class InputDecomposeArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, listOf<Int>()),
            Arguments.of(1, listOf(1)),
            Arguments.of(2, listOf(2)),
            Arguments.of(5, listOf(4, 1)),
            Arguments.of(8, listOf(8)),
            Arguments.of(9, listOf(8, 1)),
            Arguments.of(20, listOf(16, 4)),
            Arguments.of(55, listOf(32, 16, 4, 2, 1)),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max power of two test`(i: Int, expected: Int) {
        val actual = MaxPowerOfTwo().perform(i)
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputDecomposeArgumentsProvider::class)
    fun `decompose into the sum powers of two test`(num: Int, expected: List<Int>) {
        val actual = MaxPowerOfTwo().decompose(num)
        assertThat(actual, `is`(expected))
    }
}
