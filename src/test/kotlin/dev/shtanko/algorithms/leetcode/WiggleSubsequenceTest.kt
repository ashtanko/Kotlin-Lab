/*
 * Copyright 2021 Alexey Shtanko
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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal abstract class WiggleSubsequenceTest<out T : WiggleSubsequence>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0
            ),
            Arguments.of(
                intArrayOf(1, 2),
                2
            ),
            Arguments.of(
                intArrayOf(1, 7, 4, 9, 2, 5),
                6
            ),
            Arguments.of(
                intArrayOf(1, 17, 5, 10, 13, 15, 10, 5, 16, 8),
                7
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                2
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `wiggle max length test`(nums: IntArray, expected: Int) {
        val actual = strategy.perform(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

internal class WSBruteForceTest : WiggleSubsequenceTest<WSBruteForce>(WSBruteForce())
internal class WSDPTest : WiggleSubsequenceTest<WSDP>(WSDP())
internal class WSLinearDPTest : WiggleSubsequenceTest<WSLinearDP>(WSLinearDP())
internal class WSSpaceOptimizedDPTest : WiggleSubsequenceTest<WSSpaceOptimizedDP>(WSSpaceOptimizedDP())
internal class WSGreedyTest : WiggleSubsequenceTest<WSGreedy>(WSGreedy())
