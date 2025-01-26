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

abstract class GroupSum6Test<out T : GroupSum6>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                intArrayOf(5, 6, 2),
                8,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(5, 6, 2),
                9,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(5, 6, 2),
                7,
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
                0,
                intArrayOf(),
                0,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(3, 2, 4, 6),
                8,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(6, 2, 4, 3),
                8,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(5, 2, 4, 6),
                9,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(6, 2, 4, 5),
                9,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(3, 2, 4, 6),
                3,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(1, 6, 2, 6, 4),
                12,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(1, 6, 2, 6, 4),
                13,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(1, 6, 2, 6, 4),
                4,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(1, 6, 2, 6, 4),
                9,
                false,
            ),
            Arguments.of(
                0,
                intArrayOf(1, 6, 2, 6, 5),
                14,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(1, 6, 2, 6, 5),
                15,
                true,
            ),
            Arguments.of(
                0,
                intArrayOf(1, 6, 2, 6, 5),
                16,
                false,
            ),
            Arguments.of(
                1,
                intArrayOf(5, 8, 2),
                10,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `group sum 6 test`(start: Int, nums: IntArray, target: Int, expected: Boolean) {
        val actual = strategy(start, nums, target)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class GroupSum6IterativeTest : GroupSum6Test<GroupSum6>(GroupSum6Iterative())
class GroupSum6RecursiveTest : GroupSum6Test<GroupSum6>(GroupSum6Recursive())
