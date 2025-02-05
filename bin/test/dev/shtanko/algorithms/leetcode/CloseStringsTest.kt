/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class CloseStringsTest<out T : CloseStrings>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "abc",
                "bca",
                true,
            ),
            Arguments.of(
                "",
                "",
                true,
            ),
            Arguments.of(
                "a",
                "aa",
                false,
            ),
            Arguments.of(
                "cabbba",
                "abbccc",
                true,
            ),
            Arguments.of(
                "cabbba",
                "aabbss",
                false,
            ),
            Arguments.of(
                "cabbba",
                "aabbcc",
                false,
            ),
            Arguments.of(
                "cabbba",
                "aabbccc",
                false,
            ),
            Arguments.of(
                "aaabbbbccddeeeeefffff",
                "aaaaabbcccdddeeeeffff",
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `close strings test`(word1: String, word2: String, expected: Boolean) {
        val actual = strategy.invoke(word1, word2)
        assertThat(actual).isEqualTo(expected)
    }
}

class CloseStringsMapTest : CloseStringsTest<CloseStrings>(CloseStringsMap())
class CloseStringsBitwiseTest : CloseStringsTest<CloseStrings>(CloseStringsBitwise())
class CloseStringsSortTest : CloseStringsTest<CloseStrings>(CloseStringsSort())
