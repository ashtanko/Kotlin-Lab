/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.extensions

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

class NumberTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                true,
            ),
            Arguments.of(
                9L,
                true,
            ),
            Arguments.of(
                16.0,
                true,
            ),
            Arguments.of(
                25f,
                true,
            ),
            Arguments.of(
                36,
                true,
            ),
            Arguments.of(
                49,
                true,
            ),
            Arguments.of(
                64,
                true,
            ),
            Arguments.of(
                2,
                false,
            ),
            Arguments.of(
                3,
                false,
            ),
            Arguments.of(
                5,
                false,
            ),
            Arguments.of(
                6,
                false,
            ),
            Arguments.of(
                7,
                false,
            ),
            Arguments.of(
                8,
                false,
            ),
            Arguments.of(
                10,
                false,
            ),
            Arguments.of(
                -4,
                false,
            ),
            Arguments.of(
                -9,
                false,
            ),
            Arguments.of(
                -16,
                false,
            ),
            Arguments.of(
                -25,
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is num a perfect square test`(num: Number, expected: Boolean) {
        val actual = num.isSquare()
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
