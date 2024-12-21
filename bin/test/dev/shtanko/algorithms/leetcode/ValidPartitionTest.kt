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

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ValidPartitionTest<out T : ValidPartition>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                true,
            ),
            Arguments.of(
                intArrayOf(1),
                false,
            ),
            Arguments.of(
                intArrayOf(1, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(4, 4, 4, 5, 6),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 2),
                false,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                false,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `valid partition test`(nums: IntArray, expected: Boolean) {
        val actual = strategy(nums)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ValidPartitionTopDownTest : ValidPartitionTest<ValidPartition>(ValidPartitionTopDown())
class ValidPartitionBottomUpTest : ValidPartitionTest<ValidPartition>(ValidPartitionBottomUp())
class ValidPartitionBottomUpSpaceOptTest : ValidPartitionTest<ValidPartition>(ValidPartitionBottomUpSpaceOpt())
