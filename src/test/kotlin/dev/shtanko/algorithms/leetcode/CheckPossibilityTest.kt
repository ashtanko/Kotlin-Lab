/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class CheckPossibilityTest<out T : CheckPossibility>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 2, 3),
                true,
            ),
            Arguments.of(
                intArrayOf(4, 2, 1),
                false,
            ),
            Arguments.of(
                intArrayOf(),
                true,
            ),
            Arguments.of(
                intArrayOf(1),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                true,
            ),
            Arguments.of(
                intArrayOf(2, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(3, 4, 2, 3),
                false,
            ),
            Arguments.of(
                intArrayOf(5, 7, 1, 8),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0),
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `check possibility test`(nums: IntArray, expected: Boolean) {
        val actual = strategy.invoke(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

class CheckPossibilityGreedyTest : CheckPossibilityTest<CheckPossibilityGreedy>(CheckPossibilityGreedy())
