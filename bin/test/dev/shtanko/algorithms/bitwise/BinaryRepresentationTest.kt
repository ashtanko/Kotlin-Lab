/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.bitwise

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class BinaryRepresentationTest {
    private class LongArgs : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                7L,
                "00000000000000000000000000000111",
            ),
            Arguments.of(
                4L,
                "00000000000000000000000000000100",
            ),
            Arguments.of(
                0L,
                "00000000000000000000000000000000",
            ),
            Arguments.of(
                1L,
                "00000000000000000000000000000001",
            ),
            Arguments.of(
                2L,
                "00000000000000000000000000000010",
            ),
            Arguments.of(
                234L,
                "00000000000000000000000011101010",
            ),
            Arguments.of(
                2342354676L,
                "00001011100111010111111011110100",
            ),
            Arguments.of(
                Long.MAX_VALUE,
                "01111111111111111111111111111111",
            ),
            Arguments.of(
                Long.MIN_VALUE,
                "00000000000000000000000000000000",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(LongArgs::class)
    fun `long to bin string test`(num: Long, expected: String) {
        val actual = num.bin()
        assertThat(actual).isEqualTo(expected)
    }
}
