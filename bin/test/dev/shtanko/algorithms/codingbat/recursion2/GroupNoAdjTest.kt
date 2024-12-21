/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.codingbat.recursion2

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class GroupNoAdjTest<out T : GroupNoAdj>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                intArrayOf(2, 5, 10, 4),
                12,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(2, 5, 10, 4),
                14,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(2, 5, 10, 4),
                7,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(2, 5, 10, 4, 2),
                7,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(2, 5, 10, 4),
                9,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(10, 2, 2, 3, 3),
                15,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(10, 2, 2, 3, 3),
                7,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(),
                0,
                true,
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
                0,
                intArrayOf(9),
                0,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(5, 10, 4, 1),
                11,
                true,
            ),
            Arguments.of(
                1,
                intArrayOf(5, 10, 4, 1),
                11,
                true,
            ),
            Arguments.of(
                1,
                intArrayOf(1, 1, 3, 5),
                6,
                true,
            ),
            Arguments.of(
                3,
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                3,
                true,
            ),
            Arguments.of(
                5,
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                1,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `group no adj test`(start: Int, nums: IntArray, target: Int, expected: Boolean) {
        val actual = strategy(start, nums, target)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class GroupNoAdjIterativeTest : GroupNoAdjTest<GroupNoAdj>(GroupNoAdjIterative())
class GroupNoAdjRecursiveTest : GroupNoAdjTest<GroupNoAdj>(GroupNoAdjRecursive())
