/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

internal class IntExtensionTest {

    internal class InputIsEvenArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(2, true),
            Arguments.of(3, false),
            Arguments.of(4, true),
            Arguments.of(6, true),
            Arguments.of(8, true),
            Arguments.of(9, false),
            Arguments.of(11, false),
            Arguments.of(100, true),
            Arguments.of(Int.MAX_VALUE, false),
        )
    }

    internal class InputPrimeArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                false,
            ),
            Arguments.of(
                2,
                true,
            ),
            Arguments.of(
                3,
                true,
            ),
            Arguments.of(
                4,
                false,
            ),
            Arguments.of(
                5,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputPrimeArgumentsProvider::class)
    internal fun `is prime test`(num: Int, expected: Boolean) {
        val actual = num.isPrime()
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputIsEvenArgumentsProvider::class)
    internal fun `is even test`(num: Int, expected: Boolean) {
        val actual = num.isEven
        assertThat(actual, `is`(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputIsEvenArgumentsProvider::class)
    internal fun `is even fun interface test`(num: Int, expected: Boolean) {
        val actual = isEven.accept(num)
        assertThat(actual, `is`(expected))
    }

    @Test
    internal fun `random array test`() {
        assertEquals(3, 3.generateRandomArray().size)
    }

    @Test
    internal fun `less than zero test`() {
        assertTrue((-1).lessThanZero())
    }

    @Test
    internal fun `more than zero test`() {
        assertFalse(1.lessThanZero())
    }
}
