/*
 * Copyright 2023 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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

abstract class LongestArithmeticSubsequenceTest<out T : LongestArithmeticSubsequence>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(3, 6, 9, 12),
                4,
            ),
            Arguments.of(
                intArrayOf(9, 4, 7, 2, 10),
                3,
            ),
            Arguments.of(
                intArrayOf(20, 1, 15, 3, 10, 5, 8),
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `longest arith seq length test`(nums: IntArray, expected: Int) {
        val actual = strategy.longestArithSeqLength(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

class LongestArithmeticSubsequenceDPTest :
    LongestArithmeticSubsequenceTest<LongestArithmeticSubsequence>(LongestArithmeticSubsequenceDP())
