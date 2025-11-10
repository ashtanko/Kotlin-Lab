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

abstract class ShortestDistanceAfterQueriesTest<out T : ShortestDistanceAfterQueries>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                5,
                arrayOf(intArrayOf(2, 4), intArrayOf(0, 2), intArrayOf(0, 4)),
                intArrayOf(3, 2, 1),
            ),
            Arguments.of(
                4,
                arrayOf(intArrayOf(0, 3), intArrayOf(0, 2)),
                intArrayOf(1, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `shortest distance after queries test`(n: Int, queries: Array<IntArray>, expected: IntArray) {
        val actual = strategy(n, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

class ShortestDistanceAfterQueriesBFSBacktrackingTest :
    ShortestDistanceAfterQueriesTest<ShortestDistanceAfterQueries>(ShortestDistanceAfterQueriesBFS())

class ShortestDistanceAfterQueriesTopDownTest :
    ShortestDistanceAfterQueriesTest<ShortestDistanceAfterQueries>(ShortestDistanceAfterQueriesTopDown())

class ShortestDistanceAfterQueriesBottomUpTest :
    ShortestDistanceAfterQueriesTest<ShortestDistanceAfterQueries>(ShortestDistanceAfterQueriesBottomUp())
