/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

class BestSightseeingPairTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(intArrayOf(), 0),
            Arguments.of(intArrayOf(1), 1),
            Arguments.of(intArrayOf(1, 2), 2),
            Arguments.of(intArrayOf(1, 2, 3), 4),
            Arguments.of(intArrayOf(-1), 0),
            Arguments.of(intArrayOf(8, 1, 5, 2, 6), 11),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 10),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7), 12),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 14),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 16),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 18),
            Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 20),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `best sightseeing pair test`(arr: IntArray, expected: Int) {
        val actual = maxScoreSightseeingPair(arr)
        assertEquals(expected, actual)
    }
}
