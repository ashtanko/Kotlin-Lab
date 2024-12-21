/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.math

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class BinomialTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 1, 0),
            Arguments.of(1, 1, 1),
            Arguments.of(2, 1, 2),
            Arguments.of(3, 1, 3),
            Arguments.of(3, 2, 3),
            Arguments.of(4, 1, 4),
            Arguments.of(5, 0, 1),
            Arguments.of(5, 1, 5),
            Arguments.of(5, 2, 10),
            Arguments.of(5, 3, 10),
            Arguments.of(5, 4, 5),
            Arguments.of(5, 5, 1),
            Arguments.of(6, 0, 1),
            Arguments.of(6, 1, 6),
            Arguments.of(6, 2, 15),
            Arguments.of(6, 3, 20),
            Arguments.of(6, 4, 15),
            Arguments.of(6, 5, 6),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `binomial test`(num: Int, k: Int, expected: Long) {
        val actual = binomial(num, k)
        assertThat(actual, equalTo(expected))
    }
}
