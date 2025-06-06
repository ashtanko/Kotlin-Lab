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

package dev.shtanko.algorithms.codingbat.recursion2

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class GroupSumTest<out T : GroupSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                intArrayOf(2, 4, 8),
                10,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(2, 4, 8),
                14,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(2, 4, 8),
                9,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(2, 4, 8),
                8,
                true,
            ),
            Arguments.of(
                1,
                intArrayOf(2, 4, 8),
                8,
                true,
            ),
            Arguments.of(
                2,
                intArrayOf(2, 4, 8),
                9,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(1),
                1,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(9),
                1,
                false,
            ),
            Arguments.of(
                1,
                intArrayOf(9),
                0,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(),
                0,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(10, 2, 2, 5),
                17,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(10, 2, 2, 5),
                15,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(10, 2, 2, 5),
                9,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `group sum test`(start: Int, nums: IntArray, target: Int, expected: Boolean) {
        val actual = strategy(start, nums, target)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class GroupSumIterableTest : GroupSumTest<GroupSum>(GroupSumIterable())
class GroupSumBacktrackingTest : GroupSumTest<GroupSum>(GroupSumBacktracking())
