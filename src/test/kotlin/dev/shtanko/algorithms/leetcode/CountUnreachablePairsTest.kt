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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class CountUnreachablePairsTest<out T : CountUnreachablePairs>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 2),
                ),
                0,
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 2),
                    intArrayOf(0, 5),
                    intArrayOf(2, 4),
                    intArrayOf(1, 6),
                    intArrayOf(5, 4),
                ),
                14,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count pairs test`(num: Int, edges: Array<IntArray>, expected: Long) {
        val actual = strategy(num, edges)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountUnreachablePairsDFSTest : CountUnreachablePairsTest<CountUnreachablePairs>(CountUnreachablePairsDFS())
