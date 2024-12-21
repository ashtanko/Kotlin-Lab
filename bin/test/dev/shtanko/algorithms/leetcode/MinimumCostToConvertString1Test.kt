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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MinimumCostToConvertString1Test<out T : MinimumCostToConvertString1>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "abcd",
                "acbe",
                charArrayOf('a', 'b', 'c', 'c', 'e', 'd'),
                charArrayOf('b', 'c', 'b', 'e', 'b', 'e'),
                intArrayOf(2, 5, 5, 1, 2, 20),
                28L,
            ),
            Arguments.of(
                "aaaa",
                "bbbb",
                charArrayOf('a', 'c'),
                charArrayOf('c', 'b'),
                intArrayOf(1, 2),
                12L,
            ),
            Arguments.of(
                "abcd",
                "abce",
                charArrayOf('a'),
                charArrayOf('e'),
                intArrayOf(10000),
                -1L,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun minimumCostTest(
        source: String,
        target: String,
        original: CharArray,
        changed: CharArray,
        cost: IntArray,
        expected: Long,
    ) {
        val actual = strategy(source, target, original, changed, cost)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinimumCostDijkstraTest : MinimumCostToConvertString1Test<MinimumCostToConvertString1>(MinimumCostDijkstra())
class MinimumCostFloydWarshallTest :
    MinimumCostToConvertString1Test<MinimumCostToConvertString1>(MinimumCostFloydWarshall())
