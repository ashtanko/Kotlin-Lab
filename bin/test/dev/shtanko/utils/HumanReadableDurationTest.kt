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

class HumanReadableDurationTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0L,
                "Runtime: 0 ms",
            ),
            Arguments.of(
                1000000L,
                "Runtime: 1 ms",
            ),
            Arguments.of(
                2000000L,
                "Runtime: 2 ms",
            ),
            Arguments.of(
                12000000,
                "Runtime: 12 ms",
            ),
            Arguments.of(
                56000000,
                "Runtime: 56 ms",
            ),
            Arguments.of(
                65700000000,
                "Runtime: 65,700 ms",
            ),
            Arguments.of(
                99900000000,
                "Runtime: 99,900 ms",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `to human readable duration test`(duration: Long, expected: String) {
        val actual = duration.toHumanReadableDuration()
        assertThat(actual).isEqualTo(expected)
    }
}
