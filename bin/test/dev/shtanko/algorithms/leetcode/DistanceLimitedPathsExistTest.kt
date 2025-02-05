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

abstract class DistanceLimitedPathsExistTest<out T : DistanceLimitedPathsExist>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(0, 1, 2),
                    intArrayOf(1, 2, 4),
                    intArrayOf(2, 0, 8),
                    intArrayOf(1, 0, 160),
                ),
                arrayOf(
                    intArrayOf(0, 1, 2),
                    intArrayOf(0, 2, 5),
                ),
                booleanArrayOf(false, true),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1, 10),
                    intArrayOf(1, 2, 5),
                    intArrayOf(2, 3, 9),
                    intArrayOf(3, 4, 13),
                ),
                arrayOf(
                    intArrayOf(0, 4, 14),
                    intArrayOf(1, 4, 13),
                ),
                booleanArrayOf(true, false),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1, 10),
                    intArrayOf(1, 2, 1),
                    intArrayOf(2, 3, 2),
                    intArrayOf(3, 4, 1),
                ),
                arrayOf(
                    intArrayOf(0, 4, 14),
                    intArrayOf(1, 4, 13),
                ),
                booleanArrayOf(true, true),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `distance limited paths exist test`(
        num: Int,
        edgeList: Array<IntArray>,
        queries: Array<IntArray>,
        expected: BooleanArray,
    ) {
        val actual = strategy.invoke(num, edgeList, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

class DisjointSetUnionTest : DistanceLimitedPathsExistTest<DistanceLimitedPathsExist>(DisjointSetUnion())
