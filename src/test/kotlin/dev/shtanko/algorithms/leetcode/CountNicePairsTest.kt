/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

abstract class CountNicePairsTest<out T : CountNicePairs>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(42, 11, 1, 97),
                2,
            ),
            Arguments.of(
                intArrayOf(13, 10, 35, 24, 76),
                4,
            ),
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 1),
                1,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                6,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1, 1),
                10,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1, 1, 1),
                15,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count nice pairs test`(nums: IntArray, expected: Int) {
        val actual = strategy.invoke(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountNicePairsSFTest : CountNicePairsTest<CountNicePairs>(CountNicePairsSF())
class CountNicePairsTwoSumTest : CountNicePairsTest<CountNicePairs>(CountNicePairsTwoSum())
