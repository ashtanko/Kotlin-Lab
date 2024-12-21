/*
 * Copyright 2020 Oleksii Shtanko
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class DestinationCityTest<out T : DestinationCityStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                emptyList<List<String>>(),
                "",
            ),
            Arguments.of(
                listOf(
                    listOf("London", "New York"),
                    listOf("New York", "Lima"),
                    listOf("Lima", "Sao Paulo"),
                ),
                "Sao Paulo",
            ),
            Arguments.of(
                listOf(
                    listOf("B", "C"),
                    listOf("D", "B"),
                    listOf("C", "A"),
                ),
                "A",
            ),
            Arguments.of(
                listOf(
                    listOf("A", "Z"),
                ),
                "Z",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `destination city test`(paths: List<List<String>>, expected: String) {
        val actual = strategy.invoke(paths)
        assertEquals(expected, actual)
    }
}

class DestinationCitySetTest : DestinationCityTest<DestinationCityStrategy>(DestinationCitySet())
class DestinationCityHashMapTest : DestinationCityTest<DestinationCityStrategy>(DestinationCityHashMap())
class DestinationCityStrategyBFTest : DestinationCityTest<DestinationCityStrategy>(DestinationCityStrategyBF())
