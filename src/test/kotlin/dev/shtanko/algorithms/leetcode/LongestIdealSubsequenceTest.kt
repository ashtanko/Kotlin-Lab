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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class LongestIdealSubsequenceTest<out T : LongestIdealSubsequence>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "acfgbd",
                2,
                4,
            ),
            Arguments.of(
                "abcd",
                3,
                4,
            ),
            Arguments.of(
                "",
                0,
                0,
            ),
            Arguments.of(
                "a",
                0,
                1,
            ),
            Arguments.of(
                "a",
                1,
                1,
            ),
            Arguments.of(
                "a",
                2,
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun longestIdealStringTest(str: String, k: Int, expected: Int) {
        val actual = strategy(str, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class LongestIdealSubsequenceTopDownDPTest :
    LongestIdealSubsequenceTest<LongestIdealSubsequenceTopDownDP>(LongestIdealSubsequenceTopDownDP())

class LongestIdealSubsequenceBottomUpDPTest :
    LongestIdealSubsequenceTest<LongestIdealSubsequenceBottomUpDP>(LongestIdealSubsequenceBottomUpDP())
