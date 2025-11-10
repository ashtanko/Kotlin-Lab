/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

internal class LongTest {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4L,
                true,
            ),
            Arguments.of(
                12L,
                false,
            ),
            Arguments.of(
                24L,
                false,
            ),
            Arguments.of(
                484L,
                true,
            ),
            Arguments.of(
                676L,
                false,
            ),
            Arguments.of(
                1156L,
                false,
            ),
            Arguments.of(
                686,
                true,
            ),
        )
    }

    internal class ToReverseInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1L,
                1L,
            ),
            Arguments.of(
                12L,
                21L,
            ),
            Arguments.of(
                121L,
                121L,
            ),
            Arguments.of(
                242L,
                242L,
            ),
            Arguments.of(
                484L,
                484L,
            ),
        )
    }

    internal class InputPalindromeArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0L,
                true,
            ),
            Arguments.of(
                34L,
                false,
            ),
            Arguments.of(
                11L,
                true,
            ),
            Arguments.of(
                879L,
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(ToReverseInputArgumentsProvider::class)
    internal fun `reverse long test`(num: Long, expected: Long) {
        val actual = num.reverse()
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputPalindromeArgumentsProvider::class)
    internal fun `is palindrome test`(num: Long, expected: Boolean) {
        val actual = num.isSuperPalindrome()
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `is super palindrome test`(num: Long, expected: Boolean) {
        val actual = num.isSuperPalindrome()
        assertThat(actual).isEqualTo(expected)
    }
}
