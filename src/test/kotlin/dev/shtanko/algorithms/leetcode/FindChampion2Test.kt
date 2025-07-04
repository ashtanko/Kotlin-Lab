/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class FindChampion2Test<out T : FindChampion2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            // Test Case 1: 3 nodes and edges [[0, 1], [1, 2]]
            // Visualization:
            // 0 -- 1 -- 2
            Arguments.of(
                3,
                arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)),
                0,
            ),
            // Test Case 2: 4 nodes and edges [[0, 2], [1, 3], [1, 2]]
            // Visualization:
            // 0     1
            //  |  / |
            //  2    3
            Arguments.of(
                4,
                arrayOf(intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(1, 2)),
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun findChampionTest(n: Int, edges: Array<IntArray>, expected: Int) {
        val actual = strategy(n, edges)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindChampion2InDegreeCountTest : FindChampion2Test<FindChampion2>(FindChampion2InDegreeCount())
