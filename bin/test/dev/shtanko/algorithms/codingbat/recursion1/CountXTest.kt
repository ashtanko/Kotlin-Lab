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

abstract class CountXTest<out T : CountX>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "xxhixx",
                4,
            ),
            Arguments.of(
                "xhixhix",
                3,
            ),
            Arguments.of(
                "hi",
                0,
            ),
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "x",
                1,
            ),
            Arguments.of(
                "xx",
                2,
            ),
            Arguments.of(
                "hiAAhi12hi",
                0,
            ),
            Arguments.of(
                "xxjhsdgfjshdgfhjxhjcgxzjhcgjzxgczxcyuyxzgctyxrzycfgxzyucgfxztycfgzhxjgcgzxucjhzxgfchxzfgcjhxzfc",
                15,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count x test`(str: String, expected: Int) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class CountXIterativeTest : CountXTest<CountX>(CountXIterative())
class CountXRecursiveTest : CountXTest<CountX>(CountXRecursive())
class CountXRecursiveSimplifiedTest : CountXTest<CountX>(CountXRecursiveSimplified())
class CountXMemoTest : CountXTest<CountX>(CountXMemo())
