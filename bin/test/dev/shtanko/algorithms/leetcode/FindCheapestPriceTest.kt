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

abstract class FindCheapestPriceTest<out T : FindCheapestPrice>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(0, 1, 100),
                    intArrayOf(1, 2, 100),
                    intArrayOf(2, 0, 100),
                    intArrayOf(1, 3, 600),
                    intArrayOf(2, 3, 200),
                ),
                0,
                3,
                1,
                700,
            ),
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(0, 1, 100),
                    intArrayOf(1, 2, 100),
                    intArrayOf(0, 2, 500),
                ),
                0,
                2,
                1,
                200,
            ),
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(0, 1, 100),
                    intArrayOf(1, 2, 100),
                    intArrayOf(0, 2, 500),
                ),
                0,
                2,
                0,
                500,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1, 100),
                    intArrayOf(1, 2, 100),
                    intArrayOf(0, 2, 500),
                    intArrayOf(2, 3, 100),
                    intArrayOf(3, 4, 100),
                    intArrayOf(4, 2, 100),
                ),
                0,
                4,
                1,
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find cheapest price test`(num: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int, expected: Int) {
        val actual = strategy.invoke(num, flights, src, dst, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindCheapestPriceBFSTest : FindCheapestPriceTest<FindCheapestPrice>(FindCheapestPriceBFS())
class FindCheapestPriceBellmanFordTest : FindCheapestPriceTest<FindCheapestPrice>(FindCheapestPriceBellmanFord())
class FindCheapestPriceDijkstraTest : FindCheapestPriceTest<FindCheapestPrice>(FindCheapestPriceDijkstra())
