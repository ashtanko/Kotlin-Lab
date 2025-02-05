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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ReconstructItineraryTest<out T : ReconstructItinerary>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    listOf("MUC", "LHR"),
                    listOf("JFK", "MUC"),
                    listOf("SFO", "SJC"),
                    listOf("LHR", "SFO"),
                ),
                listOf("JFK", "MUC", "LHR", "SFO", "SJC"),
            ),
            Arguments.of(
                listOf(
                    listOf("JFK", "SFO"),
                    listOf("JFK", "ATL"),
                    listOf("SFO", "ATL"),
                    listOf("ATL", "JFK"),
                    listOf("ATL", "SFO"),
                ),
                listOf("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find itinerary test`(tickets: List<List<String>>, expected: List<String>) {
        val actual = strategy(tickets)
        Assertions.assertThat(actual).containsExactlyInAnyOrder(*expected.toTypedArray())
    }
}

class ReconstructItineraryRecursiveTest : ReconstructItineraryTest<ReconstructItinerary>(
    ReconstructItineraryRecursive(),
)

class ReconstructItineraryIterativeTest : ReconstructItineraryTest<ReconstructItinerary>(
    ReconstructItineraryIterative(),
)
