/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ParenBitTest<out T : ParenBit>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "xyz(abc)123",
                "(abc)",
            ),
            Arguments.of(
                "x(hello)",
                "(hello)",
            ),
            Arguments.of(
                "(xy)1",
                "(xy)",
            ),
            Arguments.of(
                "not really (possible)",
                "(possible)",
            ),
            Arguments.of(
                "(abc)",
                "(abc)",
            ),
            Arguments.of(
                "(abc)xyz",
                "(abc)",
            ),
            Arguments.of(
                "(abc)x",
                "(abc)",
            ),
            Arguments.of(
                "(x)",
                "(x)",
            ),
            Arguments.of(
                "()",
                "()",
            ),
            Arguments.of(
                "res (ipsa) loquitor",
                "(ipsa)",
            ),
            Arguments.of(
                "hello(not really)there",
                "(not really)",
            ),
            Arguments.of(
                "ab(ab)ab",
                "(ab)",
            ),
            Arguments.of(
                "",
                "",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `parenBit test`(str: String, expected: String) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ParenBitIterativeTest : ParenBitTest<ParenBit>(ParenBitIterative())
class ParenBitRecursiveTest : ParenBitTest<ParenBit>(ParenBitRecursive())
