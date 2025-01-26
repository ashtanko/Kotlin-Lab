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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MaximumXorTest<out T : MaximumXor>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(0, 1, 1, 3),
                2,
                intArrayOf(0, 3, 2, 3),
            ),
            Arguments.of(
                intArrayOf(2, 3, 4, 7),
                3,
                intArrayOf(5, 2, 6, 5),
            ),
            Arguments.of(
                intArrayOf(0, 1, 2, 2, 5, 7),
                3,
                intArrayOf(4, 3, 6, 4, 6, 7),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `get maximum xor test`(nums: IntArray, maximumBit: Int, expected: IntArray) {
        val actual = strategy.invoke(nums, maximumBit)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaximumXorOnePassTest : MaximumXorTest<MaximumXor>(MaximumXorOnePass())
