/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

internal abstract class LongestIncreasingPathTest<out T : LongestIncreasingPath>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(9, 9, 4),
                    intArrayOf(6, 6, 8),
                    intArrayOf(2, 1, 1),
                ),
                4,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 4, 5),
                    intArrayOf(3, 2, 6),
                    intArrayOf(2, 2, 1),
                ),
                4,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1),
                ),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `longest increasing path test`(matrix: Array<IntArray>, expected: Int) {
        val actual = strategy.perform(matrix)
        assertThat(actual).isEqualTo(expected)
    }
}

internal class LongestIncreasingPathDFSTest :
    LongestIncreasingPathTest<LongestIncreasingPathDFS>(LongestIncreasingPathDFS())

internal class LongestIncreasingPathPeelingOnionTest :
    LongestIncreasingPathTest<LongestIncreasingPathPeelingOnion>(LongestIncreasingPathPeelingOnion())
