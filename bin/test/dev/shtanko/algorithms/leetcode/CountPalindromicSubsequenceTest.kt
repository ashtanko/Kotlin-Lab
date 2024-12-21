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

import dev.shtanko.algorithms.leetcode.CountPalindromicStrategy.CountLetters
import dev.shtanko.algorithms.leetcode.CountPalindromicStrategy.PreComputeFirstAndLastIndices
import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class CountPalindromicSubsequenceTest<out T : CountPalindromicSubsequence>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "aabca",
                3,
            ),
            Arguments.of(
                "adc",
                0,
            ),
            Arguments.of(
                "bbcbaba",
                4,
            ),
            Arguments.of(
                "",
                0,
            ),
            Arguments.of(
                "a",
                0,
            ),
            Arguments.of(
                "aa",
                0,
            ),
            Arguments.of(
                "aaa",
                1,
            ),
            Arguments.of(
                "aaaa",
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count palindromic subsequence test`(str: String, expected: Int) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class CountLettersTest : CountPalindromicSubsequenceTest<CountPalindromicSubsequence>(CountLetters)
class PreComputeFirstAndLastIndicesTest : CountPalindromicSubsequenceTest<CountPalindromicSubsequence>(
    PreComputeFirstAndLastIndices,
)
