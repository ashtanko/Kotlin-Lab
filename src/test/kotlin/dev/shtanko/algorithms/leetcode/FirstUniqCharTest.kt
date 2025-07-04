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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class FirstUniqCharTest<out T : FirstUniqChar>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "leetcode",
                0,
            ),
            Arguments.of(
                "loveleetcode",
                2,
            ),
            Arguments.of(
                "aabb",
                -1,
            ),
            Arguments.of(
                "z",
                0,
            ),
            Arguments.of(
                "aadadaad",
                -1,
            ),
            Arguments.of(
                "dddccdbba",
                8,
            ),
            Arguments.of(
                "dddccdbbaa",
                -1,
            ),
            Arguments.of(
                "",
                -1,
            ),
            Arguments.of(
                "a",
                0,
            ),
            Arguments.of(
                "aa",
                -1,
            ),
            Arguments.of(
                "ab",
                0,
            ),
            Arguments.of(
                "abc",
                0,
            ),
            Arguments.of(
                "abcabc",
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `firstUniqChar test`(str: String, expected: Int) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class FirstUniqCharArrayTest : FirstUniqCharTest<FirstUniqChar>(FirstUniqCharArray())
