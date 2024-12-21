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

abstract class CollectApplesTest<out T : CollectApples>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(2, 6),
                ),
                listOf(false, false, true, false, true, true, false),
                8,
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(2, 6),
                ),
                listOf(false, false, true, false, false, true, false),
                6,
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(2, 6),
                ),
                listOf(false, false, false, false, false, false, false),
                0,
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(2, 6),
                ),
                listOf(true, true, true, true, true, true, true),
                12,
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(2, 6),
                ),
                listOf(true, true, true, true, true, true, false),
                10,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min time test`(num: Int, edges: Array<IntArray>, hasApple: List<Boolean>, expected: Int) {
        assertThat(strategy.minTime(num, edges, hasApple)).isEqualTo(expected)
    }
}

class CollectApplesDFSTest : CollectApplesTest<CollectApples>(CollectApplesDFS())
