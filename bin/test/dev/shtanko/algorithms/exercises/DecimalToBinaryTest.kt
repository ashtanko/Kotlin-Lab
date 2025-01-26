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
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class DecimalToBinaryTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(1, "1"),
            Arguments.of(2, "10"),
            Arguments.of(3, "11"),
            Arguments.of(4, "100"),
            Arguments.of(5, "101"),
            Arguments.of(6, "110"),
            Arguments.of(7, "111"),
            Arguments.of(8, "1000"),
            Arguments.of(9, "1001"),
            Arguments.of(10, "1010"),
            Arguments.of(100, "1100100"),
            Arguments.of(100, "1100100"),
            Arguments.of(101, "1100101"),
            Arguments.of(102, "1100110"),
            Arguments.of(103, "1100111"),
            Arguments.of(104, "1101000"),
            Arguments.of(105, "1101001"),
            Arguments.of(106, "1101010"),
            Arguments.of(107, "1101011"),
            Arguments.of(108, "1101100"),
            Arguments.of(109, "1101101"),
            Arguments.of(110, "1101110"),
            Arguments.of(500, "111110100"),
            Arguments.of(900, "1110000100"),
            Arguments.of(1000, "1111101000"),
            Arguments.of(9999, "10011100001111"),
            Arguments.of(10_000, "10011100010000"),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `decimal to binary string test`(num: Int, expected: String) {
        val actual = DecimalToBinary().perform(num)
        assertThat(actual, equalTo(expected))
    }
}
