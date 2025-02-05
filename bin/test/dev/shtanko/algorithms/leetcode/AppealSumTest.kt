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

abstract class AppealSumTest<out T : AppealSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "abbca",
                28L,
            ),
            Arguments.of(
                "code",
                20L,
            ),
            Arguments.of(
                "",
                0L,
            ),
            Arguments.of(
                "a",
                1L,
            ),
            Arguments.of(
                "ab",
                4L,
            ),
            Arguments.of(
                "abc",
                10L,
            ),
            Arguments.of(
                "abcd",
                20L,
            ),
            Arguments.of(
                "abcde",
                35L,
            ),
            Arguments.of(
                "abcdef",
                56L,
            ),
            Arguments.of(
                "abcdefg",
                84L,
            ),
            Arguments.of(
                "abcdefgh",
                120L,
            ),
            Arguments.of(
                "abcdefghi",
                165L,
            ),
            Arguments.of(
                "abcdefghij",
                220L,
            ),
            Arguments.of(
                "abcdefghijk",
                286L,
            ),
            Arguments.of(
                "abcdefghijkl",
                364L,
            ),
            Arguments.of(
                "abcdefghijklm",
                455L,
            ),
            Arguments.of(
                "abcdefghijklmn",
                560L,
            ),
            Arguments.of(
                "abcdefghijklmno",
                680L,
            ),
            Arguments.of(
                "abcdefghijklmnop",
                816L,
            ),
            Arguments.of(
                "abcdefghijklmnopq",
                969L,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `appeal sum test`(str: String, expected: Long) {
        val actual = strategy.invoke(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class AppealSumDPTest : AppealSumTest<AppealSum>(AppealSumDP())
class AppealSumDPKtTest : AppealSumTest<AppealSum>(AppealSumDPKt())
