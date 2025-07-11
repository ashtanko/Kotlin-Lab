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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class EndXTest<out T : EndX>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "xxre",
                "rexx",
            ),
            Arguments.of(
                "xxhixx",
                "hixxxx",
            ),
            Arguments.of(
                "xhixhix",
                "hihixxx",
            ),
            Arguments.of(
                "x",
                "x",
            ),
            Arguments.of(
                "xiifgkrxdfpg",
                "iifgkrdfpgxx",
            ),
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "bxx",
                "bxx",
            ),
            Arguments.of(
                "bxax",
                "baxx",
            ),
            Arguments.of(
                "axaxax",
                "aaaxxx",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `end x test`(str: String, expected: String) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class EndXIterativeTest : EndXTest<EndX>(EndXIterative())
class EndXRecursiveTest : EndXTest<EndX>(EndXRecursive())
