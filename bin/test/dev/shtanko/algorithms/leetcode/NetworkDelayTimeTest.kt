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

abstract class NetworkDelayTimeTest<out T : NetworkDelayTime>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 1, 1),
                    intArrayOf(2, 3, 1),
                    intArrayOf(3, 4, 1),
                ),
                4,
                2,
                2,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                ),
                2,
                1,
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                ),
                2,
                2,
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `network delay time test`(times: Array<IntArray>, num: Int, k: Int, expected: Int) {
        val actual = strategy.invoke(times, num, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class NetworkDelayTimeDFSTest : NetworkDelayTimeTest<NetworkDelayTime>(NetworkDelayTimeDFS())
class NetworkDelayTimeBFSTest : NetworkDelayTimeTest<NetworkDelayTime>(NetworkDelayTimeBFS())
class NetworkDelayTimeDijkstraTest : NetworkDelayTimeTest<NetworkDelayTime>(NetworkDelayTimeDijkstra())
