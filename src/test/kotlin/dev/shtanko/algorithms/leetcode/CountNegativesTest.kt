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

abstract class AbstractCountNegativesTest<out T : AbstractCountNegativesStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(4, 3, 2, -1),
                    intArrayOf(3, 2, 1, -1),
                    intArrayOf(1, 1, -1, -2),
                    intArrayOf(-1, -1, -2, -3),
                ),
                8,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 2),
                    intArrayOf(1, 0),
                ),
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, -1),
                    intArrayOf(-1, -1),
                ),
                3,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(-1),
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1),
                ),
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(),
                ),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count negatives test`(grid: Array<IntArray>, expected: Int) {
        val actual = strategy.invoke(grid)
        assertEquals(expected, actual)
    }
}

class SimpleCountNegativesTest : AbstractCountNegativesTest<SimpleCountNegatives>(SimpleCountNegatives())

class CountNegativesTwoPointersTest :
    AbstractCountNegativesTest<CountNegativesTwoPointers>(CountNegativesTwoPointers())

class CountNegativesBinaryTest : AbstractCountNegativesTest<CountNegativesBinary>(CountNegativesBinary())
