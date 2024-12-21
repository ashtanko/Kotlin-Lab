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

abstract class GraphConnectivityWithThresholdTest<out T : GraphConnectivityWithThreshold>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                6,
                2,
                arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(2, 5),
                    intArrayOf(3, 6),
                ),
                listOf(false, false, true),
            ),
            Arguments.of(
                6,
                0,
                arrayOf(
                    intArrayOf(4, 5),
                    intArrayOf(3, 4),
                    intArrayOf(3, 2),
                    intArrayOf(2, 6),
                    intArrayOf(1, 3),
                ),
                listOf(true, true, true, true, true),
            ),
            Arguments.of(
                5,
                1,
                arrayOf(
                    intArrayOf(4, 5),
                    intArrayOf(4, 5),
                    intArrayOf(3, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                ),
                listOf(false, false, false, false, false),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `are connected test`(num: Int, threshold: Int, queries: Array<IntArray>, expected: List<Boolean>) {
        val actual = strategy.areConnected(num, threshold, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

class GraphConnectivityWithThresholdUnionTest : GraphConnectivityWithThresholdTest<GraphConnectivityWithThreshold>(
    GraphConnectivityWithThresholdUnion(),
)
