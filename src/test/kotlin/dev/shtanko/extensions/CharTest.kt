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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class CharTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of('a'..'z', 0, 0),
            Arguments.of('a'..'z', 6, 6),
            Arguments.of('A'..'Z', 6, 6),
            Arguments.of('A'..'Z', 600, 600),
        )
    }

    internal class InputVowelArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of('a', true),
            Arguments.of('e', true),
            Arguments.of('i', true),
            Arguments.of('o', true),
            Arguments.of('u', true),
            Arguments.of('r', false),
            Arguments.of('q', false),
            Arguments.of('z', false),
            Arguments.of('x', false),
            Arguments.of('A', true),
            Arguments.of('E', true),
            Arguments.of('I', true),
            Arguments.of('O', true),
            Arguments.of('U', true),
            Arguments.of('R', false),
            Arguments.of('Q', false),
            Arguments.of('Z', false),
            Arguments.of('X', false),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `random string test`(range: CharRange, len: Int, expected: Int) {
        val randomString = range.randomString(len)
        val actual = randomString.length
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputVowelArgumentsProvider::class)
    internal fun `is vowel test`(c: Char, expected: Boolean) {
        val actual = c.isVowel()
        assertThat(actual, equalTo(expected))
    }
}
