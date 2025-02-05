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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class HumanReadableByteCountSITest {
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
                "1.0 kB",
            ),
            Arguments.of(
                1023,
                "1.0 kB",
            ),
            Arguments.of(
                1024,
                "1.0 kB",
            ),
            Arguments.of(
                1728,
                "1.7 kB",
            ),
            Arguments.of(
                110592,
                "110.6 kB",
            ),
            Arguments.of(
                7077888,
                "7.1 MB",
            ),
            Arguments.of(
                452984832,
                "453.0 MB",
            ),
            Arguments.of(
                28991029248,
                "29.0 GB",
            ),
            Arguments.of(
                1855425871872,
                "1.9 TB",
            ),
            Arguments.of(
                9223372036854775807,
                "9.2 EB",
            ),
            Arguments.of(
                Long.MAX_VALUE,
                "9.2 EB",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `human readable byte count binary SI test`(bytes: Long, expected: String) {
        val actual = bytes.toHumanReadableByteCountSI()
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
