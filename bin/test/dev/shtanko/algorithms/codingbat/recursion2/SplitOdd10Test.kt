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

abstract class SplitOdd10Test<out T : SplitOdd10>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(5, 5, 5),
                true,
            ),
            Arguments.of(
                intArrayOf(5, 5, 6),
                false,
            ),
            Arguments.of(
                intArrayOf(5, 5, 6, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(6, 5, 5, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(6, 5, 5, 1, 10),
                true,
            ),
            Arguments.of(
                intArrayOf(6, 5, 5, 5, 1),
                false,
            ),
            Arguments.of(
                intArrayOf(1),
                true,
            ),
            Arguments.of(
                intArrayOf(),
                false,
            ),
            Arguments.of(
                intArrayOf(10, 7, 5, 5),
                true,
            ),
            Arguments.of(
                intArrayOf(10, 0, 5, 5),
                false,
            ),
            Arguments.of(
                intArrayOf(10, 7, 5, 5, 2),
                true,
            ),
            Arguments.of(
                intArrayOf(10, 7, 5, 5, 1),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `split odd 10 test`(nums: IntArray, expected: Boolean) {
        val actual = strategy(nums)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class SplitOdd10RecursiveTest : SplitOdd10Test<SplitOdd10>(SplitOdd10Recursive())
