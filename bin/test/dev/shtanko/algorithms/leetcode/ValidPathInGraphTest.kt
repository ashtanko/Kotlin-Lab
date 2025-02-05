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

abstract class ValidPathInGraphTest<out T : ValidPathInGraph>(private val strategy: T) {
    private class InoutArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 0),
                ),
                0,
                2,
                true,
            ),
            Arguments.of(
                6,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(3, 5),
                    intArrayOf(5, 4),
                    intArrayOf(4, 3),
                ),
                0,
                2,
                true,
            ),
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 0),
                ),
                0,
                1,
                true,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(3, 4),
                    intArrayOf(2, 4),
                ),
                0,
                3,
                true,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(3, 4),
                    intArrayOf(2, 4),
                ),
                0,
                4,
                true,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(3, 4),
                    intArrayOf(2, 4),
                ),
                0,
                0,
                true,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(3, 4),
                    intArrayOf(2, 4),
                ),
                0,
                1,
                true,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(3, 4),
                    intArrayOf(2, 4),
                ),
                0,
                2,
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InoutArgumentsProvider::class)
    fun `valid path test`(num: Int, edges: Array<IntArray>, source: Int, destination: Int, expected: Boolean) {
        val actual = strategy.invoke(num, edges, source, destination)
        assertThat(actual).isEqualTo(expected)
    }
}

class ValidPathUnionFindTest : ValidPathInGraphTest<ValidPathInGraph>(ValidPathUnionFind())
class ValidPathUnionByRankTest : ValidPathInGraphTest<ValidPathInGraph>(ValidPathUnionByRank())
class ValidPathDFSTest : ValidPathInGraphTest<ValidPathInGraph>(ValidPathDFS())
class ValidPathBFSFindTest : ValidPathInGraphTest<ValidPathInGraph>(ValidPathBFS())
