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

package dev.shtanko.utils

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class HumanReadableBytesTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                "0 B",
            ),
            Arguments.of(
                27,
                "27 B",
            ),
            Arguments.of(
                999,
                "999 B",
            ),
            Arguments.of(
                1000,
                "1000 B",
            ),
            Arguments.of(
                1023,
                "1023 B",
            ),
            Arguments.of(
                1024,
                "1.0 KiB",
            ),
            Arguments.of(
                1728,
                "1.7 KiB",
            ),
            Arguments.of(
                110592,
                "108.0 KiB",
            ),
            Arguments.of(
                7077888,
                "6.8 MiB",
            ),
            Arguments.of(
                452984832,
                "432.0 MiB",
            ),
            Arguments.of(
                28991029248,
                "27.0 GiB",
            ),
            Arguments.of(
                1855425871872,
                "1.7 TiB",
            ),
            Arguments.of(
                9223372036854775807,
                "8.0 EiB",
            ),
            Arguments.of(
                Long.MAX_VALUE,
                "8.0 EiB",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `human readable byte count binary test`(bytes: Long, expected: String) {
        val actual = bytes.toHumanReadableByteCountBin()
        assertThat(actual).isEqualTo(expected)
    }
}
