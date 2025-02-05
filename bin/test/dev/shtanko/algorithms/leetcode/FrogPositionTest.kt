/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class FrogPositionTest<out T : FrogPosition>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 7),
                    intArrayOf(2, 4),
                    intArrayOf(2, 6),
                    intArrayOf(3, 5),
                ),
                2,
                4,
                0.16666666666666666,
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 7),
                    intArrayOf(2, 4),
                    intArrayOf(2, 6),
                    intArrayOf(3, 5),
                ),
                1,
                7,
                0.3333333333333333,
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 7),
                    intArrayOf(2, 4),
                    intArrayOf(2, 6),
                    intArrayOf(3, 5),
                ),
                20,
                6,
                0.16666666666666666,
            ),
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(2, 1),
                    intArrayOf(3, 2),
                ),
                1,
                2,
                1.0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `frog position test`(num: Int, edges: Array<IntArray>, t: Int, target: Int, expected: Double) {
        val actual = strategy.invoke(num, edges, t, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class FrogPositionBFSTest : FrogPositionTest<FrogPosition>(FrogPositionBFS())
