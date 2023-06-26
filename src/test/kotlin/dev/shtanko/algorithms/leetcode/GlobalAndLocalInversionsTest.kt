/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

internal abstract class GlobalAndLocalInversionsTest<out T : GlobalAndLocalInversions>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                true,
            ),
            Arguments.of(
                intArrayOf(0),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 0, 2),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 0),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `is ideal permutation test`(a: IntArray, expected: Boolean) {
        val actual = strategy.isIdealPermutation(a)
        assertThat(actual).isEqualTo(expected)
    }
}

internal class IdealPermutationRememberMinimumTest :
    GlobalAndLocalInversionsTest<IdealPermutationRememberMinimum>(IdealPermutationRememberMinimum())

internal class IdealPermutationLinearScanTest :
    GlobalAndLocalInversionsTest<IdealPermutationLinearScan>(IdealPermutationLinearScan())
