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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class Array6Test<out T : Array6>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 6, 4),
                0,
                true,
            ),
            Arguments.of(
                intArrayOf(1, 4),
                0,
                false,
            ),
            Arguments.of(
                intArrayOf(6),
                0,
                true,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                5,
                true,
            ),
            Arguments.of(
                intArrayOf(6, 5, 4),
                1,
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `array 6 test`(arr: IntArray, index: Int, expected: Boolean) {
        val actual = strategy(arr, index)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class Array6IterativeTest : Array6Test<Array6>(Array6Iterative())

class Array6RecursiveTest : Array6Test<Array6>(Array6Recursive())
