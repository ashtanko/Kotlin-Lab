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

abstract class MinOperationsTest<out T : MinOperations>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 1, 4, 2, 3),
                5,
                2,
            ),
            Arguments.of(
                intArrayOf(5, 6, 7, 8, 9),
                4,
                -1,
            ),
            Arguments.of(
                intArrayOf(3, 2, 20, 1, 1, 3),
                10,
                5,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min operations test`(nums: IntArray, x: Int, expected: Int) {
        val actual = strategy(nums, x)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MinOperationsHashMapTest : MinOperationsTest<MinOperations>(MinOperationsHashMap())
class MinOperationsTwoSumTest : MinOperationsTest<MinOperations>(MinOperationsTwoSum())
