/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

class IntExtTest {
    private class UglyNumberInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(1, true),
            Arguments.of(2, true),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(UglyNumberInputArgumentsProvider::class)
    fun `is ugly number test`(num: Int, expected: Boolean) {
        val actual = num.isUgly()
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ArgumentsSource(UglyNumberInputArgumentsProvider::class)
    fun `is ugly2 number test`(num: Int, expected: Boolean) {
        val actual = num.isUgly2()
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
