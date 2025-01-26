/*
 * Copyright 2020 Oleksii Shtanko
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

class ClosestDivisorsTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                -2,
                intArrayOf(),
            ),
            Arguments.of(
                -1,
                intArrayOf(1, 0),
            ),
            Arguments.of(
                0,
                intArrayOf(1, 1),
            ),
            Arguments.of(
                8,
                intArrayOf(3, 3),
            ),
            Arguments.of(
                123,
                intArrayOf(5, 25),
            ),
            Arguments.of(
                999,
                intArrayOf(25, 40),
            ),
            Arguments.of(
                1000000000,
                intArrayOf(23658, 42269),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `closest divisor test`(num: Int, expected: IntArray) {
        val actual = closestDivisors(num)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}
