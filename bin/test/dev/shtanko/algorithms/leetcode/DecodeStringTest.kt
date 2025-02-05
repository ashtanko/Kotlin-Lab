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

abstract class DecodeStringTest<out T : DecodeString>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "3[a]2[bc]",
                "aaabcbc",
            ),
            Arguments.of(
                "3[a2[c]]",
                "accaccacc",
            ),
            Arguments.of(
                "2[abc]3[cd]ef",
                "abcabccdcdcdef",
            ),
            Arguments.of(
                "abc3[cd]xyz",
                "abccdcdcdxyz",
            ),
            Arguments.of(
                "100[leetcode]",
                "leetcode".repeat(100),
            ),
            Arguments.of(
                "3[z]2[2[y]pq4[2[jk]e1[f]]]ef",
                "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `decode string test`(str: String, expected: String) {
        val actual = strategy.invoke(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class DecodeStringStackTest : DecodeStringTest<DecodeString>(DecodeStringStack())
class DecodeStringRecursiveTest : DecodeStringTest<DecodeString>(DecodeStringRecursive())
