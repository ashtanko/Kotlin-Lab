/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SmallestEquivalentStringTest<out T : SmallestEquivalentString>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "parker",
                "morris",
                "parser",
                "makkek",
            ),
            Arguments.of(
                "hello",
                "world",
                "hold",
                "hdld",
            ),
            Arguments.of(
                "leetcode",
                "programs",
                "sourcecode",
                "aauaaaaada",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `smallest equivalent string test`(s1: String, s2: String, baseStr: String, expected: String) {
        val actual = strategy.invoke(s1, s2, baseStr)
        assertThat(actual).isEqualTo(expected)
    }
}

class SmallestEquivalentStringUnionTest :
    SmallestEquivalentStringTest<SmallestEquivalentString>(SmallestEquivalentStringUnion())
