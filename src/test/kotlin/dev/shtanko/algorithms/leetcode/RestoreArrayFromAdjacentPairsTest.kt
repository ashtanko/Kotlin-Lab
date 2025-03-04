/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.leetcode.RestoreArrayFromAdjacentPairsStrategy.DFS
import dev.shtanko.algorithms.leetcode.RestoreArrayFromAdjacentPairsStrategy.Iterative
import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class RestoreArrayFromAdjacentPairsTest<out T : RestoreArrayFromAdjacentPairs>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(intArrayOf(2, 1), intArrayOf(3, 4), intArrayOf(3, 2)),
                intArrayOf(1, 2, 3, 4),
            ),
            Arguments.of(
                arrayOf(intArrayOf(4, -2), intArrayOf(1, 4), intArrayOf(-3, 1)),
                intArrayOf(-2, 4, 1, -3),
            ),
            Arguments.of(
                arrayOf(intArrayOf(100000, -100000)),
                intArrayOf(100000, -100000),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `restore array test`(adjacentPairs: Array<IntArray>, expected: IntArray) {
        val actual = strategy(adjacentPairs)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class DFSTest : RestoreArrayFromAdjacentPairsTest<RestoreArrayFromAdjacentPairs>(DFS)
class IterativeTest : RestoreArrayFromAdjacentPairsTest<RestoreArrayFromAdjacentPairs>(Iterative)
