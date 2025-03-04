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

abstract class SearchMatrixTest<out T : SearchMatrix>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 3, 5, 7),
                    intArrayOf(10, 11, 16, 20),
                    intArrayOf(23, 30, 34, 60),
                ),
                3,
                true,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 3, 5, 7),
                    intArrayOf(10, 11, 16, 20),
                    intArrayOf(23, 30, 34, 60),
                ),
                13,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1),
                ),
                1,
                true,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1),
                ),
                0,
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(),
                ),
                0,
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `search matrix test`(matrix: Array<IntArray>, target: Int, expected: Boolean) {
        val actual = strategy.invoke(matrix, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class SearchMatrixBSTest : SearchMatrixTest<SearchMatrix>(SearchMatrixBS())
