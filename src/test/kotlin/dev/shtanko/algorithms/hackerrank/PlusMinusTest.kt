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

package dev.shtanko.algorithms.hackerrank

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

sealed class PlusMinusTest<out T : PlusMinus>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                doubleArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1, 1, 0, -1, -1),
                doubleArrayOf(0.400000, 0.400000, 0.200000),
            ),
            Arguments.of(
                intArrayOf(-4, 3, -9, 0, 4, 1),
                doubleArrayOf(0.500000, 0.3333333333333333, 0.16666666666666666),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `plus minus test`(arr: IntArray, expected: DoubleArray) {
        val actual = strategy(arr)
        assertThat(actual).isEqualTo(expected)
    }

    data object BruteForceTest : PlusMinusTest<PlusMinus.BruteForce>(PlusMinus.BruteForce)
}
