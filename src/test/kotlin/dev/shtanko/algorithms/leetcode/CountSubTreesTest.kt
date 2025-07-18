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

abstract class CountSubTreesTest<out T : CountSubTrees>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(2, 6),
                ),
                "abaedcd",
                intArrayOf(2, 1, 1, 1, 1, 1, 1),
            ),
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(0, 3),
                ),
                "bbbb",
                intArrayOf(4, 2, 1, 1),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 3),
                    intArrayOf(0, 4),
                ),
                "aabab",
                intArrayOf(3, 2, 1, 1, 1),
            ),
            Arguments.of(
                6,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                ),
                "cbabaa",
                intArrayOf(1, 2, 1, 1, 2, 1),
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 6),
                ),
                "aaabaaa",
                intArrayOf(6, 5, 4, 1, 3, 2, 1),
            ),
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                    intArrayOf(1, 2),
                ),
                "aeed",
                intArrayOf(1, 1, 2, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count sub trees test`(num: Int, edges: Array<IntArray>, labels: String, expected: IntArray) {
        val actual = strategy.invoke(num, edges, labels)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountSubTreesDFSTest : CountSubTreesTest<CountSubTrees>(CountSubTreesDFS())
