/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class CombinationSum4Test<out T : CombinationSum4>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3),
                4,
                7,
            ),
            Arguments.of(
                intArrayOf(9),
                3,
                0,
            ),
            Arguments.of(
                intArrayOf(0),
                0,
                1,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                32,
                181997601,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `combination sum 4 test`(nums: IntArray, target: Int, expected: Int) {
        val actual = strategy.invoke(nums, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class CombinationSum4TopDownTest : CombinationSum4Test<CombinationSum4TopDown>(CombinationSum4TopDown())
class CombinationSum4BottomUpTest : CombinationSum4Test<CombinationSum4BottomUp>(CombinationSum4BottomUp())
