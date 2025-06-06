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

package dev.shtanko.concurrency

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class MatrixMultiplyStrategyTest<out T : MatrixMultiplyStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    listOf(1, 2, 3),
                    listOf(4, 5, 6),
                ),
                listOf(
                    listOf(7, 8),
                    listOf(9, 10),
                    listOf(11, 12),
                ),
                listOf(
                    listOf(58, 64),
                    listOf(139, 154),
                ),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2, 3),
                    listOf(4, 5, 6),
                ),
                listOf(
                    listOf(7, 8, 9),
                    listOf(10, 11, 12),
                    listOf(13, 14, 15),
                ),
                listOf(
                    listOf(66, 72, 78),
                    listOf(156, 171, 186),
                ),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2),
                    listOf(3, 4),
                    listOf(5, 6),
                ),
                listOf(
                    listOf(7, 8, 9),
                    listOf(10, 11, 12),
                ),
                listOf(
                    listOf(27, 30, 33),
                    listOf(61, 68, 75),
                    listOf(95, 106, 117),
                ),
            ),
            Arguments.of(
                listOf(
                    listOf(4),
                    listOf(5),
                    listOf(6),
                ),
                listOf(
                    listOf(1, 2, 3),
                ),
                listOf(
                    listOf(4, 8, 12),
                    listOf(5, 10, 15),
                    listOf(6, 12, 18),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun matrixMulTest(a: List<List<Int>>, b: List<List<Int>>, expected: List<List<Int>>) {
        val actual = strategy(a, b)
        assertThat(actual).isEqualTo(expected)
    }
}

class MatrixMultiplyBruteForceTest : MatrixMultiplyStrategyTest<MatrixMultiplyStrategy>(MatrixMultiplyBruteForce())
class MatrixMultiplyTilesTest : MatrixMultiplyStrategyTest<MatrixMultiplyStrategy>(MatrixMultiplyTiles())
class MatrixMultiplyParallelismTest : MatrixMultiplyStrategyTest<MatrixMultiplyStrategy>(MatrixMultiplyParallelism())
