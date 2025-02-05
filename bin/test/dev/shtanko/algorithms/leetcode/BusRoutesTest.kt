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
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class BusRoutesTest<out T : BusRoutes>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf<IntArray>(),
                0,
                0,
                0,
            ),
            Arguments.of(
                arrayOf(intArrayOf(1)),
                1,
                1,
                0,
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 2)),
                1,
                1,
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 7),
                    intArrayOf(3, 6, 7),
                ),
                1,
                6,
                2,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(7, 12),
                    intArrayOf(4, 5, 15),
                    intArrayOf(6),
                    intArrayOf(15, 19),
                    intArrayOf(9, 12, 13),
                ),
                15,
                12,
                -1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 7),
                    intArrayOf(3, 6, 7),
                ),
                1,
                6,
                2,
            ),
        )
    }

    @DisplayName("num buses to destination test")
    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun numBusesToDestinationTest(routes: Array<IntArray>, source: Int, target: Int, expected: Int) {
        val actual = strategy.invoke(routes, source, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class BusRoutesBFSTest : BusRoutesTest<BusRoutes>(BusRoutesBFS())
class BusRoutesBFS2Test : BusRoutesTest<BusRoutes>(BusRoutesBFS2())
