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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class TransposeMatrixTest<out T : TransposeMatrix>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
                arrayOf(intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), intArrayOf(3, 6, 9)),
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6)),
                arrayOf(intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(3, 6)),
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6)),
                arrayOf(intArrayOf(1, 3, 5), intArrayOf(2, 4, 6)),
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9), intArrayOf(10, 11, 12)),
                arrayOf(intArrayOf(1, 4, 7, 10), intArrayOf(2, 5, 8, 11), intArrayOf(3, 6, 9, 12)),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9),
                    intArrayOf(10, 11, 12),
                    intArrayOf(13, 14, 15),
                ),
                arrayOf(intArrayOf(1, 4, 7, 10, 13), intArrayOf(2, 5, 8, 11, 14), intArrayOf(3, 6, 9, 12, 15)),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `transpose test`(matrix: Array<IntArray>, expected: Array<IntArray>) {
        val actual = strategy(matrix)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class TransposeMatrixCopyDirectlyTest : TransposeMatrixTest<TransposeMatrix>(TransposeMatrixCopyDirectly())
