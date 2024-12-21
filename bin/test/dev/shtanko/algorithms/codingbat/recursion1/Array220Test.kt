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

abstract class Array220Test<out T : Array220>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 20),
                0,
                true,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                false,
            ),
            Arguments.of(
                intArrayOf(3, 30),
                0,
                true,
            ),
            Arguments.of(
                intArrayOf(3),
                0,
                false,
            ),

            Arguments.of(
                intArrayOf(3, 3, 30, 4),
                0,
                true,
            ),
            Arguments.of(
                intArrayOf(2, 19, 4),
                0,
                false,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 50, 6),
                0,
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 50, 6),
                3,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `array 220 test`(nums: IntArray, index: Int, expected: Boolean) {
        val actual = strategy(nums, index)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class Array220IterativeTest : Array220Test<Array220>(Array220Iterative())
class Array220RecursiveTest : Array220Test<Array220>(Array220Recursive())
class Array220Recursive2Test : Array220Test<Array220>(Array220Recursive2())
