/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class CriticalConnectionsTest<out T : CriticalConnections>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                listOf(
                    listOf(0, 1),
                    listOf(1, 2),
                    listOf(2, 0),
                    listOf(1, 3),
                ),
                listOf(
                    listOf(1, 3),
                ),
            ),
            Arguments.of(
                6,
                listOf(
                    listOf(0, 1),
                    listOf(1, 2),
                    listOf(2, 0),
                    listOf(1, 3),
                    listOf(3, 4),
                    listOf(4, 5),
                    listOf(5, 3),
                ),
                listOf(
                    listOf(1, 3),
                ),
            ),
            Arguments.of(
                6,
                listOf(
                    listOf(0, 1),
                    listOf(1, 2),
                    listOf(2, 0),
                    listOf(1, 3),
                    listOf(3, 4),
                    listOf(4, 5),
                ),
                listOf(
                    listOf(4, 5),
                    listOf(3, 4),
                    listOf(1, 3),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `critical connections test`(num: Int, connections: List<List<Int>>, expected: List<List<Int>>) {
        val actual = strategy.invoke(num, connections)
        assertThat(actual).containsExactlyInAnyOrder(*expected.toTypedArray())
    }
}

class CycleDetectionTest : CriticalConnectionsTest<CycleDetection>(CycleDetection())
class CriticalConnectionsGraphTest :
    CriticalConnectionsTest<CriticalConnectionsGraph>(CriticalConnectionsGraph())
