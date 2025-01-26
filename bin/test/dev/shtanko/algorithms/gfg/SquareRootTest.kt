/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.gfg

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class SquareRootTest {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                5L,
                2L,
            ),
            Arguments.of(
                4L,
                2L,
            ),
            Arguments.of(
                9L,
                3L,
            ),
            Arguments.of(
                16L,
                4L,
            ),
            Arguments.of(
                25L,
                5L,
            ),
            Arguments.of(
                36L,
                6L,
            ),
            Arguments.of(
                49L,
                7L,
            ),
            Arguments.of(
                64L,
                8L,
            ),
            Arguments.of(
                81L,
                9L,
            ),
            Arguments.of(
                100L,
                10L,
            ),
            Arguments.of(
                121L,
                11L,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `square root test`(num: Long, expected: Long) {
        val actual = SquareRoot.perform(num)
        assertThat(actual).isEqualTo(expected)
    }
}
